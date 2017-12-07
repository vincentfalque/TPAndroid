package io.falque.vincent.tp3;

public class User {

    private String nom;
    private String email;

    public User(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    public String getNom() {
        return this.nom;
    }

    public String getEmail() {
        return this.email;
    }
}
