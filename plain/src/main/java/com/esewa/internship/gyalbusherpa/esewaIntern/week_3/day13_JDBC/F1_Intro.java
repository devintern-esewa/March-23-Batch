package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day13_JDBC;

public class F1_Intro {
    /*
        1) JDBC is a technology which can be used to communicate with database from java application
        2) JDBC is part of Java Standard Edition(J2SE/JSE)
        3) JDBC is a specification (guidelines) defined by java vendor and implemented by database vendor.
        4) Database vendor provides implementation which is called Driver software.

     */

    /*
        Steps:
            1) Load and register Driver
            2) Establish connection between java app and database
            3) Creation of statement object
            4) Send and execute SQL query
            5) Process result from resultSet
            6) Close connection
     */

    /*
        Features:
            1) Standard API (database independent API)
            2) Java support
            3) CRUD
            4) Huge vendor support (e.g. mySQL, progress, etc)
     */

    /*
        Types of Drivers
            1) Type-1 Driver(JDBC-ODBC-Bridge Driver or Bridge Driver)
                Don't use (if no other driver is available there's no choice)
            2) Type-2 Driver(Native-API-Party java Driver or native Driver)
                Use when 3 and 4 driver is not available.

            3) Type-3 Driver(All java net protocol driver or network protocol Driver or middleware driver)
                This is database independent, use this when you use multiple database.
            4) Type-4 Driver(Pure java Driver or native protocol Driver or thin driver)
                Use this when you use only one database.
     */




}
