#include <stdio.h>
#include <stdlib.h>

int main()
{
    int x[5] = {0, 1, 2, 3, 4};
    short y[3] = {0, 1, 2};

    int a = x[0];
    int b = &x[0];
    int c = x + a + 3;
    int d = *c + 3;
    int e = (x + 2)[2];
    int f = &y[2] - 2;
    int g = *(&y + 1);
    int h = *(x + 5);

    printf("Hello world!\n");
    return 0;
}
