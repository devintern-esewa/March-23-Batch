package week_2.day8.map;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class F3_HashMapVSHashmap {
    /*
            HashMap                                                     Hashtable
            all method present is not synchronized                      is synchronized
            multiple thread is allowed to operate so not thread         thread-safe
            performance is high                                         performance is low
            null is allowed                                             null is not applicable
            not legacy                                                  is legacy

     */
    public static void main(String[] args) {

        // to make HashMap synchronized:

        HashMap m = new HashMap();
        Map m1 = Collections.synchronizedMap(m);
    }
}
