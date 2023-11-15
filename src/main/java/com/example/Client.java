package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    Socket connection; 
    BufferedReader in;
    DataOutputStream out;
    Scanner scan;

    public Client(Socket connection) throws IOException {
        this.connection = connection;
        in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        out = new DataOutputStream(connection.getOutputStream());
        scan = new Scanner(System.in);
    }

    private void displayOptions() {
        System.out.println("A: Scegli una lettera");
        System.out.println("B: Scegli una parola");
        System.out.println("C: Esci");
    }    

    private String getUserInput() {
        String userInput = scan.nextLine().trim();
        if (userInput.equals("ESCI")) {
            System.out.println("Uscita...");
            System.exit(0);
        }
        return userInput;
    }

    public void run() {
        try {
            String input = "";
            String serverResponse = "";
            do {
                serverResponse = in.readLine();
                if (serverResponse.equals("d")) {
                    serverResponse = in.readLine();
                    Integer attempts = Integer.parseInt(serverResponse);
                    System.out.println("You guessed the word in " + attempts + " attempts");
                    return;
                }
                System.out.println("La parola e' " + serverResponse);

                displayOptions();
                input = getUserInput();
                switch (input) {
                    case "A":
                        out.writeBytes("a\n"); 
                        System.out.println("Inserisci la lettera: ");
                        input = getUserInput();
                        
                        out.writeBytes(input + "\n");

                        break;
                
                    case "B":
                        out.writeBytes("b\n"); 
                        System.out.println("Inserisci la parola: ");
                        input = getUserInput();
                        out.writeBytes(input + "\n");
                        serverResponse = in.readLine();
                        if (serverResponse.equals("d"))  {
                            serverResponse = in.readLine();
                            Integer attempts = Integer.parseInt(serverResponse);
                            System.out.println("You guessed the word in " + attempts + " attempts");
                            return;
                        } else if (serverResponse.equals("e")) {
                            System.out.println("Try again...");

                        }
                        break;

                    case "C":
                        break; 
                    default:
                        continue;
                }
            } while (!input.equals("C"));

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
