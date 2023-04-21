package springCore.beanLifeCycle;

import javax.annotation.PostConstruct;
import java.sql.*;

public class StudentDao {
    private String driver ;
    private String url;
    private String username;
    private String password;

    private Connection con;

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        createConnection();
    }
    public void createConnection() throws ClassNotFoundException, SQLException {
        //load driver
        Class.forName(driver);

        // get a connection
        con = DriverManager.getConnection(url,username,password);
    }

    public void selectAllRows() throws ClassNotFoundException, SQLException {
        // execute query
        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("select * from passwordmanager");

        while(rs.next()){
           int studentId = rs.getInt(1);
           String name = rs.getString(2);
           String username = rs.getString(3);
           String email = rs.getString(4);
           String password = rs.getString(5);

            System.out.println(studentId+" "+name+" "+username+" "+email+" "+password+" ");
        }
        con.close();
    }

    public void delete(int id) throws ClassNotFoundException, SQLException {
        // execute query
        Statement st = con.createStatement();

        st.executeUpdate("delete from password where id = " + id);

        con.close();
    }

}
