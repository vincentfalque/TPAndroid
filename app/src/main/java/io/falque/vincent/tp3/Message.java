package io.falque.vincent.tp3;

public class Message {

    String content;
    String userName;
    String userEmail;
    Long timestamp;

    public Message() {
    }

    Message(String content, String userName, String userEmail, Long timestamp) {
        this.content = content;
        this.userName = userName;
        this.userEmail = userEmail;
        this.timestamp = timestamp;
    }
}
