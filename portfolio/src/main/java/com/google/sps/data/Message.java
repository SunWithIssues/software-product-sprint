package com.google.sps.data;

public class Message {
    public String comment;
    public long author;
    public String timestamp;
    public String email;

    // Email should be optional
    public Message(String comment, long author, String timestamp, String email){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        this.email=email;
    }

    public Message(String comment, long author, String timestamp){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        this.email="";
    }


}