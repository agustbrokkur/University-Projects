using System;
using System.Collections.Generic;
using System.Runtime.Serialization;
using System.Text;

namespace Exterminator.Models.Exceptions
{
    public class ModelFormatException : Exception
    {
        public ModelFormatException() : base("Model format exception") { }

        public ModelFormatException(string message) : base(message) { }

        public ModelFormatException(string message, Exception innerException) : base(message, innerException){ }

        protected ModelFormatException(SerializationInfo info, StreamingContext context) : base(info, context) { }
    }
}
