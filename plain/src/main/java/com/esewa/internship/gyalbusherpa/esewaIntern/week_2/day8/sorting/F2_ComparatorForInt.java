package week_2.day8.sorting;

import java.util.Comparator;
import java.util.TreeSet;

public class F2_ComparatorForInt {
    /*
        1) We can use comparator to define our own sorting(customized sorting)
        2) comparator interface is present in java.util package
        3) It defines two methods -> compare and equals.

            public int compare(Object obj1, Object obj2)
                returns -ve iff obj1 has to come before obj2
                returns +ve iff obj1 has to come after obj2
                returns 0 iff obj1 & obj2 are equal

            public boolean equals();
     */
    /*
        Whenever we are implementing comparator interface, compulsory we should provide implementation for compare()
        method
        And implementing equals() method is optional, because it is already available in every java class from Object
        class through inheritance.
     */

    public static void main(String[] args) {

//        // For default natural sorting (jvm will call compareTo() method)
//        TreeSet t = new TreeSet();
//        t.add(10);
//        t.add(0);
//        t.add(15);
//        t.add(20);
//        t.add(22);
//        System.out.println(t);

        // For customized (descending order) (jvm will call compare() method)

        TreeSet t = new TreeSet(new myComparatorString());
        t.add(10);
        t.add(0);
        t.add(15);
        t.add(20);
        t.add(22);
        System.out.println(t);
    }
}

class myComparator implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Integer i = (Integer) o1;
        Integer  i2 = (Integer) o2;

//        if (i < i2) {
//            return +1;
//        }
//        else if (i > i2) {
//            return -1;
//        }
//        else {
//            return 0;
//        }

        /*
        shortcuts:

            return i.compareTo(i2); [0,10,15,20] for ascending order
            return -i2.compareTo(i); [0,10,15,20] for ascending order
            return -i.compareTo(i2); [0,10,15,20] for descending order
            return i2.compareTo(i); [0,10,15,20] for descending order
         */

        /*
            return +1; [10,0,15,20,20] for insertion order
            return -1; [20,20,15,0,10] for reverse of insertion order
            return 0; [0] -> only first element will be inserted.
         */

        return i2.compareTo(i);
    }
}
