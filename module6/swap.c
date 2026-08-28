#include <stdio.h> 
 
void swap(int *a, int *b) { 
    int temp = *a;   /* save the value at a */ 
    *a = *b;         /* put b's value into a's location */ 
    *b = temp;       /* put saved value into b's location */ 
} 

void broken_swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
}
 
int main() { 
    int x = 10; 
    int y = 20; 
 
    printf("Before: x = %d, y = %d\n", x, y); 
 
    swap(&x, &y); 
 
    printf("After:  x = %d, y = %d\n", x, y); 

    int brokenX = 10;
    int brokenY = 20;

    printf("Before broken_swap: x = %d, y = %d\n", brokenX, brokenY);
    broken_swap(brokenX, brokenY);
    printf("After broken_swap:  x = %d, y = %d\n", brokenX, brokenY);
 
    return 0;
} 
