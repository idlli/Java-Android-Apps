package com.example.laghmouchi_exercice2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText _clientId;
    EditText _clientNom;
    RadioButton _homme;
    RadioButton _femme;
    Button _add;
    Button _edit;
    Button _remove;
    ListView _myList;

    ClientDataBaseHelper _myDb;

    ArrayAdapter<Client> _myAdapt;

    Toast _toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _myDb = new ClientDataBaseHelper(this, "dbClients", null, 1);

        _clientId = findViewById(R.id.IdClient);
        _clientNom = findViewById(R.id.NomClient);
        _homme = findViewById(R.id.RadioHomme);
        _femme = findViewById(R.id.RadioFemme);
        _add = findViewById(R.id.Add);
        _edit = findViewById(R.id.Edit);
        _remove = findViewById(R.id.Remove);
        _myList = findViewById(R.id.MyList);

        _myAdapt = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, _myDb.GetAll());
        _myList.setAdapter(_myAdapt);
        _myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                _clientId.setText(String.valueOf(_myDb.GetAll().get(i).getIdClient()));
                _clientNom.setText(_myDb.GetAll().get(i).getNomClient());
                if (_myDb.GetAll().get(i).getSexeClient().equals("Homme")) _homme.setChecked(true);
                else _femme.setChecked(true);

            }
        });

        _add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckContent()) {
                    if (_homme.isChecked())
                        _myDb.AddClient(new Client(
                                Integer.valueOf(_clientId.getText().toString()),
                                _clientNom.getText().toString(),
                                "Homme"
                                ));
                    else _myDb.AddClient(new Client(
                                Integer.valueOf(_clientId.getText().toString()),
                                _clientNom.getText().toString(),
                                "Femme"
                        ));
                    VideContent();
                    _myAdapt = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, _myDb.GetAll());
                    _myList.setAdapter(_myAdapt);
                    _toast = Toast.makeText(getApplicationContext(),"Client ajouté avec succès", Toast.LENGTH_LONG);
                }
                else _toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
                _toast.show();
            }
        });

        _edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckContent()) {
                    if (_homme.isChecked())
                        _myDb.EditClient(Integer.valueOf(_clientId.getText().toString()), _clientNom.getText().toString(), "Homme");
                    else
                        _myDb.EditClient(Integer.valueOf(_clientId.getText().toString()), _clientNom.getText().toString(), "Femme");
                    VideContent();
                    _myAdapt = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, _myDb.GetAll());
                    _myList.setAdapter(_myAdapt);
                    _toast = Toast.makeText(getApplicationContext(),"Client édité avec succès", Toast.LENGTH_LONG);
                }
                else _toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos entrées avant de continuer", Toast.LENGTH_LONG);
                _toast.show();
            }
        });

        _remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (_clientId.getText().toString() != "") {
                    _myDb.RemoveClient(Integer.valueOf(_clientId.getText().toString()));
                    VideContent();
                    _myAdapt = new ArrayAdapter<Client>(getApplicationContext(), android.R.layout.simple_list_item_1, _myDb.GetAll());
                    _myList.setAdapter(_myAdapt);
                    _toast = Toast.makeText(getApplicationContext(),"Client supprimé avec succès", Toast.LENGTH_LONG);
                }
                else _toast = Toast.makeText(getApplicationContext(),"Veuillez vérifier vos client sélectionner avant de continuer", Toast.LENGTH_LONG);
                _toast.show();
            }
        });

    }

    boolean CheckContent() {
        if (_clientId.getText().toString() != "" && _clientNom.getText().toString() != "" && (_homme.isChecked() || _femme.isChecked())) return true;
        else return false;
    }

    void VideContent() {
        _clientId.setText("");
        _clientNom.setText("");
    }
}