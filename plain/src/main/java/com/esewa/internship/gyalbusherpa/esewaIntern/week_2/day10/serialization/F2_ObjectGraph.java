package week_2.day10.serialization;

import java.io.*;

class Dogs implements Serializable{
    Cat c = new Cat();
}
class Cat implements Serializable{
    Rat r = new Rat();
}
class Rat implements Serializable{
    int j = 20;
}
public class F2_ObjectGraph {
    /*
        we can serialize any number of object to the file but in which order we serialize, in the same order only we
        have to deserialize. order of object is important in serialization.

        If we don't know order of object in serialization:
            FIS fis = new FIS("abc.ser");
            OIS ois = new OIS(fis);
            Object o = ois.readObject();
            if(o instanceOf Dog){
                Dog d = (Dog) o;
                // perform
            }

            else if(o instanceOf Cat){
                Cat c = (Cat) o;
                // perform cat operation
            }
     */

    /*
        Whenever we are serializing an object, the set of all object which are reachable from the object will be serialized automatically, this group of object is not but object graph.
        In object graph, every object should be serializable, if at-least one object is not serializable then we'll get runtime exception saying notSerializableException.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10" +
                "\\serialization\\ab.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        Dogs d = new Dogs();
        oos.writeObject(d);

        FileInputStream fis = new FileInputStream("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_2\\day10" +
                "\\serialization\\ab.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Dogs d2 = (Dogs)ois.readObject();
        System.out.println(d2.c.r.j);
    }
}
