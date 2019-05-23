#include <iostream>
using namespace std;

const int MAX_SIZE = 20;

// Fills the array a[] with data from the user for a[0] through a[numberUsed -1].
// Since the user will not necessarily use up all the allocated entries in the array,
// the function will set the actual number of entries used in the "numberUsed"
// reference variable.
void fillArray(int a[], int arraySize, int& numberUsed);

// Sorts the array a[] such that a[0] <= a[1] <= ... <= a[numberUsed - 1].
void sortArray(int a[], int numberUsed);

// Interchanges the values of v1 and v2.
void swapValues(int& v1, int& v2);

// Returns the index i such that a[i] is the smallest of the values
// a[startIndex], a[startIndex + 1], ..., a[numberUsed - 1].
int indexOfSmallest(const int a[], int startIndex, int numberUsed);

// Displays the contents of the array
void displayArray(const int a[], int numberUsed);


int main( )
{
	//cout << "This program sorts numbers from lowest to highest.\n";

	int sampleArray[MAX_SIZE] = {0};
	int numberUsed = 0;

	fillArray(sampleArray, MAX_SIZE, numberUsed);
	sortArray(sampleArray, numberUsed);

	cout << "In sorted order the numbers are:\n";
	displayArray(sampleArray, numberUsed);

	return 0;
}

void fillArray(int a[], int arraySize, int& numberUsed)
{
	cout << "Enter up to " << arraySize << " nonnegative whole numbers.\n"
	 << "Mark the end of the list with a negative number.\n";
	int next  = 0;
	int index = 0;

	cin >> next;

	while ((next >= 0) && (index < arraySize))
	{
		a[index] = next;
		index++;
		cin >> next;
	}

	numberUsed = index;
}

void sortArray(int a[], int numberUsed)
{
	//int indexOfNextSmallest;
	for (int index = 0; index < numberUsed -1; index++)
	{
		// Place the correct value in a[index]:
		//indexOfNextSmallest = indexOfSmallest(a, index, numberUsed);
        //swapValues(a[index], a[indexOfNextSmallest]);
		// a[0] <= a[1] <=...<= a[index] are the smallest of the original array
		// elements. The rest of the elements are in the remaining positions.

        if (a[index] > a[(index + 1)])
        {
            swapValues(a[index + 1], a[index]);
        }
        for (int x = index; x > 0; x--)
        {
            if (a[x] < a[(x - 1)])
            {
                swapValues(a[x -1 ], a[x]);
            }
        }


        displayArray(a, numberUsed);
	}
}

/*
    for (int index = 0; index < numberUsed; index++)
	{
		cout << a[index] << " ";
	}

	cout << endl;
*/

void swapValues(int& v1, int& v2)
{
	int temp;
	temp = v1;
	v1   = v2;
	v2   = temp;
}

int indexOfSmallest(const int a[], int startIndex, int numberUsed)
{
	int min = a[startIndex];
	int indexOfMin = startIndex;

	for (int index = startIndex + 1; index < numberUsed; index++)
	{
		if (a[index] < min)
		{
			min = a[index];
			indexOfMin = index;
			// min is the smallest of a[startIndex] through a[index]
		}
	}

	return indexOfMin;
}

void displayArray(const int a[], int numberUsed)
{
	for (int index = 0; index < numberUsed; index++)
	{
		cout << a[index] << " ";
	}

	cout << endl;
}
