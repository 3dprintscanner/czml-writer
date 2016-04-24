
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RocketTrajectoyData
{


    public class BetterRocketData
    {
        public IEnumerable<Stage> stages { get; set; }
    }

    public class Stage
    {
        public string name { get; set; }
        public Data data { get; set; }
    }

    public class Data
    {
        public IEnumerable<Profiledata> profiledata { get; set; }
    }

    public class Profiledata
    {
        public float? altitude { get; set; }
        public float? velocity { get; set; }
        public float? time { get; set; }
        public float? downrange { get; set; }
        public float deltav { get; set; }
        public float dalt { get; set; }
        public float ddrange { get; set; }
        public float? actualdownrange { get; set; }
    }

}
