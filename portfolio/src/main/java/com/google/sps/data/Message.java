package com.google.sps.data;

public final class Message {
    private final String comment;
    private final String author;
    private final long timestamp;
    private final String email;

    Message(String c, String a, long t, String e=null){
        comment=c; author=a; timestamp=t; email=e;
    }

    public String getComment(){return comment;}
    public void setComment(String c){comment=c;}
    public String getAuthor(){return author;}
    public long getTimestamp(){return timestamp;}
    public String getEmail(){return email;}
}