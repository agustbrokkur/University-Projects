using System.Collections.Generic;
using ROT.Models.Dtos;
using ROT.Models.InputModels;

namespace ROT.Repositories.Interfaces
{
    public interface ITireRepository
    {
         TireDetailsDto GetTireDetailsById(int id);
         TireDto GetTireById(int id);
         int CreateTire(TireInputModel tire);
    }
}
