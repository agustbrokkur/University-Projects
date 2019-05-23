using System.Collections.Generic;
using ROT.Models.Dtos;
using ROT.Models.InputModels;

namespace ROT.Services.Interfaces
{
    public interface ITireService
    {
         TireDetailsDto GetTireDetailsById(int id);
         TireDto GetTireById(int id);
         int CreateTire(TireInputModel tire);
    }
}
