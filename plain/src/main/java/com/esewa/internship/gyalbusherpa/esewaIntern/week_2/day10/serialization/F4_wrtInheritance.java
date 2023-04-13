package week_2.day10.serialization;

import java.io.*;

class Animal implements Serializable {
    int i = 10;
}

class Cats extends Animal {
    int j = 20;
}

class Mammal {
    int i = 32;
}

class Man extends Mammal implements Serializable {
    int j = 23;
}

public class F4_wrtInheritance {
    /*
        CASE 1:

        Even though child class doesn't implement serializable, we can serialize child if the parent class implement
        the serializable interface. i.e. serializable nature is inheriting from parent to child. Hence, if the parent
        is serializable then every child id serializable.

        In the above example, even though cats class doesn't implement serializable, we can serialize cats object
        because it's parent animal class implements serializable.

        Object doesn't implement Serializable interface.

        CASE 2:

        Even though parent class doesn't implement serializable, we can serialize child class object if child class
        implement serializable interface, i.e. to serialize child class object parent class need not be serializable.

        At the time of serialization jvm will check is any variable inheriting from non-serializable parent or not.
        If any variable is inheriting from non-serializable parent then jvm ignores original value and save default
        value to the file.

        At the time of deserialization jvm will check is any parent non-serializable or not.
        If any parent is non-serializable then jvm will execute instance control flow in non-instance parent and
        share its instance variable to the current object.

        While executing instance control flow of non-serializable parent jvm will always no-arg constructor, hence,
        every non-serializable should compulsory contain no-arg constructor. It maybe defaults constructor generated
        by compiler or customized constructor explicitly provided by the program. Otherwise, we'll get runTime
        exception saying invalidClassException.

     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Cats c = new Cats();
        FileOutputStream fos = new FileOutputStream("ac.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(c);

        FileInputStream fis = new FileInputStream("ac.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Cats c2 = (Cats) ois.readObject();

        System.out.println(c2.i + " " + c2.j);

        Man h = new Man();
        FileOutputStream fos2 = new FileOutputStream("acc.ser");
        ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
        oos2.writeObject(h);

        FileInputStream fis2 = new FileInputStream("acc.ser");
        ObjectInputStream ois2 = new ObjectInputStream(fis2);
        Man c22 = (Man) ois2.readObject();

        System.out.println(c22.i + " " + c22.j);
    }
}
