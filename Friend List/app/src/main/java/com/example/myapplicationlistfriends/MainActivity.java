package com.example.myapplicationlistfriends;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView Grid;
    List<Friends> Friends = new ArrayList<Friends>();
    ArrayAdapter FriendsBox;
    Intent Intent;

    SharedPreferences.Editor prefEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Friends.add(new Friends("Luffy D. Monkey","19","May 5","Male","Luffy is the captain of the Straw Hat Pirates and is best friends with all of them and values them over all else. At first glance, Luffy does not appear to be very intelligent, often seeing things in childish manner and can easily be amazed by the simplest things. However, because he views the world in a straightforward and simple manner, he is occasionally the only person who can see past the events and see what should be done.",R.drawable.luffy_profile,R.drawable.luffy_background));
        Friends.add(new Friends("Zoro Roronoa","19","Nov 11","Male","Zoro was the first crew member to be recruited by Luffy. Zoro is a skilled swordsman who fights with his own unique sword style known as santoryu (three katana fighting style). This is achieved by using one katana in each hand, and another in his mouth. He is also seen fighting with only one or two swords. When in a serious fight he ties his normally, tied on the arm bandana, on his head.",R.drawable.zoro_profile,R.drawable.zoro_background));
        Friends.add(new Friends("Sanji","19","Mar 2","Male","Sanji is the chain-smoking chef of the Straw Hat Pirate Crew. He has superb fighting skills that only make use of his legs, in an effort to minimize damage to his hands which would impair his cooking skills.",R.drawable.sanji_profile,R.drawable.sanji_background));
        Friends.add(new Friends("Nami","18","Jul 3","Female","Nami is the second member of the Straw Hat Pirates to appear (in the anime), but the third to align herself with the crew, and the fifth to actually join. Possibly one of the best (or even the best) navigators currently sailing the Grand Line, she has the ability to recognize and analyze even the slightest changes in the weather through sheer intuition, thus saving the ship and the crew from the very unstable Grand Line weather on multiple occasions.",R.drawable.nami_profile,R.drawable.nami_background));
        Friends.add(new Friends("Robin Nico","28","Feb 6","Female","Robin is the seventh member of Straw Hat's Crew. She had a bounty of 79 million beri (One Piece currency) since she was eight years old, during an incident in which she supposedly destroyed six Buster Call ships through the use of the powers she gained by eating the Hana Hana no Mi (Flower Flower or Blooming Blooming Fruit).",R.drawable.robin_profile,R.drawable.robin_background));
        Friends.add(new Friends("Usopp","19","Apr 1","Male","Usopp is a liar and likes to play pranks on his crew members or just goof off with Luffy and Chopper. He loves to tell tall tales to Chopper (who believes him blindly) or anyone who's listening. The other aspect of Usopp besides his lying is his cowardice; he is a very timid person, or so he thinks. In extremely stressful situations, Usopp has proven to be far more courageous than he believes himself to be.",R.drawable.usopp_profile,R.drawable.usopp_background));
        Friends.add(new Friends("Chopper Tony Tony","15","Dec 24","Male","Chopper is the Straw Hat crew's general physician despite being a reindeer. After a set of unusual circumstances, he ate the Hito Hito no Mi (Human Human Fruit), which permitted him the ability to speak, think, and (to a limited extent) change into a human Chopper often acts like a child, but he is an upright friend and companion who will try anything to overcome a task given by his team.",R.drawable.chopper_profile,R.drawable.chopper_background));
        Friends.add(new Friends("Brook","90","Apr 3","Male","Brook known as the \"Gentleman Skeleton\", is a pirate inhabiting the Florian Triangle region of the Grand Line. Although he claims to be a gentleman, and talks in the dialect of one, Brook's etiquette has severely degenerated over the course of his isolation. He has awful table manners: he shouts for food while waiting to be served, asks to swap plates with people who have larger servings than he does and eating so messily his entire face is stained.",R.drawable.broock_profile,R.drawable.broock_background));
        Friends.add(new Friends("Jinbe","44","Apr 2","Male","Jinbe is a whale shark fishman who was the second captain of the Sunny Pirates after Fisher Tiger, and who became a Shichibukai eight years ago. Jinbe's name comes from jinbei-zame (甚平鮫), which means \"whale shark\" in Japanese. His epithet, Kaikyou would normally translate into \"Strait\" or \"Channel\". However since the kanji for it is written as (海侠の) and not (海峡の), the kyou part (侠) of it comes from (任侠), a honorific used when addressing a high ranking yakuza member, thus making his epithet a Japanese pun.",R.drawable.jenbie_profile,R.drawable.jenbie_background));

        Grid = findViewById(R.id.FriendsGrid);

        FriendsBox = new FriendsBox(getApplicationContext(),0, Friends);

        Grid.setAdapter(FriendsBox);


        SharedPreferences preferences = getSharedPreferences("monPrefs", MODE_PRIVATE);
        prefEditor = preferences.edit();


        Grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent = new Intent(getApplicationContext(), ProfileVisited.class);

//                Intent.putExtra("Name", Friends.get(i).getName());
//                Intent.putExtra("Age", Friends.get(i).getAge());
//                Intent.putExtra("DateOfBirth", Friends.get(i).getDateOfBirth());
//                Intent.putExtra("Sexe", Friends.get(i).getSexe());
//                Intent.putExtra("Description", Friends.get(i).getDescription());
//
//                Intent.putExtra("Profile", Friends.get(i).getProfile());
//                Intent.putExtra("Background", Friends.get(i).getBackground());

                //Intent.putExtra("Position", i);
                Toast.makeText(getApplicationContext(),"ddd", Toast.LENGTH_SHORT).show();
                prefEditor.putString("Name", Friends.get(i).getName());
                prefEditor.putString("Age", Friends.get(i).getAge());
                prefEditor.putString("DateOfBirth", Friends.get(i).getDateOfBirth());
                prefEditor.putString("Sexe", Friends.get(i).getSexe());
                prefEditor.putString("Description", Friends.get(i).getDescription());

                prefEditor.putInt("Profile", Friends.get(i).getProfile());
                prefEditor.putInt("Background", Friends.get(i).getBackground());

                prefEditor.apply();

                startActivity(Intent);

            }
        });

    }
}