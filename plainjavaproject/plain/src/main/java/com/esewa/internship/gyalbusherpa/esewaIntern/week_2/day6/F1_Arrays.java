package week_2.day6;

import java.util.Arrays;

public class F1_Arrays {

    // Java array is an object which contains elements of a similar data type. Additionally, The elements of an array
    // are stored in a contiguous memory location. It is a data structure where we store similar elements. We can
    // store only a fixed set of elements in a Java array.

    /*

    Advantages
        Code Optimization: It makes the code optimized, we can retrieve or sort the data efficiently.
        Random access: We can get any data located at an index position.
    Disadvantages
        Size Limit: We can store only the fixed size of elements in the array. It doesn't grow its size at runtime.
        To solve
        this problem, collection framework is used in Java which grows automatically.

    * */
    public static void main(String[] args) {

        int[] a = new int[5]; //declaration and instantiation
        a[0] = 10;//initialization
        a[1] = 20;
        a[2] = 70;
        a[3] = 40;
        a[4] = 50;
        //traversing array
        for (int i = 0; i < a.length; i++)//length is the property of array
            System.out.println(a[i]);
    }
}
