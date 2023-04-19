package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day13_JDBC.assignment;

import week_3.assignment.FlightData;

import javax.crypto.Cipher;
import javax.swing.plaf.IconUIResource;
import java.sql.*;
import java.util.ArrayList;

public class DBOperation {

    Connection conn;
    PreparedStatement st;

    DBOperation() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/csv", "root",
                "Basketball");
    }
    void insertData(ArrayList<FlightData> arr) throws SQLException {
        for (FlightData flight : arr) {
            String a = flight.getFlightId();
            int b = flight.getCurrAltitude();
            String c = flight.getDestinationAirportCode();
            String d = flight.getDeptTime();
            String e = flight.getCurrLocation();

            st = conn.prepareStatement("insert into csv values(?,?,?,?,?)");
            st.setString(1, a);
            st.setInt(2, b);
            st.setString(3, c);
            st.setString(4, d);
            st.setString(5, e);

            st.executeUpdate();
        }
        conn.close();
    }

    void updateData(String alt, String id) throws SQLException {
        st = conn.prepareStatement("update csv set curr_location=? where flight_id=?");
        st.setString(1, alt);
        st.setString(2, id);
        st.executeUpdate();
        conn.close();
    }

    void deleteData(String id) throws SQLException {
        st = conn.prepareStatement("delete from csv where dept_time=?");
        st.setString(1, id);
        st.executeUpdate();
        conn.close();
    }


    public void selectAll() throws SQLException {
        st = conn.prepareStatement("select * from csv");
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getInt(2) + " " + rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
        }
    }
}
