package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
            SharedResource share = new SharedResource();
            Client c = new Client(s, share); 

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String temp = in.readLine();
            if (temp.equals("f")) {
                System.out.println("here");
                c.start();
                while (true) {
                    String message = in.readLine();
                    System.out.println(message);
                    share.addToQueue(message);
                    if (message.equals("b")) {
                        System.out.println("Someone has won the game");
                        System.exit(0);
                    }

                }
            } else {
                System.out.println("Unknown input");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }
}
