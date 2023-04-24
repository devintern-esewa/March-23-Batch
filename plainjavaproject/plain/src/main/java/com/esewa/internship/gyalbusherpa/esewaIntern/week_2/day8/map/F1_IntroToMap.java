package week_2.day8.map;

import java.util.HashMap;

public class F1_IntroToMap {
    /*

        Map is not the child interface of Collection
        If we want to represent a group of individual objects as key value pairs then we should go for map.

        Both key are value are objects, duplicated keys are not allowed but values can be duplicate.

        Each key value pair is called "Entry". Hence, map is consider as a collection of entry objects.

        Map has its own methods since it is not related to Collection interface. Methods of Collection is not
        applicable in map.

        Map interface methods:
            1) Object put(Object key, Object value)
                - to add one key value pair to the map
                - if the key is already present then the old value will be replaced with new value.
                eg. m.put(101,"don");
                    m.put(102,"hero");
                    m.put(101,"zo");

                    101 is replaced with value zo.

            2) void putAll(Map m); -> group of key value pair is added.
            3) Object get(Object key); -> we'll get corresponding value. If key is not there then we get null as answer.
            4) Object remove(Object key); -> removes the entry associated with the key.
            5) boolean containsKey(Object key); -> checks weather the particular key is present or not.
            6) boolean containsValue(Object key); -> checks weather the particular key is present or not.
            7) int size(); -> gives the size of map
            8) boolean isEmpty(); -> checks map is empty or not
            9) void clear(); -> clears all entries
     */

    /*
        SortedMap

        It is the child interface of map
        If we want to represent a group of key value pairs according to some sorting order of keys then we should go
        for sortedmap.

        NavigableMap
        It is child interface of sorted map, it defines several utility methods for navigation purpose.

        TreeMap is the child of NavigableMap.
     */

    /*
        Collection view of map

        1) Set keySet() -> gets all keys only

            example:
                HashMap h = new HashMap();
                h.put("101", "don");
                h.put("102", "hero");
                h.put("103", "don");

                Set s = h.keySet();
                Iterator i = s.iterator();
                while(i.hasNext()){
                    System.out.println(i.next()); // 101,102,103
                }

        2) Collection values() -> gets all values only
        example:
                HashMap h = new HashMap();
                h.put("101", "don");
                h.put("102", "hero");
                h.put("103", "don");

                Collection c = h.values();
                Iterator i2 = c.iterator();
                while(i2.hasNext()){
                    System.out.println(i2.next()); // don, hero, don
                }

        3) Set entrySet() -> gets both key and value.
        example:
                HashMap h = new HashMap();
                h.put("101", "don");
                h.put("102", "hero");
                h.put("103", "don");

                Set st = h.keySet();
                Iterator i3 = st.iterator();
                while(i3.hasNext()){
                    Map.Entry m = (Map.Entry)i3.next();
                    System.out.println(m.getKey() + " " + m.getValue());
                }

     */

    /*

            Entry(I) interface is defined inside of Map interface.

            interface Map{
                interface Entry{
                // these methods can be only applied in Entry object.
                    Object getKey()
                    Object getValue()
                    Object setValue(Object value)
                }
            }

     */
    public static void main(String[] args) {




    }
}
