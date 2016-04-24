using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Routing;
using System.Web.UI;
using CesiumLanguageWriter;
using RocketTrajectoyData;

namespace ExampleCesiumLanguageServer
{

    public class BetterInfoHandler : IRouteHandler, IHttpHandler
    {
        public IHttpHandler GetHttpHandler(RequestContext requestContext)
        {
            return this;
        }

        public void ProcessRequest(HttpContext context)
        {
            context.Response.ContentType = "application/json";

            using (var outputStream = new StreamWriter(context.Response.OutputStream))
            {
                var cesiumWriter = new CesiumStreamWriter();
                var output = new CesiumOutputStream(outputStream);

                output.PrettyFormatting = true;

                // The whole body of CZML must be wrapped in a JSON array, opened here.
                output.WriteStartSequence();

                // The first packet (JSON object) of CZML must be the document packet.
                using (var entity = cesiumWriter.OpenPacket(output))
                {
                    entity.WriteId("document");
                    entity.WriteVersion("1.0");

                }


                // write an entity for each rocket launch phase

                var rocketData = new RocketTrajectoryData();

                var entities = rocketData.GetBetterRocketData();

                // get each phase


                foreach (var stage in entities.stages)
                {

                    using (var packet = cesiumWriter.OpenPacket(output))
                    {
                        packet.WriteId("RocketLaunch");
                        using (var position = packet.OpenPositionProperty())
                        {

                            var cartesianVelocitySet = GenerateCartesianVelocitySet(stage);
                            position.WriteCartesianVelocity(cartesianVelocitySet.Item1, cartesianVelocitySet.Item2);
                        }
                        using (var description = packet.OpenDescriptionProperty())
                        {
                            description.WriteString("This is the description of this element");
                        }

                        using (var path = packet.OpenPathProperty())
                        {
                            using (var material = path.OpenMaterialProperty())
                            {
                                using (var outline = material.OpenSolidColorProperty())
                                {
                                    using (var colour = outline.OpenColorProperty())
                                    {
                                        colour.WriteRgba(Color.DarkGoldenrod);
                                    }
                                }
                            }
                            path.WriteWidthProperty(8);
                            path.WriteLeadTimeProperty(10);
                            path.WriteTrailTimeProperty(1000);
                            path.WriteResolutionProperty(5);
                        }


                    }
                }

                output.WriteEndSequence();
            }
        }

        /// <summary>
        /// Generates a set of position, time and DV in x,y,z 
        /// </summary>
        /// <returns></returns>
        private Tuple<List<JulianDate>, List<Motion<Cartesian>>> GenerateCartesianVelocitySet(Stage stage)
        {
            JulianDate date = new JulianDate(DateTime.Now);

            var dateList = new List<JulianDate>();
            var motionList = new List<Motion<Cartesian>>();
            foreach (var profiledata in stage.data.profiledata)
            {
                Tuple<float?, Motion<Cartesian>> result = null;
                try
                {
                    result = GenerateStageFrameData(profiledata);
                    var timeChange = result.Item1;
                    var motion = result.Item2;
                    dateList.Add(date.AddSeconds(timeChange.Value));
                    motionList.Add(motion);
                }
                catch (Exception)
                {
                    Console.WriteLine("null Error");
                }
                //var result =  GenerateStageFrameData(profiledata);
                
            }

            return new Tuple<List<JulianDate>, List<Motion<Cartesian>>>(dateList, motionList);


        }

        private Tuple<float?, Motion<Cartesian>> GenerateStageFrameData(Profiledata arg)
        {
            var baseCartesian = CesiumDataManager.GetBaseCartesian();
            var unitVector = baseCartesian.Normalize();

            var altitude = (arg.altitude*1000*unitVector.Z) + baseCartesian.Z;
            var downRange = (arg.downrange*1000*unitVector.X) + baseCartesian.X;
            var time = arg.time;
            var deltaAlt = arg.dalt;
            var deltaDownRange = arg.ddrange;

            if (altitude != null)
            {
                if (downRange != null)
                {
                    var motion = new Motion<Cartesian>(new Cartesian(altitude.Value, baseCartesian.Y, downRange.Value),
                        new Cartesian(deltaDownRange, 0, deltaAlt));

                    var tuple = new Tuple<float?, Motion<Cartesian>>(time, motion);
                    return tuple;
                }
            }
            throw new NullReferenceException();
        }

        public bool IsReusable
        {
            get { return true; }
        }
    }
}

