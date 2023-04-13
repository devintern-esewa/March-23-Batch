package week_2.day10;

import java.io.File;
import java.io.IOException;

public class F1_FileIntro {
    public static void main(String[] args) throws IOException {
        File f = new File("don.txt"); // won't create physical file only java file object
//        System.out.println(f.exists()); // false

//        f.createNewFile();
//        System.out.println(f.exists());

        /*
        We can use java file object to represent directory also.
         */
        File f2 =  new File("hero");
//        f2.mkdir();
//        System.out.println(f.exists());
        /*
        In unix everything is treated as a file.
        Java file I/O concept is implemented based on UNIX operating system.
        Hence, java file object can be used to represent both files and the directories.

         */

        /*
            File Class constructors:

            1) File f = new File(String name); -> current directory
            // WAP to create file named abc.txt in current working directory

            2) File f = new File(String subDirectory, String name); -> in specified sub location
            // make folder a then make a.txt in there.

            File f = new File("D:\\xyz","a.txt");
            f.createNewFile();

            3) File f = new File(File subDir, String name);

         */

        /*
            methods:

            f.exists()
            f.createNewFile()
            f.mkdir()
            f.isFile()
            f.isDirectory()
            f.list() -> return type is String[] array
            f.length() -> gives no. of character// return type is long
            f.delete();


         */

        int count = 0;
        File f5 = new File("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern");
        String[] s = f5.list();
        for(String s1: s){
            count++;
//            System.out.println(s1);
        }
//        System.out.println("The total number: " + count);


        // Display only file name
        int count2 = 0;
        File f15 = new File("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern");
        String[] s2 = f15.list();
        for(String s1: s2){
            File f12 = new File(f15,s1);
//            if(f12.isFile()){
            if(f12.isDirectory()){
                count2++;
                System.out.println(s1);
            }
        }
        System.out.println("The total number: " + count2);

    }
}
