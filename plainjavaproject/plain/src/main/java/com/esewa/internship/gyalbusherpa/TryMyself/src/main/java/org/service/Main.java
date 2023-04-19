package org.service;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] str = {"flight", "flower", "flow", "fly", "flew", "flesh"};

        SortedSet<String> s = new TreeSet<>();

        for (int i = 0; i < str.length; i++) {
            for (int j = 1; j < str[i].length() + 1; j++) {
                s.add(str[i].substring(0, j));
            }
        }
        ArrayList<String> a = new ArrayList<>(s);
        TreeSet<String> value = new TreeSet<>();

        System.out.println(a);

        for (int i = 0; i < a.size(); i++) {
            String l = a.get(i);
            a.remove(a.get(i));
            if (a.contains(l)) {
                value.add(l);
                System.out.println("don");
            }
        }
        System.out.println(value);


//        System.out.println(value);
//        System.out.println(a.get(0).contains("fl"));


    }
}
