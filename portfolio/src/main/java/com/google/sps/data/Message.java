package com.google.sps.data;

public class Message {
    public String comment;
    public long author;
    public long timestamp;
    public String email;

    // Email should be optional
    public Message(String c, long a, long t, String e){
        comment=c; 
        author=a; 
        timestamp=t; 
        email=e;
    }

    public Message(String c, long a, long t){
        comment=c; 
        author=a; 
        timestamp=t; 
        email='';
    }


}