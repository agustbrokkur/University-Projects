using Exterminator.Models.InputModels;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text;

namespace Exterminator.Models.Attributes
{
    public class Expertize : ValidationAttribute
    {
        protected override ValidationResult IsValid(object value, ValidationContext validationContext)
        {
            if(value.ToString().Equals("Ghost catcher"))
            {
                return ValidationResult.Success;
            } else if(value.ToString().Equals("Ghoul strangler"))
            {
                return ValidationResult.Success;
            } else if(value.ToString().Equals("Monster encager"))
            {
                return ValidationResult.Success;
            } else if(value.ToString().Equals("Zombie exploder"))
            {
                return ValidationResult.Success;
            } else
            {
                return new ValidationResult("Not a valid expertize.");
            }
            
        }
    }
}
