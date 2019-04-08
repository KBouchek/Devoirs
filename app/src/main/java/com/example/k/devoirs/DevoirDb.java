package com.example.k.devoirs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by k on 24/03/2016.
 */
public class DevoirDb  extends SQLiteOpenHelper {


    private SQLiteDatabase db;
    private final Context mctx;

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "devoirDb";

    // Contacts table name
    private static final String TABLE_DEVOIRS = "devoirs";

    // Contacts Table Columns names
    private static final String KEY_ID = "_id";
    private static final String KEY_CLASSE = "classe";
    private static final String KEY_DATE_POUR = "pour";
    private static final String KEY_DATE_DU = "du";
    private static final String KEY_TRAVAIL = "travail";
    private static final String KEY_OK = "ok";

    public DevoirDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mctx = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DEVOIRS_TABLE = "CREATE TABLE " + TABLE_DEVOIRS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CLASSE + " TEXT,"
                + KEY_DATE_POUR + " TEXT,"
                + KEY_DATE_DU + " TEXT,"
                + KEY_TRAVAIL + " TEXT,"
                + KEY_OK + " TEXT" + ")";
        db.execSQL(CREATE_DEVOIRS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEVOIRS);
        onCreate(db);
    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_DEVOIRS +" ORDER BY " + KEY_ID +" DESC", null);
        return res;

    }

    public long insert(Devoir devoir){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CLASSE, devoir.getClasse());
        values.put(KEY_DATE_POUR, devoir.getPour());
        values.put(KEY_DATE_DU, devoir.getDu());
        values.put(KEY_TRAVAIL, devoir.getTravail());
        values.put(KEY_OK, devoir.getOk());

        long idx = db.insert(TABLE_DEVOIRS, null, values);
        db.close();
        return  idx;
    }

    // Getting All Contacts
    public List<Devoir> getAllDevoirs() {
        List<Devoir> contactList = new ArrayList<Devoir>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DEVOIRS +" ORDER BY "+ KEY_CLASSE + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Devoir contact = new Devoir();
                //contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.SetClasse(cursor.getString(1));
                contact.SetPour(cursor.getString(2));
                contact.SetDu(cursor.getString(3));
                contact.SetTravail(cursor.getString(4));
                contact.SetOk(cursor.getString(5));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
    public boolean deleteDevoir(long rowId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_DEVOIRS, KEY_ID + " = " + rowId, null) > 0;
    }
}