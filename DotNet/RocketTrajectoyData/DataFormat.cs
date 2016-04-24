using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace RocketTrajectoyData
{
    public class Datum
    {
        public List<double?> y { get; set; }
        public List<double?> x { get; set; }
        public string uid { get; set; }
        public string mode { get; set; }
        public string name { get; set; }
        public bool? showlegend { get; set; }
    }

    public class RocketData
    {
        public List<Datum> data { get; set; }
    }
}
