/*
 * Verkefnalausnir - Fall 2016
 * Project 14 - Rescue Robots
 */
#include <iostream>

#ifndef ONLINE_JUDGE
#include<cstdio>
#define read(file) freopen(file,"r",stdin)
#define write(file) freopen(file,"w",stdout)
#else
#define read(file)
#define write(file)
#endif

using namespace std;

int main()
{
	// Uncomment the next line to read test data from a file
	//read("input.txt");
	// Uncomment the next line to write the output to a file
	//write("output.txt");

	// The height and width of the grid
	int n;
	cin >> n;
	int temp = 0;
	int tala = n - 1;
	int exity = (n-1)/2;
	int exitx = n/2;
	int counter = 0;

	for(int y = 0; y < n; y++)
	{
	    if (y == 0)
        {
            for(int x = 0; x < n; x++)
            {
                cout << x << " " << y << endl;
                temp = x;
            }
        }
        else if (y == n - 1)
        {
            for(int x = n - 1; x > 0; x--)
            {
                cout << x << " " << y << endl;
                temp = x;
            }
        }
	}

	return 0;
}
