/*README
**Author Jack Webb 2020-09-22
**Last updated
**
**
**
**
*/

#include <stdio.h>
#include <ctype.h>



/*A function which takes an input and output file and copies the contents
**of the input file to the output file with the exception of non alphabet
**characters (but including newline and blankspace). It also replaces these
**characters with blankspace.
*/
void filter(FILE *input, FILE *output)
{
    //Store the character being inspected
    char c;
    //Checks if it has read the whole file or not
    while(!feof(input))
    {
        //Store the character being inspected
        c = fgetc(input);
        //Checks if it the character is in the alphabet or a newline
        if(isalpha(c) || c == '\n')
        {
            //Puts the character in the output if in alphabet or newline
            fputc(c, output);
        }
        else
            fputc(' ', output); //Replaces the rest with a blankspace
    }
}







int main(int argc, char const *argv[])
{
    FILE *p_file_read;
    FILE *p_file_write;

    p_file_read  = fopen("thetext.txt", "r");
    p_file_write = fopen("newtext.txt", "w");

    filter(p_file_read, p_file_write);

    fclose(p_file_read);
    fclose(p_file_write);
    return 0;
}
