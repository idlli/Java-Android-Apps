package com.example.myapplicationrecyclesql_lite;

public class Client {
    int Cl_Id;
    String Nom;
    String Ville;
    String Sexe;

    public Client(int cl_Id, String nom, String ville, String sexe) {
        Cl_Id = cl_Id;
        Nom = nom;
        Ville = ville;
        Sexe = sexe;
    }

    public Client(String nom, String ville, String sexe) {
        Nom = nom;
        Ville = ville;
        Sexe = sexe;
    }

    public int getCl_Id() {
        return Cl_Id;
    }

    public void setCl_Id(int cl_Id) {
        Cl_Id = cl_Id;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville) {
        Ville = ville;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    @Override
    public String toString() {
        return "Client{" +
                "Cl_Id=" + Cl_Id +
                ", Nom='" + Nom + '\'' +
                ", Ville='" + Ville + '\'' +
                ", Sexe='" + Sexe + '\'' +
                '}';
    }
}
