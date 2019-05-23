using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Text;

namespace Exterminator.Models.Exceptions
{
    public class ResourceNotFoundException : Exception
    {
        public ResourceNotFoundException() : base("The resource was not found") { }

        public ResourceNotFoundException(string message) : base(message) { }

        public ResourceNotFoundException(string message, Exception innerException) : base(message, innerException) { }

        protected ResourceNotFoundException(SerializationInfo info, StreamingContext context) : base(info, context) { }
    }
}
