/*
 * Verkefnalausnir - Fall 2016
 * Verk08 - Finite Automata Programming
 *
 * A program that simulates an automaton A.
 */
#include <iostream>
#include <cstring>
#include <cstdio>

#define read(file) freopen(file,"r",stdin)

using namespace std;

const int DEFAULT = -1;

// The start state of A.
const int START_STATE = 0;

// Returns true if 'state' is an accept state
// of A, false otherwise.
bool is_accept_state(int state)
{
    return state == 3;
}

// The transition function (delta) of A. I.e. a
// function that, given the current state (i.e. 'state')
// and input symbol (i.e. 'input'), returns the state
// to which A transits.
int move(int state, char input)
{
    switch(state)
    {
        case 0:
            if(isupper(input)) return 1;
            else if(ispunct(input)) return 2;
            else if(islower(input)) return 0;
            break;
        case 1:
            if(isupper(input)) return 1;
            else if(ispunct(input)) return 3;
            else if(islower(input)) return 1;
            break;
        case 2:
            if(isupper(input)) return 3;
            else if(ispunct(input)) return 4;
            else if(islower(input)) return 2;
            break;
        case 3:
            if(isupper(input)) return 3;
            else if(ispunct(input)) return 4;
            else if(islower(input)) return 3;
            break;
        case 4:
            if(isupper(input)) return 4;
            else if(ispunct(input)) return 4;
            else if(islower(input)) return 4;
            break;
    }
    return DEFAULT; //The default (or error) state
}

// Returns true if A accepts the string
// s, false otherwise.
bool accept(const char s[])
{
    int state = START_STATE;
    for(int i=0; s[i] != '\0'; i++)
    {
        state = move(state, s[i]);
    }
    return is_accept_state(state);
}

int main()
{
	// Uncomment the next line to read test data from file
	//read("input.txt");

    int runs;
    char input_string[1000];
    cin >> runs;
    cin.ignore(1, '\n');

    for(int i = 0; i < runs; i++)
    {
        cin.getline(input_string, sizeof(input_string), '\n');

        // Remove carriage return character (usually on Windows)
        if (input_string[strlen(input_string)-1] == '\r')
        {
            input_string[strlen(input_string)-1] = '\0';
        }

        cout << accept(input_string) << endl;
    }
    return 0;
}
