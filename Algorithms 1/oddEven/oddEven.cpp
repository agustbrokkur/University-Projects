#include <iostream>

using namespace std;

int main()
{
    int N;
    cout << "Input n: ";
    cin >> N;

    while(N > 0)
    {
        if(N%2 == 0)
        {
            cout << N << " is even" << endl;
        }
        else
        {
            cout << N << " is odd" << endl;
        }
        N--;
    }
    return 0;
}
