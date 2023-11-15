package com.example;

import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Socket s = new Socket("127.0.0.1", 6969);       
            Client c = new Client(s); 
            c.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}
