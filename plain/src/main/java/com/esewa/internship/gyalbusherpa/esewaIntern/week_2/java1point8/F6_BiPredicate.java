package week_2.java1point8;

import java.util.function.BiPredicate;

public class F6_BiPredicate {
    /*
    Normal predicate can take only one input argument and perform some conditional check.
    Sometimes our programming requirement is we have to take 2 input arguments and perform some conditional check, for this requirement we should gof ro BiPredicate.

    BiPredicate is exactly same as predicate except that it will take 2 input argument.

    interface BiPredicate<T1,T2>
    {
        public boolean test(T1 t1, T2 t2);
        // remaining default and static methods are same
    }

     */
    public static void main(String[] args) {
        // Program to check two number even or not
        BiPredicate<Integer,Integer> b = (a,b2) -> a+b2%2==0;
        System.out.println(b);
    }
}
