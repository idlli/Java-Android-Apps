package com.example.myapplicationrecyclesql_lite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ClientDataBaseHelper extends SQLiteOpenHelper {

    String TableClient = "Client";

    String ClientId = "ClientId";
    String Nom = "Nom";
    String Ville = "Ville";
    String Sexe = "Sexe";

    String TableCommand = "Command";
    //String TableClient = "Client";

    String CommandId = "CommandeId";
    String CommandDesc = "Description";
    String CommandDate = "Date";
    //String ClientId = "ClientId";
    public ClientDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MessageFormat.format("Create Table {0} ({1} Integer Primary Key Autoincrement, {2} Text, {3} Text, {4} Text)", TableClient, ClientId, Nom, Ville, Sexe));
        sqLiteDatabase.execSQL(MessageFormat.format("Create Table {0} ({1} Integer Primary Key Autoincrement, {2} Text, {3} Text, {4} Integer, Foreign Key({4}) References {5}(ClientId) ON DELETE CASCADE)", TableCommand, CommandId, CommandDesc, CommandDate, ClientId, TableClient));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(ClientDataBaseHelper.class.getName(), MessageFormat.format("Mettre a jour l'ancienne version {0} to {1}, avec destruction de toutes les données", i ,i1));
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableCommand);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableClient);
        onCreate(sqLiteDatabase);
    }
    ContentValues contentValues;
    public void AddClient(Client Cl) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(Nom, Cl.getNom());
        contentValues.put(Ville, Cl.getVille());
        contentValues.put(Sexe, Cl.getSexe());

        db.insert(TableClient, null, contentValues);
        db.close();
    }

    public void EditClient(int id, String nom, String ville, String sexe) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(Nom, nom);
        contentValues.put(Ville, ville);
        contentValues.put(Sexe, sexe);

        db.update(TableClient, contentValues, ClientId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void RemoveClient(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TableClient, ClientId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Client> GetAll() {
        List<Client> listClient = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Client Cl;

        Cursor cursor = db.rawQuery("Select * From " + TableClient, null);
        if (cursor.moveToFirst()) {
            do {
                Cl = new Client(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                );
                listClient.add(Cl);
            } while (cursor.moveToNext());
        }

        db.close();
        return listClient;
    }
  //  ContentValues contentValues;
    public void AddCommand(Command Cm) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(CommandDesc, Cm.getCommand());
        contentValues.put(CommandDate, Cm.getDateCommand());
        contentValues.put(ClientId, Cm.getCodeCl());

        db.insert(TableCommand, null, contentValues);
        db.close();
    }

    public void EditCommand(int id, String desc, String date) {
        SQLiteDatabase db = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put(CommandDesc, desc);
        contentValues.put(CommandDate, date);

        db.update(TableCommand, contentValues, CommandId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public void RemoveCommand(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TableCommand, CommandId + " = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    @SuppressLint("Range")
    public List<Command> GetAllCommande(int idClient) {
        List<Command> listCommand = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Command Com;

        Cursor cursor = db.rawQuery("Select * From " + TableCommand + " WHERE " + ClientId + " = " + idClient, null);
        if (cursor.moveToFirst()) {
            do {
                Com = new Command(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        Integer.parseInt(cursor.getString(3))
                );
                listCommand.add(Com);
            } while (cursor.moveToNext());
        }

        db.close();
        return listCommand;
    }
}
