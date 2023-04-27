package week_2.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class F5_BufferedReader {
    /*

        We can use bufferedReader to read character data from the file.
        The main advantage of br when compared to fr is we can read data line by line in addition to char by char.

        Constructors:
        1) BufferedReader br = new BufferedReader(Reader w);
        2) BufferedReader br = new BufferedReader(Reader w, int bufferSize);

        BufferedWriter can't communicate directly to the file. It can communicate via some writer object.

        Methods:
         1) int read()
         2) int read(char[] n)
         3) close()
         4) String readLine()

     */

    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader("abc.txt");
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line!=null){
            System.out.println(line);
            line = br.readLine();
        }
        br.close();
    }
}
