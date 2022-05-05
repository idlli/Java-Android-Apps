package com.example.laghmouchi_exercice2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDataBaseHelper extends SQLiteOpenHelper {

    String ClientTable = "client";

    String ClientId = "id";
    String Nom = "nom";
    String Sexe = "sexe";
    String email = "email";
    String motdepasse = "motdepasse";

    public ClientDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MessageFormat.format(
                "Create Table {0} ({1} Integer Primary Key, {2} Text, {3} Text, {4} Text, {5} Text)",
                ClientTable, ClientId, Nom, Sexe, email, motdepasse));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ClientTable);
        onCreate(sqLiteDatabase);
    }

    ContentValues contentValues;
    public void AddClient(Client Cl) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(ClientId, Cl.getIdClient());
        contentValues.put(Nom, Cl.getNomClient());
        contentValues.put(Sexe, Cl.getSexeClient());

        db.insert(ClientTable, null, contentValues);
        db.close();
    }

    public void AddClientWithLogin(Client Cl) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(ClientId, Cl.getIdClient());
        contentValues.put(Nom, Cl.getNomClient());
        contentValues.put(Sexe, Cl.getSexeClient());
        contentValues.put(email, Cl.getEmail());
        contentValues.put(motdepasse, Cl.getMotDePasse());

        db.insert(ClientTable, null, contentValues);
        db.close();
    }

    public void EditClient(int id, String nom, String sexe) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(Nom, nom);
        contentValues.put(Sexe, sexe);

        db.update(ClientTable, contentValues, ClientId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void RemoveClient(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(ClientTable, ClientId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Client> GetAll() {
        List<Client> listClient = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Client Cl;

        Cursor cursor = db.rawQuery("Select * From " + ClientTable, null);
        if (cursor.moveToFirst()) {
            do {
                Cl = new Client(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                );
                listClient.add(Cl);
            } while (cursor.moveToNext());
        }

        db.close();
        return listClient;
    }

    @SuppressLint("Range")
    public Client GetClient(String _identity, String _password) {
        SQLiteDatabase db = getReadableDatabase();
        Client _u = null;

        Cursor cursor = db.rawQuery("Select * From " + ClientTable + " Where " + email + " = '" + _identity + "' And " + motdepasse + " = '" + _password +"'", null);
        if (cursor.moveToFirst()) {
            _u = new Client(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
            );
        }

        db.close();
        return _u;
    }
}
