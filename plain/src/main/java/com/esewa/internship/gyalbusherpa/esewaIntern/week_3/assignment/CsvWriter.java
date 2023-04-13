package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.assignment;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

public class CsvWriter implements Runnable {
    private ArrayList<Map.Entry<String, FlightData>> data;

    private ArrayList<String> head;
    private ArrayList<ArrayList<Map.Entry<String, FlightData>>> tail;
    private ArrayList<Object> countFile;

    public CsvWriter(ArrayList<String> head,
                     ArrayList<ArrayList<Map.Entry<String,
                             FlightData>>> tail,
                     ArrayList<Object> countFile) {
        this.head = head;
        this.tail = tail;
        this.countFile = countFile;
    }

    @Override
    public void run() {
//        try {
//            String variable = head.get(i);
//            System.out.println(variable + " -> " + countFile.get(i));
//            PrintWriter p = new PrintWriter("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_3" +
//                    "\\assignment\\allFiles\\ " + variable + ".csv");
//            p.println(
//                    ", " +
//                            "flightId, " +
//                            "currAltitude, " +
//                            "destinationAirportCode, " +
//                            "deptTime, " +
//                            "currLocation");
//            p.println("," + tail.get(j));
//
//            p.flush();
//            p.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
