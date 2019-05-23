using System;
using System.Collections.Generic;
using System.Text;

namespace ROT.Models.Dtos
{
    public class TireDetailsDto
    {

        public int Id { get; set; }
        public String Name { get; set; }
        public String Manufacturer { get; set; }
        public String Type { get; set; }
        public int? Width { get; set; }
        public int? AspectRatio { get; set; }
        public int? Diameter { get; set; }
    }
}
