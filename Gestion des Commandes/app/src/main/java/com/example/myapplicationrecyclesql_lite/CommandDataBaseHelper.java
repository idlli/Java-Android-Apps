package com.example.myapplicationrecyclesql_lite;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommandDataBaseHelper extends SQLiteOpenHelper {

    String TableCommand = "Command";
    String TableClient = "Client";

    String CommandId = "Id";
    String CommandDesc = "Description";
    String CommandDate = "Date";
    String ClientId = "ClientId";

    public CommandDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MessageFormat.format("Create Table {0} ({1} Integer Primary Key Autoincrement, {2} Text, {3} Text, {4} Integer, Foreign Key({4}) References {5}(Id))", TableCommand, CommandId, CommandDesc, CommandDate, ClientId, TableClient));
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(ClientDataBaseHelper.class.getName(), MessageFormat.format("Mettre a jour l'ancienne version {0} to {1}, avec destruction de toutes les données", i ,i1));
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TableCommand);
        onCreate(sqLiteDatabase);
    }

    ContentValues contentValues;
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
    public List<Command> GetAll(int idClient) {
        List<Command> listCommand = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Command Com;

        Cursor cursor = db.rawQuery("Select * From " + TableCommand + " WHERE " + ClientId + " = " + idClient, null);
        if (cursor.moveToFirst()) {
            do {
                Com = new Command(
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(CommandId))),
                        cursor.getString(cursor.getColumnIndex(CommandDesc)),
                        cursor.getString(cursor.getColumnIndex(CommandDate)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(ClientId)))
                );
                listCommand.add(Com);
            } while (cursor.moveToNext());
        }

        db.close();
        return listCommand;
    }
}
