package week_2.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class f7_PrintWriter_merge {
    /*
        In general, we can use readers and writers to handle char data(txt data) whereas we can stream to handle
        binary data(like images, pdf, video, audio, etc.)
        We can use output stream to write binary data to the file, input stream to read binary data from the file.
     */

    /*
        Write a program to merge data from 2 file to 3rd file.
        Write a program to perform file merge operation where merging should be done line by line alternatively.
     */
    public static void main(String[] args) throws IOException {
        /*
        PrintWriter pw = new PrintWriter("file.txt");

        BufferedReader br = new BufferedReader(new FileReader("file1.txt"));
        String line = br.readLine();
        while (line != null) {
            pw.println(line);
            line = br.readLine();
        }

        br = new BufferedReader(new FileReader("file2.txt"));
        String line2 = br.readLine();
        while (line2 != null) {
            pw.println(line2);
            line2 = br.readLine();
        }

        pw.flush();
        br.close();
        pw.close();

         */

        PrintWriter pw = new PrintWriter("file3.txt");
        BufferedReader br1 = new BufferedReader(new FileReader("file1.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("file2.txt"));

        String line1 = br1.readLine();
        String line2 = br2.readLine();

        while (line1 != null || line2 != null) {
            if (line1 != null) {
                pw.println(line1);
                line1 = br1.readLine();
            }
            if (line2 != null) {
                pw.println(line2);
                line2 = br2.readLine();
            }
        }

        pw.flush();
        br1.close();
        br2.close();
        pw.close();
    }
}
