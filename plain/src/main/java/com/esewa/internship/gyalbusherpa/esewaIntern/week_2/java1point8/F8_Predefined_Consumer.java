package week_2.java1point8;

import java.util.function.Consumer;

class Movie{
    String name;

    public Movie(String name) {
        this.name = name;
    }
}
public class F8_Predefined_Consumer {
    /*

        Predicate<T> ----> boolean (test)
        Function<T,R> ----> R type (apply)
        Consumer<T> ----> void (accept)

        method is accept.

     */
    public static void main(String[] args) {
        Consumer<String> c = i-> System.out.println(i);
        c.accept("don");

        Consumer<Movie> c2 = i -> System.out.println(i.name + "ready ");
        Consumer<Movie> c3 = i -> System.out.println(i.name + "ready but flop");
        Consumer<Movie> c4 = i -> System.out.println(i.name + "ready not");
        Consumer<Movie> cc = c2.andThen(c3).andThen(c4);

        Movie m = new Movie("Spy");
        cc.accept(m);
    }
}
