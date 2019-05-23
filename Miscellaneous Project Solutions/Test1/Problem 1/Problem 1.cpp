#include <iostream>

using namespace std;

int main()
{
    int first;
    int second;

    cout << "Enter the first number: ";
    cin >> first;

    cout << "Enter the second number: ";
    cin >> second;

    if (first >= second)
    {
        while(second <= first)
            {
                cout << second << endl;
                second = second + 3;
            }
    }
    else
    {
        while(first <= second)
            {
                cout << first << endl;
                first = first + 3;
            }
    }

    return 0;
}
