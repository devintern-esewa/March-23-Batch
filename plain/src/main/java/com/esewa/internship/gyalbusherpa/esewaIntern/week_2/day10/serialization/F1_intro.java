package week_2.day10.serialization;

import java.io.*;

class Dog implements Serializable {
    int i = 10;
    int j = 20;
//    transient int j = 20;

//    transient static int j = 20;
//    transient final int j = 20;

    /*


        Declaration                         output
        int i = 10;
        int j = 20;                         10..20

        transient int i = 10;
        int j = 20;                         0..20

        transient static int i = 10;
        transient int j = 20;               10..0

        transient int i = 10;
        transient final int j =20;          0..20

        transient static int i = 10;
        transient final int j = 20;         10..20

     */

}

public class F1_intro {
    /*
        1) Intro
        2) Object Graph in serialization
        3) customized serialization
        4) serialization wrt inheritance
        5) externalization
        6) serialVersionUID
     */

    /*
        What is serialization?
            The process of writing state of an object to a file is called serialization but strictly speaking, it is
            the process of conversion an object from java supported form into whether file supported or network
            supported form.

            By using FileOutputStream and ObjectOutputStream classes we can implement Serialization.

        What is deserialization?
            The process of reading the state of an object from the file is called deserialization but strictly
            speaking, it is the process of converting an object from either file supported or network supported into
            java supported form.

            By using FileInputStream and ObjectInputStream classes we can implement deserialization.
     */
    /*
        We can serialize only serializable objects.
        An object is said to be serializable if and only if the corresponding class implements Serializable interface.
        Serializable is present in java.io package, and it doesn't contain any methods.
        It is a marker interface.
        If we try to serializable a non-serializable then we'll get runtime exception saying NotSerializableException.

     */

    /*
        Transient
        - transient modifier only applicable for variable but not for methods and classes.
        - At the time of serialization, if we don't want to save the value of the particular variable to meet the
          security constraint, then we should declare that variable as transient.
        - While performing serialization, jvm ignores the value of original value of transient variable and save
          default value with to the file.
        - Hence, transient means not to serialize.

        Transient vs static
        - static variable is not part of object state, hence it won't participate in serialization. Due to this
        declaring static variable as transient, there is no use.

        Transient vs final
        - final variable will participate in serialization directly by value. Hence, declaring final variable as
        transient there is no impact.



     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dog d = new Dog();

        // Serialization
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10" +
                "\\serialization\\abc.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(d);

        // DeSerialization
        FileInputStream fis = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10" +
                "\\serialization\\abc.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dog d2 = (Dog) ois.readObject();

        System.out.println(d2.i + d2.j);

    }
}
