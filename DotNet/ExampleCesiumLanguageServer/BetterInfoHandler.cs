using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Web;
using System.Web.Routing;
using System.Web.UI;
using CesiumLanguageWriter;
using GeometricComputations;
using RocketTrajectoyData;

namespace ExampleCesiumLanguageServer
{

    public class BetterInfoHandler : IRouteHandler, IHttpHandler
    {
        CesiumDataManager manager = new CesiumDataManager();
        
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
                    var cartesianVelocitySet = GenerateCartesianVelocitySet(stage);

                    var clr = GetNextColour();
                    var count = 0;
                    foreach (var positions in cartesianVelocitySet.Item2)
                    {
                        count++;

                        if (count % 100 == 0)
                        {
                            using (var packet = cesiumWriter.OpenPacket(output))
                            {
                                using (var point = packet.OpenPointProperty())
                                {
                                    point.WriteColorProperty(Color.Yellow);
                                    point.WritePixelSizeProperty(10.0);
                                }

                                using (var desc = packet.OpenDescriptionProperty())
                                {
                                    desc.WriteString("x,y,z:" + positions.Value.ToString());
                                }
                                using (var position = packet.OpenPositionProperty())
                                {

                                    position.WriteCartesian(positions.Value);
                                }
                            }    
                        }
                           
                    }
                    
                    
                    
                    using (var packet = cesiumWriter.OpenPacket(output))
                    {
                        packet.WriteId("RocketLaunch");
                        using (var position = packet.OpenPositionProperty())
                        {

                            
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

        private Color GetNextColour()
        {
            var colours = new Color[3]{Color.Aqua, Color.Blue, Color.Yellow};
            Random random = new Random();

            Color col = colours[random.Next(2)];
            return col;
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
                    //result = GenerateStageFrameData(profiledata);
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



        private Tuple<float?, Motion<Cartesian>> GenerateBetterStageFrameData(Profiledata arg)
        {
            
            // check for nulls 

            if (arg.altitude == null || arg.actualdownrange == null || arg.downrange == null)
            {
                throw new NullReferenceException();
            }
            
            
            // get a reference cartesian, e.g the last one.

            var baseCartesian = manager.GetReferenceCartesian();


            // get this reference cartesian and align it to the surface of the earth

            var ellipsoid = Ellipsoid.Wgs84;
            var surfaceScaledCartesian = ellipsoid.ScaleToGeodeticSurface(baseCartesian);
            //var surfaceScaledCartesian = baseCartesian;
            var surfaceNormal = ellipsoid.GeodeticSurfaceNormal(surfaceScaledCartesian);

            // add a downrange vector and scale it to the surface 
            var drVector =
                surfaceScaledCartesian.Add(
                    new Cartesian((arg.actualdownrange.Value*1000.0*surfaceNormal.X) + baseCartesian.X, 0, 0));
            var downrangeScaledCartesian = ellipsoid.ScaleToGeodeticSurface(drVector);
            var downrangeScaledCartesianNormal = ellipsoid.GeodeticSurfaceNormal(downrangeScaledCartesian);

            // set the reference cartesian to this surface cartesian 

            manager.SetReferenceCartesian(downrangeScaledCartesian);


            // add a height vector

            var altVector =
                baseCartesian.Add(new Cartesian(arg.altitude.Value * 1000.0 * downrangeScaledCartesianNormal.X, arg.altitude.Value * 1000.0 * downrangeScaledCartesianNormal.Y,
                    arg.altitude.Value * 1000.0 * downrangeScaledCartesianNormal.Z));

            // add ancilliary data
            var time = arg.time;
            var deltaAlt = arg.dalt;
            var deltaDownRange = arg.ddrange;


            var motion = new Motion<Cartesian>(altVector,
                        new Cartesian(deltaDownRange, 0, deltaAlt));

            var tuple = new Tuple<float?, Motion<Cartesian>>(time, motion);
            return tuple;

        }

        private Tuple<float?, Motion<Cartesian>> GenerateStageFrameData(Profiledata arg)
        {

            //var baseCartesian = manager.GetReferenceCartesian();
            var baseCartesian = CesiumDataManager.GetBaseCartesian();
            //var unitVector = baseCartesian.Normalize();
            var ellipsoid = Ellipsoid.Wgs84;

            var unitVector = ellipsoid.GeodeticSurfaceNormal(baseCartesian);
            // add altitude to the vector
            var altVector =
                baseCartesian.Add(new Cartesian(arg.altitude.Value*1000.0*unitVector.X, arg.altitude.Value*1000.0*unitVector.Y,
                    arg.altitude.Value*1000.0*unitVector.Z));

            // add downrange to the vector

            var drVector =  altVector.Add(new Cartesian((arg.actualdownrange.Value*1000.0*unitVector.X), 0, 0));
        
            var altitude = (arg.altitude*1000.0 * (unitVector.Z)) + baseCartesian.Z;
            var downRange = (arg.actualdownrange*1000.0* unitVector.X) + baseCartesian.X;
            //var latitude = arg.downrange*1000.0*unitVector.Y + baseCartesian.Y;
            var time = arg.time;
            var deltaAlt = arg.dalt;
            var deltaDownRange = arg.ddrange;

            
            if (altitude != null)
            {
                if (downRange != null)
                {
                    //var motion = new Motion<Cartesian>(new Cartesian(downRange.Value, baseCartesian.Y, altitude.Value),
                    //    new Cartesian(deltaDownRange, 0, deltaAlt));
                    manager.GenerateReferenceCartesian(baseCartesian, arg);
                    var currentCartesian = new Cartesian(downRange.Value, baseCartesian.Y,altitude.Value);
                    var scaledCartesian = ellipsoid.ScaleToGeodeticSurface(currentCartesian);
                    

                    var normal = scaledCartesian.Normalize();
                    var newScaledCartesian = scaledCartesian.Add(new Cartesian(0, 0, ((arg.altitude*1000.0*normal.Z)).Value));
                    
                    var motion = new Motion<Cartesian>(drVector,
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

