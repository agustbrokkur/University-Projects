#include "MyTime.h"

Time::Time()
{
    _hours = 0;
    _minutes = 0;
    _seconds = 0;
}

Time::Time(int hours, int minutes, int seconds)
{
    _hours = hours;
    _minutes = minutes;
    _seconds = seconds;
}

Time::Time(const Time& rhs)
{
    _hours = rhs._hours;
    _minutes = rhs._minutes;
    _seconds = rhs._seconds;
}

void Time::normalize()
{
    int s = _seconds;
    int m = _minutes;
    int h = _hours;


    while(s < 0)
    {
        s += 60;
        m--;
    }


    while(m < 0)
    {
        m += 60;
        h--;
    }


    while(h < 0)
    {
        h = h + 24;
    }


    _seconds = s % 60;
    _minutes = (m + s/60) % 60;
    _hours   = (h + m/60 + s/3600) % 24;
}

Time Time::operator + (const Time& rhs)
{
    Time result;

    result._hours = rhs._hours + _hours;
    result._minutes = rhs._minutes + _minutes;
    result._seconds = rhs._seconds + _seconds;

    result.normalize();

    return result;
}

Time Time::operator - (const Time& rhs)
{
    Time result;

    result._hours = _hours - rhs._hours;
    result._minutes = _minutes - rhs._minutes;
    result._seconds = _seconds - rhs._seconds;

    result.normalize();

    return result;
}

bool Time::operator < (const Time& rhs)
{
    if (_hours < rhs._hours)
    {
        return true;
    }
    else if (_hours == rhs._hours && _minutes < rhs._minutes)
    {
        return true;
    }
    else if (_hours == rhs._hours && _minutes == rhs._minutes && _seconds < rhs._seconds)
    {
        return true;
    }
    else
    {
        return false;
    }
}

void Time::operator = (const Time& rhs)
{
    _hours = rhs._hours;
    _minutes = rhs._minutes;
    _seconds = rhs._seconds;
}

istream& operator >> (istream& in, Time& rhs)
{
    //TODO: read the time;
    in >> rhs._hours >> rhs._minutes >> rhs._seconds;
    rhs.normalize();

    return in;
}

ostream& operator << (ostream& in, const Time& rhs)
{
    if (rhs._hours < 10)
        in << "0";
    in << rhs._hours << ":";
    if (rhs._minutes < 10)
        in << "0";
    in << rhs._minutes << ":";
    if (rhs._seconds < 10)
        in << "0";
    in << rhs._seconds << endl;

    return in;
}
