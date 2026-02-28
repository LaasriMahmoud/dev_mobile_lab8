package com.example.projetws.beans;

public class Etudiant {

    private int id;
    private String nom;
    private String prenom;
    private String ville;
    private String sexe;

    public Etudiant() {
        // Constructeur vide obligatoire pour Gson
    }

    public Etudiant(int id, String nom, String prenom, String ville, String sexe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.sexe = sexe;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", ville='" + ville + '\'' +
                ", sexe='" + sexe + '\'' +
                '}';
    }
}