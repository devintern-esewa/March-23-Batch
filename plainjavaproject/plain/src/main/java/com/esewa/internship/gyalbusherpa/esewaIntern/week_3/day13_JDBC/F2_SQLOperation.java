package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day13_JDBC;

public class F2_SQLOperation {
    /*
           Types of SQL Commands

           1) DDL (Data definition language) commands:
           -> create table, alter table, drop table, etc.

           2) DML (Data manipulation language) commands:
           -> insert, delete, update

           3) DQL (Data query language) commands:
           -> select

           4) DCL (Data control language) commands:
           -> alter password, grant access etc.

           5) Data administration commands:
           -> start audit, stop audit

           6) Transaction control commands:
           -> commit, rollback, savepoint, etc.
     */

    /*
        For java developer
        Select Queries => DQL => select * from
        non-select Queries => DML => insert, delete, update
     */

    /*
        1) To create a table
        create table movies(movieNum number, movieName varchar(10), hero varchar(10));

        2) Delete/drop table
            drop table movies;

        3) To insert rows into table
            insert into movies values(1,'bahubali','prabhas');
            insert into movies values(2,'raees','sharukh');

        4) To update data
            update movies set hero = 'don' where movieNum = 1;

        5) To delete a row
            delete from movies where movieNum = 1;

        6) To select data:
            select * from movies;

     */

    /*
        executeQuery() => select queries => return type ResultSet
        executeUpdate() => non-select queries => return type int

        execute() => both select and non-select operations => return boolean if true=> select, if false=>non-select
     */
}
