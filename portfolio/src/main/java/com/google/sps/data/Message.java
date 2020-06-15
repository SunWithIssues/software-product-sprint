package com.google.sps.data;

public class Message {
    public String comment;
    private long author;
    private long timestamp;
    private String email;

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
        email=null;
    }

    public String getComment(){return comment;}
    public void setComment(String c){comment=c;}
    public long getAuthor(){return author;}
    public long getTimestamp(){return timestamp;}
    public String getEmail(){return email;}
}