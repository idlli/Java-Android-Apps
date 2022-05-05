package com.example.myapplicationlistfriends;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileVisited extends AppCompatActivity {

    Intent Intent;
    TextView NameView;
    TextView AgeView;
    TextView DateOfBirthView;
    TextView SexeView;
    TextView DescriptionView;
    ImageView ProfileView;
    ImageView BackgroundView;

    //int[] Profiles = {R.drawable.luffy_profile, R.drawable.zoro_profile, R.drawable.sanji_profile, R.drawable.nami_profile, R.drawable.robin_profile, R.drawable.usopp_profile, R.drawable.chopper_profile, R.drawable.broock_profile, R.drawable.jenbie_profile};
    //int[] Backgrounds = {R.drawable.luffy_background, R.drawable.zoro_background, R.drawable.sanji_background, R.drawable.nami_background, R.drawable.robin_background, R.drawable.usopp_background, R.drawable.chopper_background, R.drawable.broock_background, R.drawable.jenbie_background};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_visited);

        //Intent = getIntent();
//        String Name = Intent.getStringExtra("Name");
//        String Age = Intent.getStringExtra("Age");
//        String DateOfBirth = Intent.getStringExtra("DateOfBirth");
//        String Sexe = Intent.getStringExtra("Sexe");
//        String Description = Intent.getStringExtra("Description");
//
//        int Profile = Intent.getIntExtra("Profile", 0);
//        int Background = Intent.getIntExtra("Background", 0);

        ////int P = Intent.getIntExtra("Position",0);

        SharedPreferences settings = getSharedPreferences("monPrefs", MODE_PRIVATE);

        String Name = settings.getString("Name",null);
        String Age = settings.getString("Age",null);
        String DateOfBirth = settings.getString("DateOfBirth",null);
        String Sexe = settings.getString("Sexe",null);
        String Description = settings.getString("Description",null);

        int Profile = settings.getInt("Profile", 0);
        int Background = settings.getInt("Background", 0);

        NameView = findViewById(R.id.profileName);
        AgeView = findViewById(R.id.profileAge);
        DateOfBirthView = findViewById(R.id.profileDateOfBirth);
        SexeView = findViewById(R.id.profileSexe);
        DescriptionView = findViewById(R.id.profileDescription);
        ProfileView = findViewById(R.id.profileVector);
        BackgroundView = findViewById(R.id.profileBackground);

        NameView.setText(Name);
        AgeView.setText(Age);
        DateOfBirthView.setText(DateOfBirth);
        SexeView.setText(Sexe);
        DescriptionView.setText(Description);

//        ProfileView.setImageResource(Profiles[P]);
//        BackgroundView.setImageResource(Backgrounds[P]);
        ProfileView.setImageResource(Profile);
        BackgroundView.setImageResource(Background);

    }
}