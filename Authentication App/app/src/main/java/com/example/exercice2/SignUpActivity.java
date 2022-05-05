package com.example.laghmouchi_exercice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText _id;
    EditText _nom;
    EditText _sexe;
    EditText _email;
    EditText _password;

    Button _signUp;
    Button _signIn;

    ClientDataBaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        _id = findViewById(R.id.ClientId);
        _nom = findViewById(R.id.Nom);
        _sexe = findViewById(R.id.Sexe);
        _email = findViewById(R.id.EMail);
        _password = findViewById(R.id.MotDePasse);

        _signIn = findViewById(R.id.SignIn1);
        _signUp = findViewById(R.id.SignUp1);

        MyDB = new ClientDataBaseHelper(this, "dbClients", null, 1);

        _signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast _toast;
                if (CheckContent()) {
                    try {
                        MyDB.AddClient(new Client(
                                Integer.valueOf(_id.getText().toString()),
                                _nom.getText().toString(),
                                _sexe.getText().toString(),
                                _email.getText().toString(),
                                _password.getText().toString()
                        ));

                        Intent _intent;
                        _intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(_intent);
                    } catch (Exception e) {
                        _toast = Toast.makeText(getApplicationContext(),e.toString(), Toast.LENGTH_LONG);
                        _toast.show();
                    }
                }
                else {
                    _toast = Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs nécessaires", Toast.LENGTH_LONG);
                    _toast.show();
                }
            }
        });

        _signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intent;
                _intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(_intent);
            }
        });
    }

    boolean CheckContent() {
        if (_id.getText().length() < 1 || _password.getText().length() < 1) return  false;
        else return true;
    }
}