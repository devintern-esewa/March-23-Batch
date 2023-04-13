package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.assignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FlightDataMain {

    /*
    This is Java code for processing flight data from a CSV file. It reads the data from the CSV file using a
    BufferedReader and stores it in an ArrayList of FlightData objects. It then converts the ArrayList into a TreeMap
    of, TreeMap of FlightData objects, where the outer TreeMap key is the destination airport code and the inner
    TreeMap key is the departure time. The FlightData class contains fields for flight ID, current altitude,
    destination airport code, departure time, and current location.

    The code then sorts the FlightData objects within each inner TreeMap by current altitude and stores the sorted data
    in an ArrayList of, ArrayList of Map.Entry of String and FlightData objects. The ArrayList also stores the count of
    FlightData objects in each inner TreeMap.

    Finally, the code writes the sorted FlightData objects to separate CSV files, one file for each destination airport
    code, with the file name being the airport code. The file contains a header row followed by the FlightData objects
    sorted by current altitude.
     */

    static ArrayList<String> head;
    static ArrayList<ArrayList<Map.Entry<String, FlightData>>> tail;

    static ArrayList<Object> countFile;


    public ArrayList<FlightData> csvReader(BufferedReader br) throws IOException {

        ArrayList<FlightData> flight = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");

            if (split[0].equals("flight_id")) {
                continue;
            }

            String flightId = split[0];
            int currAltitude = Integer.parseInt(split[1].isEmpty() ? String.valueOf(0) : split[1]);
            String destinationAirportCode = split[2];
            String deptTime = split[3];
            String currLocation = split.length > 4 ? split[4] : "N/A";

            flight.add(new FlightData(flightId, currAltitude, destinationAirportCode, deptTime, currLocation));
        }
        return flight;
    }

    public TreeMap<String, TreeMap<String, FlightData>> toMap(ArrayList<FlightData> flight) {

        TreeMap<String, TreeMap<String, FlightData>> map = new TreeMap<>();

        for (FlightData flightData : flight) {
            TreeMap<String, FlightData> flights = map.getOrDefault(flightData.getDestinationAirportCode(),
                    new TreeMap<>());
            flights.put(flightData.getDeptTime(), flightData);
            map.put(flightData.getDestinationAirportCode(), flights);
        }
        return map;
    }

    public void sortAltitude(TreeMap<String, TreeMap<String, FlightData>> map, ArrayList<ArrayList<Map.Entry<String,
            FlightData>>> tail, ArrayList<Object> countFile) {

        for (TreeMap<String, FlightData> flights : map.values()) {
            // Converting inner nested map to arraylist and sorting the currAltitude
            ArrayList<Map.Entry<String, FlightData>> entries = new ArrayList<>(flights.entrySet());
            entries.sort(((o1, o2) ->
                    Integer.compare(o1.getValue().currAltitude, o2.getValue().currAltitude)
            ));
            tail.add(entries);
            countFile.add(entries.size());
        }

    }

    public static HashMap<String, ArrayList<Map.Entry<String, FlightData>>> csvWriter(ArrayList<String> head,
                                                                                      ArrayList<ArrayList<Map.Entry<String,
                                                                                              FlightData>>> tail,
                                                                                      ArrayList<Object> countFile) {

        HashMap<String, ArrayList<Map.Entry<String, FlightData>>> finalSorted = new HashMap<>();

        for (int i = 0; i < head.size(); i++) {
            for (int j = 0; j < tail.size(); j++) {

                if (i == j) {
                    finalSorted.put(head.get(i), tail.get(j));
                    try {
//                        ExecutorService service = Executors.newFixedThreadPool(50);
//                        long a = System.nanoTime();
//
//                        CsvWriter d = new CsvWriter(head, tail, countFile);
//                        service.execute(d);
//
//                        service.shutdown();
//                        long b = System.nanoTime();
//                        System.out.println(b - a);
                        String variable = head.get(i);
                        System.out.println(variable + " -> " + countFile.get(i));
                        PrintWriter p = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_3" +
                                "\\assignment\\allFiles\\ " + variable + ".csv");
                        p.println(
                                ", " +
                                        "flightId, " +
                                        "currAltitude, " +
                                        "destinationAirportCode, " +
                                        "deptTime, " +
                                        "currLocation");
                        p.println("," + tail.get(j));

                        p.flush();
                        p.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }
        return finalSorted;
    }

    public static void main(String[] args) throws IOException, NullPointerException {


        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_3" +
                "\\assignment\\MOCK_FLIGHT_DATA.csv"));

        FlightDataMain flightObject = new FlightDataMain();

        // Reading from csv file and saving to this arrayList
        ArrayList<FlightData> flight = flightObject.csvReader(br);

        // Removing duplicate elements and saving to this map
        TreeMap<String, TreeMap<String, FlightData>> map = flightObject.toMap(flight);

        // Saving the key in this arrayList

        Set<String> s = map.keySet();
        head = new ArrayList<>(s);

        // Sorted currAltitude are saved to this list from map
        tail = new ArrayList<>();

        // Counting inner content in each main file and saving to this arrayList
        countFile = new ArrayList<>();

        flightObject.sortAltitude(map, tail, countFile);

        System.out.println("Total file " + head.size());

//         saving the final output in this map
        HashMap<String, ArrayList<Map.Entry<String, FlightData>>> finalSorted = flightObject.csvWriter(head, tail,
                countFile);

        br.close();
    }

}
