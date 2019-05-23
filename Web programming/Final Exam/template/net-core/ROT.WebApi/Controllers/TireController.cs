using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using ROT.Models.InputModels;
using ROT.Services.Interfaces;

namespace ROT.WebApi.Controllers
{
    [Route("api/tires")]
    [ApiController]
    public class TireController : ControllerBase
    {
        private readonly ITireService _tireService;

        public TireController(ITireService tireService)
        {
            _tireService = tireService;
        }

        // TODO: Implement routes
        [HttpGet("{id}")]
        public IActionResult GetTireById(int id, bool? extensive = true)
        {
            try
            {
                if ((bool)extensive)
                {
                    var tire = _tireService.GetTireDetailsById(id);
                    if (tire != null)
                    {
                        return Ok(tire);
                    }
                    else
                    {
                        return StatusCode(404);
                    }
                }
                else
                {
                    var tire = _tireService.GetTireById(id);
                    if (tire != null)
                    {
                        return Ok(tire);
                    }
                    else
                    {
                        return StatusCode(404);
                    }
                }
            }
            catch (Exception ex)
            {
                return StatusCode(500);
            }

        }

        [HttpPost]
        public IActionResult CreateNewTier(TireInputModel tire)
        {
            if (_tireService.CreateTire(tire) == 1)
            {
                return Ok();
            }
            else
            {
                return StatusCode(400);
            }
        }
    }
}
