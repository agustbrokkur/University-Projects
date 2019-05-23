#include <iostream>
#include <iomanip>

using namespace std;

double money(double dollar, double numer, double denom)
{
    double temp = numer / denom;
    temp = temp + dollar;
    return temp;
}

double hluti(double peningur, int shares)
{
    double money(shares);
    return money * peningur;
}

int main()
{
    string boli;
    int shares;
    int dollar;
    int numer;
    int denom;
    double peningur;

    start:
    cout << "Enter number of shares : ";
    cin >> shares;

    cout << "Enter price (dollars, numerator, denominator): ";
    cin >> dollar >> numer >> denom;

    peningur = money(dollar, numer, denom);
    peningur = hluti(peningur, shares);
    cout<< setprecision(2) << fixed << shares << " shares with market price " << dollar << " "
         << numer << "/" << denom << " have value $" << peningur;

    cout << endl << "Continue: ";
    cin >> boli;

    if (boli == "y" || boli == "Y")
    {
        goto start;
    }
    else
    {
        return 0;
    }
}
