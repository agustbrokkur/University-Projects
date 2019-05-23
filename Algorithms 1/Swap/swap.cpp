#include <iostream>

using namespace std;

void swapFrontBack(int rod[], int telja)
{
    telja--;
    int x = rod[0];
    int y = rod[telja];
    rod[telja] = x;
    rod[0] = y;
}

int main()
{
    const int star = 20;
    int rada = 0;
    int tolur[star] = {0};

    cout << "Input size of array: ";
    cin >> rada;


    if (rada > 0 && rada < 21)
    {
        cout << "Input " << rada<< " elements: ";

        for (int i = 0; i < rada; i++)
        {
            cin >> tolur[i];
            //cout << tolur[i] << endl;
        }

        swapFrontBack(tolur, rada);
        for (int i = 0; i < rada; i++)
        {
            cout << tolur[i] << endl;
        }
    }

    else
    {
        cout << "Invalid size!";
        return 0;
    }
}
