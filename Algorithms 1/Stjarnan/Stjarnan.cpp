#include <iostream>

using namespace std;

int main()
{
    int N;

    cout << "Input an integer: ";
    cin >> N;

    for(int i = N; i > 0; i--)
        {
            for(int x = i; x > 0; x--)
            {
                cout << "*";
            }
            cout << endl;
        }

    return 0;
}
