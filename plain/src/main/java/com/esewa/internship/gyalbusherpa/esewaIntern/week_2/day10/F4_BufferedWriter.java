package week_2.day10;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class F4_BufferedWriter {
    /*
        We can use bufferedWriter to write char data to the file.

        Constructors:

        1) BufferedWriter bw = new BufferedWriter(Writer w);
        2) BufferedWriter bw = new BufferedWriter(Writer w, int bufferSize);

        BufferedWriter can't communicate directly to the file. It can communicate via some writer object.

        BW bw = new BW("abc.txt"); not valid
        BW bw = new BW(new File("abc.txt")); not valid
        BW bw = new BW(new FW("abc.txt")); -> valid
        BW bw = new BW(new BW(new FW("abc.txt"))); -> valid (Two-level Buffering)

        Methods:
        1) write(int ch)
        2) write(char[] ch)
        3) write(String s)
        4) flush()
        5) close()
        6) newLine() -> To insert a line separator
     */
    /*
        Whenever we close bw, automatically internally fw is closed.
     */
    public static void main(String[] args) throws IOException {
        FileWriter f2 = new FileWriter("abc.txt");
        BufferedWriter bw = new BufferedWriter(f2);
        bw.write(100); // addinga  singlel char
        bw.newLine();
        char[] c = {'a', 'b'};
        bw.write(c);
        bw.newLine();
        bw.write("don");
        bw.newLine();
        bw.write("i am don");
        bw.flush();
        bw.close();
    }
}
