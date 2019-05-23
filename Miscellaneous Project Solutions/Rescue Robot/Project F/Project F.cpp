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
	int starty = (n/2);
	int startx = ((n-1)/2);

	for(int z = 0; z < n; z++)
    {
        for(int y = starty; y < n; y++)
        {
            y + z;
            for(int x = startx; x < n; x++)
            {
                x + z;
                cout << x << " " << y << endl;
            }
        }
        if (z < 0)
        {
            z = -z;
        }
    }

	return 0;
}
