package week_2.day8.list;

import java.lang.reflect.Constructor;
import java.util.Stack;

public class F8_Stack {
    /*
        It is a child class of Vector
        It is specially designed class fo FIFO
     */

    // Constructor
    // Stack a = new Stack();

    /*
    methods
        push(Object o)
        pop() -> removes and returns top of the stack.
        peek() -> returns the top of stack without removal of object
        empty()
        search() -> return offset
     */

    public static void main(String[] args) {
        Stack s = new Stack();
        s.push("A");
        s.push(5);
        s.push("C");
        System.out.println(s);

        System.out.println(s.search("A"));
        System.out.println(s.search("Z"));

    }
}
