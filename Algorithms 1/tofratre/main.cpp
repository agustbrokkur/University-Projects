#include <iostream>

using namespace std;

int main()
{
    double x = 1;
    double y = 2;
    int teljari = 0;
    do
    {
        x = x + (x / y);
        teljari++;
        y++;
    } while (x < 99);
    cout << "Talan er : " << y;
    return 0;
}
