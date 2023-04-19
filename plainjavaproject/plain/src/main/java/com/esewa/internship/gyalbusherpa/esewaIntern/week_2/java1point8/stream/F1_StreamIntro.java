package week_2.java1point8.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class F1_StreamIntro {

    /*
        stream()
        filter()
        map()
        collect()
        sorted()
        sorted(Comparator)
        min(Comparator)
        max(Comparator)
        forEach()
        toArray()

        Stream.of().
     */

    public static void main(String[] args) {
        ArrayList<Integer> l = new ArrayList<>();
        l.add(90);
        l.add(72);
        l.add(5);
        l.add(7);
        l.add(82);

//        System.out.println(l);
        List<Integer> l2 = l.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        List<Integer> l3 = l.stream().map(i -> i + 5).collect(Collectors.toList());
//        System.out.println(l2);
//        System.out.println(l3);

        long noOfFailedStudent = l.stream().filter(i->i<40).count();

        List<Integer> i = l.stream().sorted(((o1, o2) -> (o1<o2)?+1:(o1>02)?-1:0)).collect(Collectors.toList());
        List<Integer> i2 = l.stream().sorted().collect(Collectors.toList());
//        System.out.println(i);
//        System.out.println(noOfFailedStudent);

        Integer min = l.stream().min(Integer::compareTo).get();
//        System.out.println(min);

        Integer max = l.stream().max(Integer::compareTo).get();
//        System.out.println(max);

//        l.stream().forEach(System.out::println);

        // to convert stream of objects into array

        Integer[] arr = l.stream().toArray(Integer[]::new);
        Stream.of(arr).forEach(System.out::println);
    }

}
