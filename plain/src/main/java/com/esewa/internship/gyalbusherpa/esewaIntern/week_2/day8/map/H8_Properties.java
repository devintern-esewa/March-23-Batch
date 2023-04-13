package week_2.day8.map;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class H8_Properties {
    /*
        In our program if anything which changes frequently (like username, password, email ids, phone number, etc.)
        are not recommended to hard code in java program because if there is any change, to reflect that change
        recompilation, rebuild and redeployment is required, sometimes server restart also required, which creates a
        big impact to the client.

        We can overcome this problem by using properties file. Such type of variable things we have to configure in
        the properties file. From that properties file we have to read into java program and we can use those
        properties.

        The main advantage of this approach if there is a change in properties file, to reflect that change just
        redeployment is enough which won't create any business impact to the client.

        We can use java properties object to hold properties which are coming from properties file.

     */

    /*

        In normal map (like hashMap, hashtable, treemap) key and value can be any type.
        But in properties, key and value should be String type.

     */

    // Constructor:
    // Properties p = new Properties();

    /*
        methods:

        String getProperty(String name) -> get value associated with the specified property
        String setProperty(String name, String value) -> set new property
        Enumeration propertyNames()

        void load(InputStream is)
            - to load properties from properties file into java properties object
        void store(OutputStream os, String comment)
            - to store properties from java properties object into properties file

     */
    public static void main(String[] args) throws IOException, SQLException {
        Properties p = new Properties();
        FileInputStream fis = new FileInputStream("abc.properties");
        p.load(fis);

        System.out.println(p);

        String s = p.getProperty("username");
        System.out.println(s);

        p.setProperty("don","te53");
        FileOutputStream fos = new FileOutputStream("abc.properties");
        p.store(fos,"Updated by me");

        //Sample 2

        Properties p2 = new Properties();
        FileInputStream fis2 = new FileInputStream("db.properties");
        p.load(fis2);
        String url = p.getProperty("url");
        String user = p.getProperty("user");
        String pwd = p.getProperty("pwd");
        Connection con = DriverManager.getConnection(url,user,pwd);

    }
}
