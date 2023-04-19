package week_2.java1point8;

interface I {
    void don();

    default void hero() {
        System.out.println("don");
    }
}

interface I2 {
    void don();

    default void hero() {
        System.out.println("don2");
    }
}

public class F5_F5_Default_Static_In_Interface implements I, I2, Inter {

    /*
        Every method present inside interface is always: public and abstract

        void m1();
        public void m1();
        abstract void m1();
        public abstract void m1();

        Related Methods:
        1.8v : default and static method allowed
        1.9v : private method also allowed

        Variables:
        Every variable present inside interface is always: public static final

     */

    /*
        Default method | Virtual extension method | defender method:

        - Without affecting implementation classes, we can add methods to the interface.
        // we cannot make Object class methods as default in interface.

     */

    /*
        interface static method by default is not available to implementing class.

     */

    public static void main(String[] args) {
        // because of default we don't have to give implementation of hero();
        // we can also override the default method.
        // to override use public instead of default

        F5_F5_Default_Static_In_Interface i = new F5_F5_Default_Static_In_Interface();
        i.don();
        i.hero();

        // static

        Inter.m1(); // -> only valid way to use static method of interface.
        /*
        all invalid:
            m1();
            F5_F5_Default_Static_In_Interface.m1();
            F5_F5_Default_Static_In_Interface f = new F5_F5_Default_Static_In_Interface();
            f.m1();
         */
    }

    public void hero() {
        System.out.println("override");
//        I.super.hero();
        I2.super.hero();
    }

    @Override
    public void don() {
        System.out.println("don3");

    }
}

interface Inter {
    public static void m1() {
        System.out.println("Interface static method");
    }
}
