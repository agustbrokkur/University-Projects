#include "MyTime.h"
#include <iostream>
using namespace std;


int main()
{
    Time t1, t2, t3, t4;
    cin >> t1;
    cin >> t2;
    cin >> t3;


    cout << "Time1: " << t1;
    cout << "Time2: " << t2;
    cout << "Time3: " << t3;


    t4 = t1 + t2;
    cout << "Time4: " << t4;


    t1 = t3 - t4;
    cout << "Time1: " << t1;


    if (t1 < t3)
    {
        cout << "Time1 < Time3" << endl;
    }
    else
    {
        cout << "Time3 >= Time1" << endl;
    }


    Time t5 = t2 + Time(0,0,1);
    if (t5 < t2)
    {
        cout << "Time5 < Time2" << endl;
    }
    else
    {
        cout << "Time5 >= Time2" << endl;
    }


    cout << "Almost midnight: " << Time(0,0,0) - Time(0,0,1) << endl;


    return 0;
}
