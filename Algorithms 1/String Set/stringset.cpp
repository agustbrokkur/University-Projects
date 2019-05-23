#include "stringset.h"

StringSet operator + (const StringSet& lhs, const StringSet& rhs)
{
    StringSet result;

    return result;

}

StringSet operator * (const StringSet& lhs, const StringSet& rhs)
{
    StringSet result;

    return result;

}

ostream& operator << (ostream& in, const StringSet& rhs)
{
    for(size_t i = 0; i < rhs.size(); i++)
    {
        in << rhs.at(i) << " ";
    }

    return in;

}


