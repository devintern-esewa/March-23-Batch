package week_2.day8.list;

import java.util.ArrayList;

public class F3_ArrayList {

    /*
    - Resizable array or growable array
    - duplicates are allowed
    - insertion order is preserved
    - heterogeneous objects allowed (except treeSet and treeMap)
    - null insertion is possible.
     */

    /*
        Constructor:

        1) ArrayList l = new ArrayList();
        // default capacity = 10
        // new capacity = (cc * 3/2) + 1;

        2) ArrayList l = new ArrayList(int initialCapacity);

        3) ArrayList l = new ArrayList(Collection c);

     */

    /*
    Usually we can use Collections to hold and transfer Objects from one place to another place, to provide support
    for this requirement every Collection already implements Serializable and Cloneable interfaces.
     */

    /*

    ArrayList and Vector classes implements RandomAccess interface so that we can access any Random element with the
    same speed.
    Hence, is our frequent operation is retrieval operation then ArrayList is the best choice.

    Random Access
    - Present in java.util package
    - It doesn't contain any methods, and it is Marker interface
     */

    /*
    ArrayList is the best choice if our frequent operation is retrieval operation(Because ArrayList implements
    RandomAccess Interface)
    ArrayList is the worst choice if our frequent operation is insertion of deletion in the middle (Because
    several shift operation is required)
    */


    public static void main(String[] args) {

        ArrayList<Object> l = new ArrayList<>();
        l.add("A");
        l.add("ball");
        l.add("A");
        l.add(10);
        l.add(null);
        System.out.println(l);
        l.remove(0);
        System.out.println(l);
        l.add(0, "Don");
        System.out.println(l);
        l.add("f");
        System.out.println(l);

//        System.out.println(l instanceof Serializable); true
//        System.out.println(l instanceof Cloneable); true
//        System.out.println(l instanceof RandomAccess); true
    }
}
