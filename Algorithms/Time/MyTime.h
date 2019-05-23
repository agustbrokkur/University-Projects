#ifndef MYTIME_H_INCLUDED
#define MYTIME_H_INCLUDED
#include <iostream>
using namespace std;

class Time
{
    int _hours;
    int _minutes;
    int _seconds;
    void normalize();

public:
    Time();
    Time(int hours, int minutes, int seconds);
    Time(const Time& rhs);

    friend istream& operator >> (istream& left, Time& right);
    friend ostream& operator << (ostream& in, const Time& rhs);

    void operator = (const Time& rhs);
    Time operator + (const Time& rhs);
    Time operator - (const Time& rhs);
    bool operator < (const Time& rhs);

};


#endif // MYTIME_H_INCLUDED
