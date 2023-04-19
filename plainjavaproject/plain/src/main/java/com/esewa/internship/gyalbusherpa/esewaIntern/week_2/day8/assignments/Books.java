package week_2.day8.assignments;

import java.util.Comparator;

public class Books implements Comparable {
    String name;
    int price;

    public Books(String name, int price) {
        this.name = name;
        this.price = price;
    }

//    @Override
//    public String toString() {
//        return " name : " + name + " price : " + price + "\n";
//    }

    @Override
    public int compareTo(Object o) {
        int o1 = this.price;
        Books b = (Books) o;
        int o2 = b.price;

        if (o1 < o2) {
            return -1;
        } else if (o1 > o2) {
            return +1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}' + "\n";
    }
}

class BooksDecending implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Books b1 = (Books) o1;
        Books b2 = (Books) o2;

        int p1 = b1.price;
        int p2 = b2.price;

        if (p1 < p2) {
            return +1;
        } else if (p1 > p2) {
            return -1;
        } else {
            return 0;
        }
    }
}
