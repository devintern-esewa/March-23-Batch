package week_2.day8.map;

public class F4_SortedMap {

    /*
        - It is the child interface of map
        - If we want to represent the group of object as group of key value pair according to some sorting order of
          key then we should go for sortedMap
        - Sorting is based on key but not based on value.

        methods:

        Object firstKey()
        Object lastKey()
        SortedMap headMap(Object key) -> less than key
        SortedMap tailMap(Object key) -> greater than key
        SortedMap subMap(103,110) -> greater than 103 and less than 110

        Comparator comparator() -> returns comparator object that describes underlying sorting technique. If we are using
        default natural sorting order then we will get null

     */

}
