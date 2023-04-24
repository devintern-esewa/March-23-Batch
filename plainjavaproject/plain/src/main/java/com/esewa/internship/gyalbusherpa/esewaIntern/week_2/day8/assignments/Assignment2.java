package week_2.day8.assignments;

import java.util.TreeSet;

public class Assignment2 {
    public static void main(String[] args) {
        Books b1 = new Books("Jungle Book", 5000);
        Books b2 = new Books("Jaws", 1000);
        Books b3 = new Books("X", 52001);
        Books b4 = new Books("Eve", 25009);
        Books b5 = new Books("Glitch", 5103);
        Books b6 = new Books("Love", 6969);
        Books b7 = new Books("Sing", 501);

        // for natural sorting order
        TreeSet<Books> ts = new TreeSet<>();
        ts.add(b1);
        ts.add(b2);
        ts.add(b3);
        ts.add(b4);
        ts.add(b5);
        ts.add(b6);
        ts.add(b7);
        System.out.println(ts);

        // for custom sorting order
        TreeSet<Books> ts2 = new TreeSet<>(new BooksDecending());
        ts2.add(b1);
        ts2.add(b2);
        ts2.add(b3);
        ts2.add(b4);
        ts2.add(b5);
        ts2.add(b6);
        ts2.add(b7);
        System.out.println(ts2);

    }
}
