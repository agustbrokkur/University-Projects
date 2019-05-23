#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

struct Chess
{
    string nafn;
    int ar;
    int stig;
};

void lesaInn(vector<Chess>& spilarar);
void lesaUt(vector<Chess>& spilarar);
void haMedal(vector<Chess>& spilarar);
void meTal(vector<Chess>& spilarar);
bool beraSaman(const Chess &a, const Chess &b);
int stoP();

int main()
{
    vector<Chess> spilarar;

    lesaInn(spilarar);
    lesaUt(spilarar);
    haMedal(spilarar);
    return 0;
}

void lesaInn(vector<Chess>& spilarar)
{
    int fjoldi = 0;
    cout << "Number of players: ";
    cin >> fjoldi;


    cout << "--- Reading players ---" << endl;
    for (int i = 0; i < fjoldi; i++)
    {
        Chess spilari;
        cout << "Name: ";
        cin >> spilari.nafn;
        cout << "Year: ";
        cin >> spilari.ar;
        cout << "Rating: ";
        cin >> spilari.stig;
        cout << endl;
        spilarar.push_back(spilari);
    }
}

void lesaUt(vector<Chess>& spilarar)
{
    cout << "--- Displaying players --- " << endl;
    for (size_t i = 0; i < spilarar.size(); i++)
    {
        cout << "Name: " << spilarar[i].nafn << endl;
        cout << "Year: " << spilarar[i].ar << endl;
        cout << "Rating: "<< spilarar[i].stig << endl;
        cout << endl;
    }
}

void haMedal(vector<Chess>& spilarar)
{
    sort (spilarar.begin(), spilarar.end(), beraSaman);
    cout << "Highest rated player: " << endl;
    cout << "Name: " << spilarar[0].nafn << endl;
    cout << "Year: " << spilarar[0].ar << endl;
    cout << "Rating: "<< spilarar[0].stig << endl;

    meTal(spilarar);
}

void meTal(vector<Chess>& spilarar)
{
    double temp = 0.0;
    int div = 0;
    for (size_t i = 0; i < spilarar.size(); i++)
    {
        temp = temp + spilarar[i].stig;
        div++;
    }
    temp = temp / div;
    cout << endl << "Average rating: " << temp << endl;
}

bool beraSaman(const Chess &a, const Chess &b)
{
    return a.stig > b.stig;
}
