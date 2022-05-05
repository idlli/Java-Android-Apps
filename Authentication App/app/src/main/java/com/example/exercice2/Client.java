package com.example.laghmouchi_exercice2;

import java.text.MessageFormat;

public class Client {
    int IdClient;
    String NomClient;
    String SexeClient;
    String Email;
    String MotDePasse;

    public Client(int idClient, String nomClient, String sexeClient) {
        IdClient = idClient;
        NomClient = nomClient;
        SexeClient = sexeClient;
    }

    public Client(String nomClient, String sexeClient) {
        NomClient = nomClient;
        SexeClient = sexeClient;
    }

    public Client(int idClient, String nomClient, String sexeClient, String email, String motDePasse) {
        IdClient = idClient;
        NomClient = nomClient;
        SexeClient = sexeClient;
        Email = email;
        MotDePasse = motDePasse;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int idClient) {
        IdClient = idClient;
    }

    public String getNomClient() {
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }

    public String getSexeClient() {
        return SexeClient;
    }

    public void setSexeClient(String sexeClient) {
        SexeClient = sexeClient;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return IdClient + " " + NomClient + " " + SexeClient;
    }
}
