using System.Collections.Generic;
using ROT.Models.Dtos;
using ROT.Models.InputModels;
using ROT.Repositories.Interfaces;
using ROT.Services.Interfaces;

namespace ROT.Services.Implementations
{
    public class TireService : ITireService
    {
        private readonly ITireRepository _tireRepository;

        public TireService(ITireRepository tireRepository)
        {
            _tireRepository = tireRepository;
        }

        public int CreateTire(TireInputModel tire)
        {
            // TODO: Implement
            return _tireRepository.CreateTire(tire);
        }

        public TireDetailsDto GetTireDetailsById(int id)
        {
            // TODO: Implement
            return _tireRepository.GetTireDetailsById(id);
        }

        public TireDto GetTireById(int id)
        {
            // TODO: Implement
            return _tireRepository.GetTireById(id);
        }
    }
}
