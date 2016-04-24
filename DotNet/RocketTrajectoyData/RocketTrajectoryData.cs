using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace RocketTrajectoyData
{
    public class RocketTrajectoryData
    {
        public RocketData  GetStandardRocketTrajectoryData()
        {
            var directory = Directory.GetCurrentDirectory();
            var objectsFromBasicFile = System.IO.File.ReadAllText("C:\\Sites\\czml-writer\\DotNet\\RocketTrajectoyData\\testfile.json");
            var jsonSettings = new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore, MissingMemberHandling = MissingMemberHandling.Ignore };
            return JsonConvert.DeserializeObject<RocketData>(objectsFromBasicFile);

        }

        public BetterRocketData GetBetterRocketData()
        {
            var objectsFromBasicFile = System.IO.File.ReadAllText("C:\\Sites\\czml-writer\\DotNet\\RocketTrajectoyData\\betterresults.json");
            var jsonSettings = new JsonSerializerSettings { NullValueHandling = NullValueHandling.Ignore, MissingMemberHandling = MissingMemberHandling.Ignore };
            return JsonConvert.DeserializeObject<BetterRocketData>(objectsFromBasicFile);
        }

    }
}
