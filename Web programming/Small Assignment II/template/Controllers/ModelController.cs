using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using template.Models;
using small_assignment_2.Models;
using template.Data;
using template.Extensions;

namespace small_assignment_2.Controllers
{
    [Route("api")]
    public class ModelController : Controller
    {
        [HttpGet]
        [Route("models")]
        public IActionResult GetAllModels([FromQuery]int pageNumber = 1, [FromQuery]int pageSize = 10)
        {
            Envelope<ModelDTO> env = new Envelope<ModelDTO>();

            var mod = DataContext.Models.ToLightWeight(GetAcceptedLanguage()).ToList();
            
            foreach (var item in mod)
            {
                item.Links.AddReference("self", "/models");
            }

            env.Items = mod;
            env.PageSize = pageSize;
            env.PageNumber = pageNumber;

            return Ok(env);
        }

        [HttpGet]
        [Route("models/{modelId:int}")]
        public IActionResult GetModelById(int modelId)
        {
            var mod = DataContext.Models.ToDetails(GetAcceptedLanguage()).Find(x => x.Id == modelId);

            mod.Links.AddReference("self", "models" + modelId);

            return Ok(mod);
        }

        public string GetAcceptedLanguage()
        {
            if (HttpContext.Request.Headers["Accept-language"].ToString() == "")
            {
                return "en-US";
            }
            else
            {
                return HttpContext.Request.Headers["Accept-language"].ToString();
            }
        }
    }
}