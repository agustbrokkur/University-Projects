#include <iostream>
#include <bitset>

using namespace std;

int main()
{
    unsigned int u = 255U;
    short v = u;
    short sa = -7;
    int x = 24;
    int ux = -64;
    unsigned int h = (unsigned int)ux;
    unsigned int uy = -10 + 1U;
    int is = (short) x;
    short sb = 24 - is;
    //unsigned short usc = TMax + TMin;
    std::bitset<8> d(ux);
    std::cout << d << " " << ux << endl;
    return 0;
}
