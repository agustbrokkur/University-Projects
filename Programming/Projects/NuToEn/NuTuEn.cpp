#include <iostream>

using namespace std;

int main()
{
    int tala = 0;
    const string units[19] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    const string tens[8] = {"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};

    cout << "Input number: ";
    cin >> tala;

    while (tala < 100 && tala > 0)
    {
        int upp = (tala-20)/10;
        int nidur = (tala - ((tala/10)*10)) -1;

        if (tala < 20)
        {
            cout << units[tala-1] << endl;;
            cout << "Input number: ";
            cin >> tala;
        }
        else if (tala >= 20  && nidur >= 0)
        {
            cout << tens[upp] << "-" << units[nidur] << endl;;
            cout << "Input number: ";
            cin >> tala;
        }
        else
        {
            cout << tens[upp] << endl;;
            cout << "Input number: ";
            cin >> tala;
        }
    }
    return 0;
}
