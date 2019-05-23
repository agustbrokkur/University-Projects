#include <iostream>
#include <iomanip>      // std::setprecision

using namespace std;

int main()
{
    int N = 0;
    int counter = 0;
    double scores = 0.;
    double total = 0.;
    double temp = 0.;

    cout << "How many exercises to input: ";
    cin >> N;
    cout << endl;

    if(N <= 0)
    {
        cout << "Input at least one exercise!" << endl;
        return 0;
    }

    do
    {
        counter++;
        cout << "Score received for exercise " << counter << ": ";
        cin >> temp;
        scores = scores + temp;

        cout << "Total points possible for exercise " << counter << ": ";
        cin >> temp;
        total = total + temp;

        cout << endl;
        N--;
    } while(N > 0);

    temp = (scores / total) * 100;
    cout << fixed << setprecision(2) << "Your total is " << scores << " out of " << total << ", or " << temp << "%.";

    return 0;
}
