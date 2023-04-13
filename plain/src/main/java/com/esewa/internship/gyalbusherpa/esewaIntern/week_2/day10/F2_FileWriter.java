package week_2.day10;

import java.io.FileWriter;
import java.io.IOException;

public class F2_FileWriter {
    /*
        We can use fileWriter to write character data to the file

     */

    /*
        Constructors:

        FileWriter f2 = new FileWriter(String fName);

        FileWriter f2 = new FileWriter(File f);

        // The above file writer are meant for overRiding of data.
        // if you want to append then we have to create file writer by using the below constructor.

        FileWriter f2 = new FilWriter(String fName, boolean append);

        FileWriter f2 = new FileWriter(File f, boolean append);

        If the specified file is not already available then all the above constructors will create the file.
     */

    /*
            methods:

            1) write(int ch) -> To write a single character to the file.
            2) write(char[] ch) -> To write an array of characters
            3) write(String s) -> To write string to the file

            4) flush() -> only for writer. To give the guarantee that total data including last character will be
            written to the file.
            5) close() -> to close the writer.

     */
    /*

        The main problem with the file writer is we have to insert line separator manually which is varied from
        system to system. It is difficulty to the programmer. We can solve this problem using bufferedWriter and the
        printWriter classes.
     */
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("abc.txt");
//        FileWriter fw = new FileWriter("abc.txt",true); -> for appending
        fw.write(100); // addinga  singlel char
        fw.write("don\nHero");
        fw.write('\n');
        char[] ch1 = {'a', 'b', 'c'};
        fw.write(ch1);
        fw.write('\n');
        fw.flush();
        fw.close();

    }
}
