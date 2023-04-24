package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day12_Socket;

import java.io.*;
import java.net.Socket;

public class SocClient {
    public static void main(String[] args) throws IOException {
        String ip = "localhost";
        int port = 3333; //0 to 65535
        Socket s = new Socket(ip,port);

        String str = "don is me";

        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println(str);
        os.flush();

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String name = br.readLine();

        System.out.println("C: Data from server " + name);


    }
}
