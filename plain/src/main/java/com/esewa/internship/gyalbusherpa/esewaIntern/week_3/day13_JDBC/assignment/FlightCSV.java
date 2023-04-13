package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day13_JDBC.assignment;

import week_3.assignment.FlightData;
import week_3.assignment.FlightDataMain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightCSV {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\hp\\IdeaProjects\\eSewa_Intern\\src\\week_3" +
                "\\assignment\\MOCK_FLIGHT_DATA.csv"));

        FlightDataMain f = new FlightDataMain();
        ArrayList<FlightData> flight = f.csvReader(br);

        DBOperation db = new DBOperation();
        db.insertData(flight);
        db.updateData("don", "01GWK9T121ZE8WNMBHSQQ5R9H4");
        db.deleteData("12:00 AM");
        db.selectAll();
    }

}
