#ifndef STRINGSET_H_INCLUDED
#define STRINGSET_H_INCLUDED
#include <iostream>
#include <vector>
using namespace std;

class StringSet
{
vector<string> _Strengir;

public:

    string at(int i) const;

    friend StringSet operator * (const StringSet& lhs, const StringSet& rhs);
    friend StringSet operator + (const StringSet& lhs, const StringSet& rhs);
    friend ostream& operator << (ostream& in, const StringSet& rhs);
    friend void readFileKeywords(StringSet& setDocument, const char filename[]);
};

#endif // STRINGSET_H_INCLUDED
