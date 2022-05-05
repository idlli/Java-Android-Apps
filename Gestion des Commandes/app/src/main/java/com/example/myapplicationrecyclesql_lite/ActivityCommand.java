package com.example.myapplicationrecyclesql_lite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityCommand extends AppCompatActivity {

    EditText Command, Date;
    Button Add, Edit, Remove;
    ListView MyListView;
    Command Cm;
    ClientDataBaseHelper MyDB;

    List<Command> listCommand = new ArrayList<>();
    ArrayAdapter<Command> MyAdapt = null;

    SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_command);

        MyDB = new ClientDataBaseHelper(this, "GestionClientCommande", null, 1);

        Command = findViewById(R.id.CommandEdit);
        Date = findViewById(R.id.DateEdit);

        MyListView = findViewById(R.id.ListComm);

        settings = getSharedPreferences("monPrefs", MODE_PRIVATE);

        ShowInListView();

        MyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cm = MyDB.GetAllCommande(settings.getInt("ClientId", 0)).get(i);
                Command.setText(Cm.getCommand());
                Date.setText(Cm.getDateCommand());
                position = i;
            }
        });
    }
    int position = -1;
    Toast toast;
    public void AddCommand(View view) {
        if (CheckContent()) {
            MyDB.AddCommand(new Command(Command.getText().toString(), Date.getText().toString(), settings.getInt("ClientId", 0)));
            VideContent();
            ShowInListView();
            toast = Toast.makeText(getApplicationContext(),"Command ajouté avec succès", Toast.LENGTH_LONG);
        }
        else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
        toast.show();
    }

    public void EditCommand(View view) {
        if (CheckContent() && position != -1) {
            MyDB.EditCommand(MyDB.GetAllCommande(settings.getInt("ClientId", 0)).get(position).getNumCommamd(), Command.getText().toString(), Date.getText().toString());
            VideContent();
            ShowInListView();
            toast = Toast.makeText(getApplicationContext(),"Command édité avec succès", Toast.LENGTH_LONG);
        }
        else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
        toast.show();
    }

    public void RemoveCommand(View view) {
        if (position != -1) {
            MyDB.RemoveCommand(MyDB.GetAllCommande(settings.getInt("ClientId", 0)).get(position).getNumCommamd());
            VideContent();
            ShowInListView();
            toast = Toast.makeText(getApplicationContext(),"Command supprimé avec succès", Toast.LENGTH_LONG);
        }
        else toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos command sélectionner avant de continuer", Toast.LENGTH_LONG);
        toast.show();
    }

    boolean CheckContent() {
        if (Command.getText().toString() != "" && Date.getText().toString() != "") return true;
        else return false;
    }

    void VideContent() {
        Command.setText("");
        Date.setText("");
    }

    void ShowInListView() {
        MyAdapt = new ArrayAdapter<Command>(getApplicationContext(), android.R.layout.simple_list_item_1, MyDB.GetAllCommande(settings.getInt("ClientId", 0)));
        MyListView.setAdapter(MyAdapt);
    }
}