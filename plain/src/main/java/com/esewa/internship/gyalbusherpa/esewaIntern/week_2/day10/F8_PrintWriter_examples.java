package week_2.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class F8_PrintWriter_examples {
    /*
        Write a program to perform file extraction operation.
        222         555
        888         888
        333         222
        444
        555

        output
        333
        444

     */
    public static void main(String[] args) throws IOException {
        /*
        PrintWriter pw = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10\\output.txt");

        BufferedReader read = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src" +
                "\\week_2\\day10\\input.txt"));
        String reading = read.readLine();


        while(reading!=null){
            boolean available = false;
            BufferedReader delete = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src" +
                    "\\week_2\\day10\\z.txt"));
            String deleting = delete.readLine();

            while(deleting!=null){
                if(reading.equals(deleting)){
                    available = true;
                    break;
                }
                deleting = delete.readLine();
            }
            if(!available){
                pw.println(reading);
            }
            reading = read.readLine();
        }

        pw.flush();
        read.close();
        pw.close();

         */

        // write a java program to remove duplicate from the given input file.

        PrintWriter pw = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10\\noDuplicates.txt");
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10\\output.txt"));

        String line = br.readLine();
        while(line!=null){
            boolean available = false;
            BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10\\noDuplicates.txt"));
            String target = br2.readLine();
            while(target!=null){
                if(line.equals(target)){
                    available = true;
                    break;
                }
                target = br2.readLine();
            }
            if(!available){
                pw.println(line);
                pw.flush();
            }
            line = br.readLine();

        }

        br.close();
        pw.close();

    }
}
