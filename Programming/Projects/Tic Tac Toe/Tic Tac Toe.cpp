#include <iostream>
#include <vector>

using namespace std;

bool hasWinnerX(vector< vector<char> >& rows)
{
    for (int i = 0; i < 3; i++)
    {
        if (rows[i][0] == 'X' && rows[i][1] == 'X' && rows[i][2] == 'X')
        {
            return true;
        }
        else if (rows[0][i] == 'X' && rows[1][i] == 'X' && rows[2][i] == 'X')
        {
            return true;
        }
    }
    if (rows[0][0] == 'X' && rows[1][1] == 'X' && rows[2][2] == 'X')
    {
        return true;
    }
    else if (rows[0][2] == 'X' && rows[1][1] == 'X' && rows[2][0] == 'X')
    {
        return true;
    }
    else if (rows[0][0] == 'X' && rows[0][1] == 'X' && rows[0][2] == 'X')
    {
        return true;
    }
    else if (rows[1][0] == 'X' && rows[1][1] == 'X' && rows[1][2] == 'X')
    {
        return true;
    }
    else if (rows[2][0] == 'X' && rows[2][1] == 'X' && rows[2][2] == 'X')
    {
        return true;
    }
    return false;
}

bool hasWinnerO(vector< vector<char> >& rows)
{
    for (int i = 0; i < 3; i++)
    {
        if (rows[i][0] == '0' && rows[i][1] == 'O' && rows[i][2] == 'O')
        {
            return true;
        }
        else if (rows[0][i] == 'O' && rows[1][i] == 'O' && rows[2][i] == 'O')
        {
            return true;
        }
    }
    if (rows[0][0] == 'O' && rows[1][1] == 'O' && rows[2][2] == 'O')
    {
        return true;
    }
    else if (rows[0][2] == 'O' && rows[1][1] == 'O' && rows[2][0] == 'O')
    {
        return true;
    }
    else if (rows[0][0] == 'O' && rows[0][1] == 'O' && rows[0][2] == 'O')
    {
        return true;
    }
    else if (rows[1][0] == 'O' && rows[1][1] == 'O' && rows[1][2] == 'O')
    {
        return true;
    }
    else if (rows[2][0] == 'O' && rows[2][1] == 'O' && rows[2][2] == 'O')
    {
        return true;
    }
    return false;
}

bool aTie()
{
    return true;
}

void readInputX(vector< vector<char> >& rows)
{
    char x;
    int counter = 0;

    yid:
    cout << "X position: ";
    cin >> x;


    int xa = x - '0';

    if (0 < xa && xa < 10)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                counter++;
                if (counter == xa)
                {
                    if (rows[i][k] != 'X' && rows[i][k] != 'O')
                    {
                        rows[i][k] = 'X';
                    }
                    else
                    {
                        cout << "Illegal move!" << endl;
                        counter = 0;
                        goto yid;

                    }
                }
            }
        }
    }
    else
    {
        cin.clear();
        cin.ignore();
        cout << "Illegal move!" << endl;
        goto yid;
    }

}
void readInputO(vector< vector<char> >& rows)
{
    char x;
    int counter = 0;

    xid:
    cout << "O position: ";
    cin >> x;


    int xa = x - '0';

    if (0 < xa && xa < 10)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                counter++;
                if (counter == xa)
                {
                    if (rows[i][k] != 'X' && rows[i][k] != 'O')
                    {
                        rows[i][k] = 'O';
                    }
                    else
                    {
                        cout << "Illegal move!" << endl;
                        counter = 0;
                        goto xid;

                    }
                }
            }
        }
    }
    else
    {
        cin.clear();
        cin.ignore();
        cout << "Illegal move!" << endl;
        goto xid;
    }


}


void displayBoard(vector< vector<char> >& rows)
{
    bool sigurX = false;
    bool sigurO = false;
    bool jafnt = false;
    bool stada = true;
    int counter = 0;

    for (int h = 0; h < 9; h++)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                cout << rows[i][k] << " ";
                counter++;

                if (counter % 2 == 1)
                {
                    stada = true;
                }
                else if (counter % 2 == 0)
                {
                    stada = false;
                }

                if (i == k && k == 3)
                {
                }
                sigurO = hasWinnerO(rows);
                sigurX = hasWinnerX(rows);
            }
        cout << endl;
        }

        if (sigurX == true)
        {
            cout << "Winner is: X";
            sigurX = false;
            counter = 9;
            break;
        }
        else if (sigurO == true)
        {
            cout << "Winner is: O";
            sigurO = false;
            counter = 9;
            break;
        }

        if (stada == true)
        {
            readInputX(rows);
        }
        else if (stada == false)
        {
            readInputO(rows);
        }
    }
    sigurO = hasWinnerO(rows);
    sigurX = hasWinnerX(rows);
    jafnt = aTie();
    if (sigurX == true && counter != 9)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                cout << rows[i][k] << " ";
            }
            cout << endl;
        }
        cout << "Winner is: X";
        jafnt = false;
    }
    else if (sigurO == true && counter != 9)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                cout << rows[i][k] << " ";
            }
            cout << endl;
        }
        cout << "Winner is: O";
        jafnt = false;
    }
    else if (jafnt == true && sigurX == false && sigurO == false)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int k = 0; k < 3; k++)
            {
                cout << rows[i][k] << " ";
            }
            cout << endl;
        }
        cout << "Draw!";
    }

}


int main()
{
    vector< vector<char> > rows;
    int h = 0;
    for (int i = 1; i <= 3; i++)
    {
        vector<char> row;
        for (int k = 1; k <= 3; k++)
        {
            h++;
            row.push_back((char)((h)+48));
        }
        rows.push_back(row);
    }

//    char cells[3][3];

    displayBoard(rows);

    return 0;
}
