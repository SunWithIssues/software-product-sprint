package com.google.sps.data;

public class Message {
    public String comment;
    public long author;
    public long timestamp;
    public String email;

    // Email should be optional
    public Message(String comment, long author, long timestamp, String email){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        this.email=email;
    }

    public Message(String comment, long author, long timestamp){
        this.comment=comment; 
        this.author=author; 
        this.timestamp=timestamp; 
        email='';
    }


}