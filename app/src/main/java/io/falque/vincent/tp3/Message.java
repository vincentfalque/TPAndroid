package io.falque.vincent.tp3;

public class Message {

    public String content;
    public String userName;
    public String userEmail;
    public Long timestamp;

    public Message() {
    }

    public Message(String content, String userName, String userEmail, Long timestamp) {
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
        this.timestamp = timestamp;
    }
}
