using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Routing;
using CesiumLanguageWriter;
using GeometricComputations;
using GeoUtility;
using RocketTrajectoyData;

namespace ExampleCesiumLanguageServer
{
    
    
    /// <summary>
    /// This is a sample handler that uses CesiumLanguageWriter to return CZML in response
    /// to an HTTP request.  In this case it returns a handful of yellow dots that could have
    /// easily been computed client-side in JavaScript, but, the point here is to demonstrate
    /// returning CZML from a server that may have access to data needed by the client.
    /// </summary>
    public class InfoHandler : IHttpHandler, IRouteHandler
    {

        const int EARTH_RADIUS = 6371000;
        private RocketTrajectoryData rocketData;
 
        // convert the distance of the downrange into a distance from the launch site, at a specific trajectory

        
        
        /// <summary>
        /// This handles the HTTP request by writing some example CZML into the response.
        /// </summary>
        /// <param name="context">The current HttpContext</param>
        public void ProcessRequest(HttpContext context)
        {
            // A more complex example could examine context.Request here for
            // inputs coming from the client-side application.

            // Set the response type for CZML, which is JSON.
            context.Response.ContentType = "application/json";

            // Create an output stream writer for the response.
            using (var outputStream = new StreamWriter(context.Response.OutputStream))
            {
                var cesiumWriter = new CesiumStreamWriter();
                var output = new CesiumOutputStream(outputStream);

                // Since this is a demo, turning on PrettyFormatting makes the response easier to view
                // with web browser developer tools.  It just adds whitespace and newlines to the response,
                // so production environments would typically leave this turned off.
                output.PrettyFormatting = true;

                // The whole body of CZML must be wrapped in a JSON array, opened here.
                output.WriteStartSequence();

                // The first packet (JSON object) of CZML must be the document packet.
                using (var entity = cesiumWriter.OpenPacket(output))
                {
                    entity.WriteId("document");
                    entity.WriteVersion("1.0");
                   
                }

                using (var entity = cesiumWriter.OpenPacket(output))
                {
                    entity.WriteId("canavitem");
                    using (var pos = entity.OpenPositionProperty())
                    {

                        pos.WriteCartesian(CesiumDataManager.GetBaseCartesian());
                    }
                    using (var point = entity.OpenPointProperty())
                    {
                        point.WriteColorProperty(Color.Aqua);
                        point.WritePixelSizeProperty(10.0);
                    }

                }

                rocketData = new RocketTrajectoryData();

                var entities = rocketData.GetStandardRocketTrajectoryData();
                

                var cartList = new List<Cartesian>();
                var dateList = new List<JulianDate>();

                var now = DateTime.Now;
                
                for (int i = 0; i < entities.data[2].x.Count; i++)
                {
                    var cartesian = CesiumDataManager.GenerateCartesian(entities.data[2].x[i], entities.data[2].y[i]);
                    cartList.Add(cartesian);
                    var julDate = new JulianDate(now + TimeSpan.FromSeconds(i*2));
                    dateList.Add(julDate);

                }

                using (var thisEntity = cesiumWriter.OpenPacket(output))
                {
                    thisEntity.WriteId("testpath");
                    thisEntity.WriteDescriptionProperty("rocket launch path");
                    using (var position = thisEntity.OpenPositionProperty())
                    {
                        position.WriteCartesian(dateList, cartList);
                        position.WriteReferenceFrame("#referenceitem");
                    }

                    //using (var model = thisEntity.OpenModelProperty())
                    //{
                    //    model.WriteGltfProperty(new Uri("http://localhost:56332/Models/CesiumAir/Cesium_Air.gltf"),CesiumResourceBehavior.Embed);
                    //}

                    using (var path = thisEntity.OpenPathProperty())
                    {
                        using (var material = path.OpenMaterialProperty())
                        {
                            using (var outline = material.OpenSolidColorProperty())
                            {
                                using (var colour = outline.OpenColorProperty())
                                {
                                    colour.WriteRgba(Color.DarkSeaGreen);
                                }
                            }
                        }
                        path.WriteWidthProperty(8);
                        path.WriteLeadTimeProperty(10);
                        path.WriteTrailTimeProperty(1000);
                        path.WriteResolutionProperty(5);
                    }
                }

                

                // Close the JSON array that wraps the entire CZML document.
                output.WriteEndSequence();
            }
        }

       

        public bool IsReusable
        {
            // Return false if your handler cannot be reused for another request, true if it can.
            get { return true; }
        }

        /// <summary>
        /// Gets the IHttpHandler for this route, which is always "this" because this class
        /// is a combination of IHttpHandler and IRouteHandler.  All routing requests are routed
        /// to itself for HTTP handling.  See Global.asax.cs to route requests here.
        /// </summary>
        /// <param name="requestContext">The current request context</param>
        /// <returns>this</returns>
        public IHttpHandler GetHttpHandler(RequestContext requestContext)
        {
            return this;
        }
    }
}
