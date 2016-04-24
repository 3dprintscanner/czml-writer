using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CesiumLanguageWriter;
using GeometricComputations;

namespace RocketTrajectoyData
{
    public class CesiumDataManager
    {
        public static Cartesian GenerateCartesian(double? x, double? y)
        {
            var thisPosition = GetBaseCartesian();

            var unitVector = thisPosition.Normalize();



            if (x != null && y != null)
            {
                var translatedx = (x * 1000 * unitVector.X) + thisPosition.X;
                var translatedy = (y * 1000 * unitVector.Y) + thisPosition.Z;

                var thisX = ((double)x * 1000) + thisPosition.X;
                var thisY = ((double)y * 1000) + thisPosition.Z;
                return new Cartesian(translatedx.Value, thisPosition.Y, translatedy.Value);
            }
            return new Cartesian();
        }

        public static Cartesian GetBaseCartesian()
        {

            var carto = GetBaseCartoGraphic();
            Ellipsoid ellipsoid = Ellipsoid.Wgs84;

            var baseCartesian = ellipsoid.ToCartesian(carto);
            return baseCartesian;

        }

        private static Cartographic GetBaseCartoGraphic()
        {
            const double canavlat = (28.4 * Math.PI) / 180.0;
            const double canavLong = (-80.5 * Math.PI) / 180.0;
            return new Cartographic(canavLong, canavlat, 0);
        }

        public Cartesian GetReferenceCartesian()
        {
            if (ReferenceCartesian.Y == 0)
            {
                return GetBaseCartesian();
            }
            return ReferenceCartesian;
        }

        public void SetReferenceCartesian(Cartesian cartesian)
        {
            this.ReferenceCartesian = cartesian;
        }
       

        public void GenerateReferenceCartesian(Cartesian baseCartesian, Profiledata profiledata)
        {
            var unitVector = baseCartesian.Normalize();
            var downRange = (profiledata.actualdownrange * 1000.0 * unitVector.X) + baseCartesian.X;
            this.ReferenceCartesian = new Cartesian(downRange.Value, baseCartesian.Y, baseCartesian.Z);
        }

        private Cartesian ReferenceCartesian { get; set; }
    }
}
