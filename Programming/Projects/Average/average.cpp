#include <iostream>
using namespace std;

int main()
{
    int first;
    int second;

    // Spyrja notandann um t�lu 1:
    cout << "Enter the first number:" << endl;
    cin >> first;

    // Spyrja notandann um t�lu 2:
    cout << "Enter the second number:" << endl;
    cin >> second;

    //Reikna me�altali�:
    int average = (first + second) / 2;

    // Prenta me�altali�:
    cout << "The average is: " << average;

    return 0;
}
