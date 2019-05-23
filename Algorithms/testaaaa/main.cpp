#include <iostream>

using namespace std;

namespace Test
{
    class Program
    {
        static void Main(string[] args)
        {
            string inputString = System.Console.ReadLine();
            char[] input = inputString.ToCharArray();
            //Dictionary stores occurences of each letter in the input string.
            Dictionary count = new Dictionary();
            char curr;
            foreach (var item in input)
            {
                //Non-alphabetic characters are ignored.
                if (!Char.IsLetter(item))
                    continue;
                curr = char.ToLower(item);
                //Counts further occurences of curr.
                if (count.ContainsKey(curr))
                    count[curr] = count[curr] + 1;
                else
                {
                    //Counts the first occurence of curr.
                    count.Add(curr, 1);
                }
            }
            foreach (var item in count)
            {
                System.Console.WriteLine(item.ToString());
            }
        }
    }
}
