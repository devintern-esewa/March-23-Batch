package week_2.java1point8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

interface Int {
    public int square(int n);
}

//class MyRunnable implements Runnable{
//
//    @Override
//    public void run() {
//        for (int i = 0; i < 10; i++) {
//            System.out.println("child");
//        }
//    }
//}
public class F3_LE_Multithreading_Collections {
    public static void main(String[] args) {
//        Int i = n->n*n;
//        System.out.println(i.square(8));

//        Runnable r = ()->{
//            for (int i = 0; i < 10; i++) {
//                System.out.println("child");
//            }
//        };
//        Thread t= new Thread(r);
//        t.start();
//        for (int i = 0; i < 10; i++) {
//            System.out.println("main");
//        }

        ArrayList<Integer> l = new ArrayList<>();
        l.add(53);
        l.add(533);
        l.add(13);
        l.add(33);
        l.add(573);
        l.add(538);
        l.sort((o1, o2) -> {
            return (o1 < o2) ? -1 : (o1 > o2) ? 1 : 0;
        });
        System.out.println(l);

        // legend code
        List<Integer> l2 = l.stream().filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(l2);
    }
}
