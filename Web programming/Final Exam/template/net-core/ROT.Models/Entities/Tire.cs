namespace ROT.Models.Entities
{
    public class Tire
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Manufacturer { get; set; }
        public string Type { get; set; }
        public int? Width { get; set; }
        public int? AspectRatio { get; set; }
        public int? Diameter { get; set; }
    }
}