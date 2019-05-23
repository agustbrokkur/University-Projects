#include <iostream>
#include <vector>

using namespace std;

void swapValues(int& v1, int& v2);

int main()
{
    vector<int> numbers;
    int number = 0;
    int counter = 0;
    int tempo = 0;
    bool check = true;

    while(number >= 0)
    {
        cin >> number;

        if(number >= 0)
        {
            numbers.push_back(number);
            counter++;
        }
    }
    if ((counter == 1 && (numbers[0] % 2 == 0)))
    {
        counter = 1;
        check = false;
    }

    int* slett = new int[counter / 2];

    number = 0;
    for(int i = 0; i < counter; i++)
    {
        if (numbers[i] % 2 == 0)
        {
            slett[number] = numbers[i];
            number++;
        }
    }

    if (number != 0)
    {
        counter++;
        for(int i = 0; i < number; i++)
        {
            if(((slett[i] % 2 == 0) && number == 1))
            {
                cout << slett[i] << " ";
                i = number;
            }
            else if(slett[i] % 2 == 0)
            {
                cout << slett[i] << " ";
            }

        }

        for(int i = 0; i < (number / 2); i++)
        {
            if (tempo + 2 <= counter && check == true)
            {
                swapValues(slett[tempo], slett[tempo + 1]);
                tempo = tempo + 2;
            }
        }

        cout << endl;

        for(int i = 0; i < number; i++)
        {
            if(((slett[i] % 2 == 0) && number == 1))
            {
                cout << slett[i] << " ";
                i = number;
            }
            else if(slett[i] % 2 == 0)
            {
                cout << slett[i] << " ";
            }
        }
    }

    delete slett;
    return 0;
}

//Lánağ frá SelectionSort dæminu.
void swapValues(int& v1, int& v2)
{
	int temp;
	temp = v1;
	v1   = v2;
	v2   = temp;
}
