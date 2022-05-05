package com.example.myapplicationlistfriends;

public class Friends {
    String Name;
    String Age;
    String DateOfBirth;
    String Sexe;
    String Description;
    int Profile;
    int Background;

    public Friends(String name, String age, String dateOfBirth, String sexe, String description, int profile, int background) {
        Name = name;
        Age = age;
        DateOfBirth = dateOfBirth;
        Sexe = sexe;
        Description = description;
        Profile = profile;
        Background = background;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String sexe) {
        Sexe = sexe;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getProfile() {
        return Profile;
    }

    public void setProfile(int profile) {
        Profile = profile;
    }

    public int getBackground() {
        return Background;
    }

    public void setBackground(int background) {
        Background = background;
    }
}
