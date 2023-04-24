package week_2.java1point8;

public class F1_Intro {

     /*
        1) Lambda Expressions
        2) Functional Interface
        3) default methods and static methods
        4) Predefined Functional Interfaces
            - Predicate
            - Function
            - Consumer
            - Supplier
        5) Double Colon Operator (::)
            - method reference
            - constructor reference
        6) Streams
        7) Date and Time API
        8) Optional Class
        9) Nashron JavaScript Engine

     */

    /*
        1) Lambda Expressions:

        came in 1930
        LISP was the first programming language to use Lambda expression

        The main Objective of Lambda Expression:
            - to bring benefits of functional programming into java
            - to write more readable, maintainable and concise code
            - to use APIs very easily
            - enable parallel processing

        What is lambda expression:
        - It is an anonymous function (nameless, without return type, without modifiers)

     */
    public static void main(String[] args) {
        /*
            () -> System.out.println("ehl"); // eg 1
            (x, y) -> System.out.println(x + y); // eg2
            (x) -> x*x; // eg3
            (x) -> {return x*x}; // eg4
            (s)-> s.length(); // eg5
        */

        // How to call lambda expression?
        // FI => Functional Interface
            // An interface that contain only one method inside it.
            // eg. Runnable, Comparable, comparator, etc.
            // eg. Runnable, Comparable, comparator, etc.

        // If you want to use lambda expression, compulsory we need to invoke functional interface.


    }
}
