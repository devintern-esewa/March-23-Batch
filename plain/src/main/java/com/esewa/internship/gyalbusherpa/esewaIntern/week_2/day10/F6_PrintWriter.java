package week_2.day10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class F6_PrintWriter {
    /*
        It is the most enhanced writer to write character data to the file.
        The main advantage of print writer over fileWriter and bufferedWriter is we can write any type of primitive
        data directly to the file.

        Constructors:

        1) PrintWriter pw = new PrintWriter(String fName);
        2) PrintWriter pw = new PrintWriter(File f);
        3) PrintWriter pw = new PrintWriter(Writer w);

        Methods:

        1) write(int ch)
        2) write(char[] ch)
        3) write(String s)
        4) flush()
        5) close()

        6) print(char ch);
        7) print(int i);
        8) print(double d);
        9) print(boolean b);
        10) print(String s);

     */
    /*
        The most enhanced writer to write char data to the file is PrintWriter.
        whereas the most enhanced reader to read char data from the file is BufferedReader.
     */
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("abc.txt");
        PrintWriter out = new PrintWriter(fw);
        out.write(100); // -> char d is added
        out.println(100); // -> int 100 is added
        out.println(true);
        out.println('c');
        out.println("don");
        out.flush();
        out.close();

    }
}
