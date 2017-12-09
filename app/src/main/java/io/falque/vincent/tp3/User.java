package io.falque.vincent.tp3;

class User {

    private String nom;
    private String email;

    User(String nom, String email) {
        this.nom = nom;
        this.email = email;
    }

    String getNom() {
        return this.nom;
    }

    String getEmail() {
        return this.email;
    }
}
