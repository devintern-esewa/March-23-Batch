package com.esewa.internship.gyalbusherpa.esewaIntern.week_3.day12_Socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocServer {
    public static void main(String[] args) throws IOException {
        System.out.println("S: Server started");
        ServerSocket ss = new ServerSocket(3333);

        System.out.println("S: Server is waiting for client request");
        Socket s = ss.accept();

        System.out.println("S: client connected");

        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String str = br.readLine();
        System.out.println("S: Client data:  " + str);

        String name = str.substring(0,3);

        OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(os);
        out.println(name);
        out.flush();
        System.out.println("Data send from server to client ");

    }
}
