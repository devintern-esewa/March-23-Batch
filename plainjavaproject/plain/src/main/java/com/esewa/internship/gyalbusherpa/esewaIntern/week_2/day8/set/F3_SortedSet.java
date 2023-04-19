package week_2.day8.set;

import java.util.SortedSet;

public class F3_SortedSet {
    /*
        It is the child interface of set
        If we want to represent a group of individual objects according to some sorting order and duplicates are not
        allowed then we should go for SortedSet
     */

    /*
        methods:

        first()
        last()
        headSet(Object o) -> less than o
        tailSet(Object o) -> greater than 0
        subSet(103,110) -> greater than 103 and less than 110

        comparator() -> returns comparator object that describes underlying sorting technique. If we are using
        default natural sorting order then we will get null
     */

    /*
        default natural sorting order
        - for numbers = ascending
        - for strings = alphabetical order
     */

    public static void main(String[] args) {

    }
}
