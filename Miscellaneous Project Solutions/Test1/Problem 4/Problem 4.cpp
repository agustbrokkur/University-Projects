#include <iostream>

using namespace std;

void readFirstTwo(int a1, int b1)
{
    int a;
    int b;
    cin >> a;
    cin >> b;
    a1 = a;
    b1 = b;
}
void readNextTwo(int c1, int d1)
{
    int c;
    int d;
    cin >> c;
    cin >> d;
    c1 = c;
    d1 = d;
}
void printNumbers(int a, int b, int c, int d)
{
    //int e = a + b + c + d;
    //cout << "The sum of the numbers " << a << ", " << b << ", " <<
    //                                 c << ", " << d << ", is " << e;
    cout << a << endl;
    cout << b << endl;;
    cout << c << endl;
    cout << d << endl;
}
void readNumbers(int a, int b, int c, int d)
{
    readFirstTwo(a, b);
    readNextTwo(c, d);
}

int main(int a, int b, int c, int d)
{
    readNumbers(a, b, c, d);
    printNumbers(a, b, c, d);
}
