#include <iostream>

using namespace std;

double CtoF(double celsius)
{
    return 32 + (1.8 * celsius);
}

int main()
{
    int N;
    double faren;

    cout << "Input maximum celsius: ";
    cin >> N;

    for(int i = 0; i <= N; i++)
    {
        faren = CtoF(i);
        cout << i << " " << faren << endl;
    }

    return 0;
}
