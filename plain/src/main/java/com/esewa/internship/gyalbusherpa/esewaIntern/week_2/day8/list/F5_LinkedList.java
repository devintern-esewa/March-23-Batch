package week_2.day8.list;

import java.util.LinkedList;

public class F5_LinkedList {

    /*
        The underlying data structure is Double Linked List
        Insertion order is preserved
        Duplicates are allowed
        Heterogeneous Objects are allowed
        Null insertion is possible
     */

    /*
        LinkedList implements Serializable and Cloneable interfaces but not RandomAccess Interface
        LinkedList is the best choice if our frequent operation is insertion or deletion in the middle.
        LinkedList is the worst choice if our frequent operation is retrieval operation.
     */

    /*
    Usually we can use LinkedList to implement stacks and queues to provide support for this requirement LinkedList
    class defines following specific methods.
        void addFirst(Object o)
        void addLast(Object o)
        Object getFirst()
        Object getLast()
        Object removeLast()
        Object removeFirst()
     */

    /*
    Constructors

        LinkedList l = new LinkedList();
        // creates an empty LinkedList Object

        LinkedList l = new LinkedList(Collection c);
        // creates an equivalent LinedList object for the given collection
     */

    public static void main(String[] args) {
        LinkedList<Object> l = new LinkedList<>();
        l.add("don");
        l.add(30);
        l.add(null);
        l.add("don");
        System.out.println(l);
        l.set(0,"soft");
        l.set(0,"vn");
        System.out.println(l);
        l.removeLast();
        System.out.println(l);
        l.addFirst("cat");
        System.out.println(l);

    }

}
