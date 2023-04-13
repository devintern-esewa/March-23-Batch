package week_2.day8.map;

import java.util.Hashtable;

public class F7_Hashtable {
    /*
        - The underlying ds of hashtable is hashtable
        - insertion order is not preserved.
        - it is based on hashcode of keys
        - duplicate keys are not allowed but values are allowed
        - heterogeneous obj are allowed for both keys and values
        - null is not allowed for both keys and values.
        - implements Serializable and Cloneable interfaces but not RandomAccess
        - every method in hashtable is synchronized and hence, hashtable object is thread safe
        - hashtable is best if our frequent operation is search operation.
     */

    /*
        Constructors

        1) Hashtable h = new Hashtable()
                    initial capacity = 11
                    fill ratio/load factor = 0.75

        2) Hashtable h = new Hashtable(int initialCapacity)

        3) Hashtable h = new Hashtable(int initialCapacity, float loadFactor)

        4) Hashtable h = new Hashtable(Map m)

     */
    public static void main(String[] args) {
//        Hashtable h = new Hashtable();
        // change in initial capacity
        Hashtable h = new Hashtable(25);
        h.put(new Temp(5),"A");
        h.put(new Temp(2),"B");
        h.put(new Temp(6),"C");
        h.put(new Temp(15),"D");
        h.put(new Temp(23),"E");
        h.put(new Temp(16),"F");
        System.out.println(h);
    }

}

class Temp {
    int i;

    Temp(int i) {
        this.i = i;
    }

    public int hashCode() {
        // change in hashCode formula
        // return i;
        return i%9;
    }

    public String toString() {
        return i + "";
    }
}