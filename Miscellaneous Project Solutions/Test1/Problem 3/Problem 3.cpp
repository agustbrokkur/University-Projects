#include <iostream>

using namespace std;

int CtoM(int miles, int type)
{
    if (type == 1)
    {
        return miles = miles * 1609;
    }
    else if (type == 2)
    {
        return miles = miles * 1852;
    }
    else if (type == 3)
    {
        return miles = miles * 10000;
    }
}

int main()
{
    int milur;
    int tegund;
    int tempar;

    cout << "Enter distance in miles: ";
    cin >> milur;
    cout << "Enter type of mile: ";
    cin >> tegund;

    if (tegund == 1)
    {
        tempar = CtoM(milur, tegund);
        cout << milur << " English mile(s) equals " << tempar << " meters" << endl;
        return 0;
    }

    else if (tegund == 2)
    {
        tempar = CtoM(milur, tegund);
        cout << milur << " Nautical mile(s) equals " << tempar << " meters" << endl;
        return 0;
    }

    else if (tegund == 3)
    {
        tempar = CtoM(milur, tegund);
        cout << milur << " Scandinavian mile(s) equals " << tempar << " meters" << endl;
        return 0;
    }

    else
    {
        cerr << "Invalid mile type selected!" << endl;
        return 0;
    }

}
