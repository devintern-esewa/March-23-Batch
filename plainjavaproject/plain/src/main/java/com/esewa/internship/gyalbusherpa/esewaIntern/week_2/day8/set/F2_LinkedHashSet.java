package week_2.day8.set;

import java.util.LinkedHashSet;

public class F2_LinkedHashSet {
    /*
          hashSet                   LinkedHashSet
          hashtable                 hashtable + linkedList
          insertion not preserved   insertion is preserved

     */

    /*
            linkedHashSet is the best choice to develop cache based applications, where duplicates are not allowed
            and insertion order must be preserved.
     */

    public static void main(String[] args) {

        LinkedHashSet h = new LinkedHashSet();
        h.add("b");
        h.add("c");
        h.add("d");
        h.add("z");
        h.add(null);
        System.out.println(h.add("z"));
        System.out.println(h);

    }
}
