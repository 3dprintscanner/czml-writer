using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NUnit.Framework;

namespace RocketTrajectoyData
{
    [TestFixture]
    public class Tests
    {

        [Test]
        public void IGetBetterRocketData()
        {
            var data = new BetterRocketData();
            Assert.IsNotNull(data);
        }
    }
}
