package week_2.day10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class F3_FileReader {
    /*
        we can use fileReader to read character data from the file.
     */

    /*
        Constructors:
        1) FileReader fr = new FileReader(String fName);
        1) FileReader fr = new FileReader(File f);

        Methods:
        1) int read()
            [int is unicode value]
            It attempts to read next character from the file and return its unicode value. If the next character is
            not available then this method returns -1. As method returns unicode value[int value], at the time of
            printing we have to perform type casting.

        2) int read(char[] n)
            It attempts to read n character from the file into char array and returns no. of characters copied from
            the file.

        3) close()

     */

    /*
        By using fileReader we can read data char by char which is not convenient to the programmer.
     */

    /*
        Use of fileWriter and fileReader is not recommended because :
            1) while writing by fileWriter we have to insert line separator(\n) manually which is varied from system to system which is difficult to the programmer.
            2) By using fileReader we can read data by char to char, which is not convenient to the programmer.

            To overcome these problems we should go for bufferedReader and bufferedWriter.
     */

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("abc.txt");
        int i = fr.read();
        while (i != -1) {
            System.out.println((char) i);
            i = fr.read();
        }

        File f = new File("abc.txt");
//        char[] ch = new char[10];
        char[] ch = new char[(int) f.length()];

        FileReader fr2 = new FileReader("abc.txt");
        fr.read(ch);
        for (char chr : ch) {
            System.out.println(chr);
        }

        fr.close();
        fr2.close();

    }
}
