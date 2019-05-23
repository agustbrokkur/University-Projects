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

	for(int y = 0; y < 1; y++)
	{
		for(int x = 0; x < n; x++)
		{
			cout << x << " " << y << endl;
		}
	}

	return 0;
}
