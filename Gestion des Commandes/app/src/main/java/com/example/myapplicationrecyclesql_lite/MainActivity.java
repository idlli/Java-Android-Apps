package com.example.myapplicationrecyclesql_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static EditText Nom, Ville, Sexe;
    Button Add, Edit, Remove;
    //RecyclerView MyRecycler;
    static Client Cl;
    ClientDataBaseHelper MyDB;

    ListView list;
    ArrayAdapter<Client> Adp;

    List<Client> listClient = new ArrayList<>();
    //MyRecyclerAdapter MyAdapt;

    Toast toast;

    //public static MainActivity MyActivity;

    int pos = -1;

    //private static Context MyContext;

    Intent intent;

    SharedPreferences.Editor prefEditor;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //MyActivity = this;

        MyDB = new ClientDataBaseHelper(this, "GestionClientCommande", null, 1);

        Nom = findViewById(R.id.NomEdit);
        Ville = findViewById(R.id.VilleEdit);
        Sexe = findViewById(R.id.SexeEdit);

        Add = findViewById(R.id.Add);
        Edit = findViewById(R.id.Edit);
        Remove = findViewById(R.id.Remove);

        list = findViewById(R.id.myList);

        ShowClients();

        preferences = getSharedPreferences("monPrefs", MODE_PRIVATE);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = position;

                Cl = MyDB.GetAll().get(position);
                Nom.setText(Cl.getNom());
                Ville.setText(Cl.getVille());
                Sexe.setText(Cl.getSexe());
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Cl = MyDB.GetAll().get(position);
                prefEditor = preferences.edit();

                intent = new Intent(getApplicationContext(), ActivityCommand.class);

                prefEditor.putInt("ClientId", Cl.getCl_Id());
                prefEditor.commit();

                startActivity(intent);

                return false;
            }
        });

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckContent()) {
                    MyDB.AddClient(new Client(Nom.getText().toString(), Ville.getText().toString(), Sexe.getText().toString()));
                    VideContent();
                    ShowClients();
                    toast = Toast.makeText(getApplicationContext(),"Client ajouté avec succès", Toast.LENGTH_LONG);
                }
                else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
                toast.show();
            }
        });


        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckContent() && pos != -1) {
                    MyDB.EditClient(MyDB.GetAll().get(pos).getCl_Id(), Nom.getText().toString(), Ville.getText().toString(), Sexe.getText().toString());
                    VideContent();
                    ShowClients();
                    toast = Toast.makeText(getApplicationContext(),"Client édité avec succès", Toast.LENGTH_LONG);
                }
                else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pos != -1) {
                    MyDB.RemoveClient(MyDB.GetAll().get(pos).getCl_Id());
                    VideContent();
                    ShowClients();
                    toast = Toast.makeText(getApplicationContext(),"Client supprimé avec succès", Toast.LENGTH_LONG);
                }
                else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos client sélectionner avant de continuer", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }

    boolean CheckContent() {
        if (Nom.getText().toString() != "" && Ville.getText().toString() != "" && Sexe.getText().toString() != "") return true;
        else return false;
    }

    void VideContent() {
        Nom.setText("");
        Ville.setText("");
        Sexe.setText("");
    }

    void ShowClients() {
        Adp = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, MyDB.GetAll());
        list.setAdapter(Adp);
    }
}