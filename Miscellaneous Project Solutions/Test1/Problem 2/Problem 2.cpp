#include <iostream>

using namespace std;

int main()
{
    char staf;
    int tala;
    int telja = 1;

    cout << "Enter letter: ";
    cin >> staf;
    cout << "Enter how many: ";
    cin >> tala;

    while(telja <= tala)
    {
        for(int i = 0; i < telja; i++)
        {
            cout << staf;
        }
        telja++;
        cout << endl;
    }

    return 0;
}
