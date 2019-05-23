#include <iostream>

using namespace std;

int main()
{
    int vara;
    cout << "Enter the cost of an item: ";
    cin >> vara;
    double vat = vara * 1.24;
    cout << "The total cost including VAT is: " << vat << endl;
    return 0;
}
