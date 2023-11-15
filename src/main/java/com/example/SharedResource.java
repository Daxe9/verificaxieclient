package com.example;

import java.util.ArrayList;

public class SharedResource {
    ArrayList<String> messages;
    int counter;
    public SharedResource() {
        this.messages = new ArrayList<>();
        counter = 0;
    } 
    
    public void addToQueue(String msg) {
        messages.add(msg);
    }

    public String getMessage() {
        String result = messages.get(counter);
        counter++;
        return result;
    }

}
