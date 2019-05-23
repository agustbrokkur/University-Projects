#include <iostream>
using namespace std;

int main()
{
    int first;
    int second;

    // Spyrja notandann um tölu 1:
    cout << "Enter the first number:" << endl;
    cin >> first;

    // Spyrja notandann um tölu 2:
    cout << "Enter the second number:" << endl;
    cin >> second;

    //Reikna meðaltalið:
    int average = (first + second) / 2;

    // Prenta meðaltalið:
    cout << "The average is: " << average;

    return 0;
}
