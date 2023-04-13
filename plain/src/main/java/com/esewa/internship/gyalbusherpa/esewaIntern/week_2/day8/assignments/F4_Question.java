package week_2.day8.assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Emp {
    int id;
    String name;

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}

public class F4_Question {
    public static void main(String[] args) {
        Emp e = new Emp(1, "Ram");
        Emp e4 = new Emp(1352, "Sita");
        Emp e1 = new Emp(35, "Gita");
        Emp e5 = new Emp(1, "Sita");
        Emp e2 = new Emp(15, "Anita");
        Emp e3 = new Emp(1, "Laxmi");
        Emp e6 = new Emp(13298732, "Sita");
        ArrayList<Emp> arr = new ArrayList<>();
        arr.add(e);
        arr.add(e1);
        arr.add(e2);
        arr.add(e3);
        arr.add(e4);
        arr.add(e5);
        arr.add(e6);
        System.out.println("Before sorting");
        for (Emp ee : arr) {
            System.out.println(ee.toString());
        }


        Collections.sort(arr, new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                if (o1.id < o2.id) {
                    return -1;
                } else if (o1.id > o2.id) {
                    return 1;
                } else {
                    //
                    return 0;
                }
            }
        });
        System.out.println("After sorting Id");
        for (Emp ee : arr) {
            System.out.println(ee.toString());
        }
        Collections.sort(arr, new Comparator<Emp>() {
            @Override
            public int compare(Emp o1, Emp o2) {
                return o1.name.compareTo(o2.name);
            }
        });
        System.out.println("After sorting name");
        for (Emp ee :
                arr) {
            System.out.println(ee.toString());
        }
    }
}
