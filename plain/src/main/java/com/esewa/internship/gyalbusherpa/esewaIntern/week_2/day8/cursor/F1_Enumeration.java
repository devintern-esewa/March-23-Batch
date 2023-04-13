package week_2.day8.cursor;

import java.util.Enumeration;
import java.util.Vector;

public class F1_Enumeration {

    /*
        Introduced in 1.0 version(for Legacy)
        we can use enumeration to get Objects one by one from the old Collection Objects(Legacy Collection)
        we can create enumeration object by using elements() method of vector class

        public Enumeration elements();

        Enumeration e = v.elements();
     */

    public static void main(String[] args) {
        Vector v = new Vector();
        for (int i = 0; i < 10; i++) {
            v.addElement(i);
        }
        System.out.println(v);

        Enumeration e = v.elements();
        while(e.hasMoreElements()){
            Integer i = (Integer)e.nextElement();
            if(i%2==0){
                System.out.println(i);
            }
        }
    }
}
