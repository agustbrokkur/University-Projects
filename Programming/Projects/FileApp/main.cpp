#include <iostream>
#include <fstream>

using namespace std;

struct Point
{
    int x;
    int y;
};

Point* createPoint(int x, int y)
{
    Point* p = new Point();
    p->x = x;
    p->y = y;
    delete p;
    return p;
}

/*void printPoint(ostream& out, const Point& p)
{
    out << p.x << "," << p.y << endl;
}*/

ostream& operator << (ostream& out, const Point& p)
{
    out << p.x << "," << p.y << endl;
    return out;
}

int main(int argc, char* argv[])
{
    Point p;
    p.x = 10;
    p.y = 20;
    Point* pPoint = createPoint(45, 67);

//    printPoint(cout, p);
    cout << p;

    ofstream pointFile;
    pointFile.open("C:\\Temp\\Point.txt");
    if (pointFile)
    {
 //       printPoint(pointFile, p);
        pointFile << p;
        pointFile.close();
    }

    delete pPoint;

    return 0;
}
