using System.Linq;

namespace CleanThatCode.Community.Common
{
    // Your job is to implement this class
    public static class StringHelpers
    {
        // Instead of spaces it should be separated with dots, e.g. Hello World -> Hello.World
        public static string ToDotSeparatedString(this string str)
        {
            return str.Replace(" ", "."); ;
        }
        
        // All words in the string should be capitalized, e.g. teenage mutant ninja turtles -> Teenage Mutant Ninja Turtles
        public static string CapitalizeAllWords(this string str)
        {
            string[] newString = str.Split(' ');
            string answer = "";
            foreach(string s in newString)
            {
                answer = answer + " " + s.Substring(0, 1).ToUpper() + s.Substring(1);
            }
            answer = answer.Substring(1, answer.Length - 1);
            return answer;
        }

        // The words should be reversed in the string, e.g. Hi Ho Silver Away! -> Away! Silver Ho Hi
        public static string ReverseWords(this string str)
        {
            string[] newString = str.Split(' ');
            string answer = "";
            foreach (string s in newString)
            {
                answer = s + " " + answer;
            }
            answer = answer.Substring(0, answer.Length - 1);
            return answer;
        }
    }
}