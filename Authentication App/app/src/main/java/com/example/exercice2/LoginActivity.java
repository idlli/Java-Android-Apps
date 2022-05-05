package com.example.laghmouchi_exercice2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText _identity;
    EditText _password;

    Button _singIn;
    Button _singUp;



    ClientDataBaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        _identity = findViewById(R.id.identity);
        _password = findViewById(R.id.password);

        _singIn = findViewById(R.id.SignIn);
        _singUp = findViewById(R.id.SignUp);



        MyDB = new ClientDataBaseHelper(this, "dbClients", null, 1);

        _singIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast _toast;

                if (CheckContent()) {

                    Client _u = MyDB.GetClient(_identity.getText().toString(), _password.getText().toString());
                    if (_u != null) {

                            Intent In;
                            In = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(In);
                    }
                    else {
                        _toast = Toast.makeText(getApplicationContext(),"Utilisateur non trouvé", Toast.LENGTH_LONG);
                        _toast.show();
                    }
                }
                else {
                    _toast = Toast.makeText(getApplicationContext(),"Veuillez remplir tous les champs nécessaires", Toast.LENGTH_LONG);
                    _toast.show();
                }

            }
        });

        _singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intent;
                _intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(_intent);
            }
        });
    }

    boolean CheckContent() {
        if (_identity.getText().length() < 1 || _password.getText().length() < 1) return  false;
        else return true;
    }
}