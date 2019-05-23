#include <iostream>
#include <vector>
#include <fstream>
#include <string>

using namespace std;

class Sales
{
    vector<double> data;

public:

    Sales(vector<double> d)
    {
        data = d;
    }

    double getAverage()
    {
        double x = 0.0;
        for(size_t i = 0; i < data.size(); i++)
        {
            x = data[i] + x;
        }
        return x / data.size();
    }

    void addSales(double d)
    {
        data.push_back(d);
    }

};

void readSales(vector<double>& data)
{
    double tala = 0.0;
    ifstream sales ("sales.data");
    sales.open("sales.dat");
    if (sales.is_open())
    {
        while (sales >> tala)
        {
            data.push_back(tala);
        }
        sales.close();
    }
    else
    {
        cout << "Unable to open file";
    }
}

int main()
{
    vector<double> data;
    readSales(data);
    Sales sales(data);

    cout.setf(ios::fixed);
    cout.setf(ios::showpoint);
    cout.precision(2);

    cout << "Average sales: " << sales.getAverage() << endl;
    sales.addSales(78.5);
    cout << "Average sales: " << sales.getAverage() << endl;

    return 0;
}