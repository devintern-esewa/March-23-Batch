package week_2.day8.assignments;

import java.util.Iterator;
import java.util.LinkedList;

public class Assignment1 {
    public static void main(String[] args) {
        Books b1 = new Books("Jungle Book", 5000);
        Books b2 = new Books("Jaws", 1000);
        Books b3 = new Books("X", 52001);
        Books b4 = new Books("Eve", 25009);
        Books b5 = new Books("Glitch", 5103);
        Books b6 = new Books("Love", 6969);
        Books b7 = new Books("Sing", 501);

        LinkedList<Books> liList = new LinkedList<>();
        liList.add(b1);
        liList.add(b2);
        liList.add(b3);
        liList.add(b4);
        liList.add(b5);
        liList.add(b6);
        liList.add(b7);

        Iterator<Books> i = liList.iterator();
        while (i.hasNext()) {
            Books b = i.next();
            if (b.price % 2 == 0) {
                i.remove();
            }
            System.out.println(b);
        }

        liList.remove(b7);
        System.out.println(liList);
    }
}
