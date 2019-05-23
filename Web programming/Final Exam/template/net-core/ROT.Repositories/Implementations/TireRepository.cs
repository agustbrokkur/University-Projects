using System.Collections.Generic;
using System.Linq;
using ROT.Models.Dtos;
using ROT.Models.Entities;
using ROT.Models.InputModels;
using ROT.Repositories.Data;
using ROT.Repositories.Interfaces;

namespace ROT.Repositories.Implementations
{
    public class TireRepository : ITireRepository
    {
        private readonly TireDbContext _dbContext = new TireDbContext();

        public int CreateTire(TireInputModel tire)
        {
            // TODO: Implement
            if (tire == null)
            {
                return 0;
            }
            Tire newTire = new Tire()
            {
                Id = _dbContext.Tires.Max(t => t.Id) + 1,
                Name = tire.Name,
                Manufacturer = tire.Manufacturer,
                Type = tire.Type,
                Width = tire.Width,
                AspectRatio = tire.AspectRatio,
                Diameter = tire.Diameter

            };

            _dbContext.Tires.Add(newTire);
            _dbContext.SaveChanges();
            return 1;
        }

        public TireDetailsDto GetTireDetailsById(int id)
        {
            // TODO: Implement
            TireDetailsDto tire = _dbContext.Tires.Select(item => new TireDetailsDto
            {
                Id = item.Id,
                Name = item.Name,
                Manufacturer = item.Manufacturer,
                Type = item.Type,
                Width = item.Width,
                AspectRatio = item.AspectRatio,
                Diameter = item.Diameter

            }).FirstOrDefault(item => item.Id == id);
            return tire;
        }

        public TireDto GetTireById(int id)
        {
            // TODO: Implement
            TireDto tire = _dbContext.Tires.Select(item => new TireDto
            {
                Id = item.Id,
                Name = item.Name,
                Manufacturer = item.Manufacturer

            }).FirstOrDefault(item => item.Id == id);
            return tire;
        }
    }
}
