using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace ROT.Models.InputModels
{
    public class TireInputModel
    {
        [Required]
        [MinLength(2)]
        public String Name { get; set; }
        [Required]
        [MaxLength(30)]
        public String Manufacturer { get; set; }
        [Required]
        [RegularExpression("^(winter|all-around|summer)$")]
        public String Type { get; set; }
        public int Width { get; set; }
        public int AspectRatio { get; set; }
        public int Diameter { get; set; }
    }
}
