using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using template.Models;

namespace small_assignment_2.Models
{
    public class ModelDetailsDTO : HyperMediaModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Race { get; set; }
        public double Price { get; set; }

        public string Description { get; set; }
        public int Rarity { get; set; }
        public string DifficultyLevel { get; set; }
        public int YearOfRelease { get; set; }
        public string ImageUrl { get; set; }
    }
}
