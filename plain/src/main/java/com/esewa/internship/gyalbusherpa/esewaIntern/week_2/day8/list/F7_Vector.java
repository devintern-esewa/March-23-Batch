package week_2.day8.list;

import java.util.Vector;

public class F7_Vector {

    /*
        - The underlying data structure for the vector is resizable or growable array
        - Duplicate objects are allowed
        - Insertion order is preserved
        - Null insertion is possible
        - Heterogeneous objects are allowed
        - Vector class implements Serializable, Cloneable and RandomAccess Interfaces.
        - Most of the methods present in Vector are synchronized. Hence, Vector object is Thread-safe.
        - Best choice if the frequent operation is retrieval.
     */

    /*  methods():

        add(Object o)               -- collection
        add(int index, Object o)    -- list
        addElement(Object o)        -- Vector

        remove(Object o)            - c
        removeElement(Object o)     - v
        remove(int index)           - l
        removeElementAt(int index)  - v
        clear()                     - c
        removeAllElements()         - v

        // for accessing elements:
        Object get(int index)       - c
        Object elementAt(int index) - v
        Object firstElement()       - v
        Object lastElement()        - v

        // other methods:
        int size();
        int capacity();
        Enumeration elements();
     */

    /*
    Constructors

        Vector v = new Vector();
        // default initial capacity 10
        // new capacity = 2 * cc

        Vector v = new Vector(int initialCapacity)

        Vector v = new Vector(int initialCapacity, int incrementalCapacity)

        Vector v = new Vector(Collection c)
        



     */

    public static void main(String[] args) {
//        Vector<Integer> v = new Vector<>(25);
//        Vector<Integer> v = new Vector<>(25,50);
        Vector<Integer> v = new Vector<>();
        System.out.println(v.capacity());
        for (int i = 0; i < 10; i++) {
                v.addElement(i);
        }
        System.out.println(v.capacity());
        v.addElement(500);
        System.out.println(v.capacity());
        System.out.println(v);
    }
}
