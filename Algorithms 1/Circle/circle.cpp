#include <iostream>

using namespace std;

class Circle
{
    double radius;

public:

    Circle(double r)
    {
        radius = r;
    }

    void setRadius(double r)
    {
        radius = r;
    }

    double getRadius() const
    {
        return radius;
    }

    double area () const
    {
        return radius * radius * PI;
    }

    double perimeter() const
    {
        return 2 * PI * radius;
    }

    static const double PI = 3.14159;
};

void circleInfo(Circle& circle) {
    cout << "Area: " << circle.area() << endl;
    cout << "Perimeter: " << circle.perimeter() << endl;
}

int main()
{
    double radius;

    cout << "Radius of circle: ";
    cin >> radius;

    Circle circle(radius);
    circleInfo(circle);
    circle.setRadius(circle.getRadius() + 1.0);
    circleInfo(circle);

    return 0;
}
