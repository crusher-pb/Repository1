#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
    char ch1[20];
    printf("Enter the string:- \n");
    scanf("%s",ch1);
    char ch2[20];
    strcpy(ch2,ch1);
    printf("\nThe string reversed using string function is:- ");
    printf("%s\n",strrev(ch1));
    int i;
    for (i=0;i<strlen(ch2)/2;i++)
    {
        char temp;
        temp=ch2[i];
        ch2[i]=ch2[strlen(ch2)-1-i];
        ch2[strlen(ch2)-1-i]=temp;
    }
    printf("\nThe string reversed without using string function is:- ");
    printf("%s",ch2);
    return 0;
}
