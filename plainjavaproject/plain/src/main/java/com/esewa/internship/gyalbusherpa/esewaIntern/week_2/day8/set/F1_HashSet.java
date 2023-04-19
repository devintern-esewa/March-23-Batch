package week_2.day8.set;

import java.util.HashSet;

public class F1_HashSet {

    /*
        - The underlying data structure is hashtable
        - duplicates are not allowed. If we are trying to insert duplicates, we won't get any compile time or runtime errors. add() method simply returns false.
        - insertion order is not preserved and all objects will be inserted based on hash-code of objects.
        - heterogeneous objects are allowed.
        - 'null' insertion is possible
        - implements Serializable and cloneable interfaces but not RandomAccess
        - hashset is the best choice, if our frequent operation is search operation
     */

    /*
        Constructors:
        // initial capacity = 16 and load factor = 0.75

        HashSet h = new HashSet();

        HashSet h = new HashSet(int initialCapacity);

        HashSet h = new HashSet(int initialCapacity, float loadFactor);

        HashSet h = new HashSet(Collection c);

     */

    public static void main(String[] args) {
        HashSet h = new HashSet();
        h.add("b");
        h.add("c");
        h.add("d");
        h.add("z");
        h.add(null);
        System.out.println(h.add("z"));
        System.out.println(h);
    }
}
