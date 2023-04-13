package week_2.day8.assignments;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment4 {
    public static void main(String[] args) {

        ArrayList<Employee> al = new ArrayList<>();

        Employee e1 = new Employee("don", 1, 25);
        Employee e3 = new Employee("son", 3, 33);
        Employee e4 = new Employee("son", 4, 23);
        Employee e2 = new Employee("don", 2, 51);
        Employee e5 = new Employee("don", 3, 513);
        Employee e6 = new Employee("dagssadasdf", 3, 513);

        al.add(e1);
        al.add(e2);
        al.add(e3);
        al.add(e4);
        al.add(e5);
        al.add(e6);

        Map<String, List<Employee>> map = new HashMap<>();

        for(Employee e: al){
            List<Employee> emps = map.getOrDefault(e.getName(),new ArrayList<>());
            emps.add(e);
            map.put(e.getName(),emps);
        }
        System.out.println(map);


        ArrayList<String> indi2 = new ArrayList<>();


        for (int i = 0; i < al.size(); i++) {
            for (int j = i; j < al.size(); j++) {
                if (i == j) {
                    continue;
                }
                if (String.valueOf(al.get(i)).equals(String.valueOf(al.get(j)))) {
                    String na = String.valueOf(al.get(i));
                    indi2.add(na);

                    String k = "";
                    k += al.get(i).id + " " + al.get(i).age + "";
                    indi2.add(k);

                    String k2 = "";
                    k2 += al.get(j).id + " " + al.get(j).age + "";
                    indi2.add(k2);
                }
            }
        }
//        System.out.println(indi2);
//        System.out.println(indi.size());


//        String [] arr = {"d","s","s","d"};
//
//        for (int i = 0; i < arr.length; i++) {
//            for (int j = 0; j < arr.length; j++) {
//                if(i==j){
//                    continue;
//                }
//                if(arr[i].equals(arr[j])){
//                    System.out.print(arr[i] + " " + arr[j] +"\n");
//                    break;
//                }
//            }
//            i++;
//        }


    }

}
