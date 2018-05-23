package jannikokan.de.stundenplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jannik on 14.03.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Stundenplan.db";
    public static final String TABLE_NAME = "Fach_table";
    public static final String FACH_ID = "ID";
    public static final String FACH_NAME = "FACHNAME";
    public static final String FACH_KUERZEL = "FACHKUERZEL";
    public static final String FACH_RAUM = "FACHRAUM";
    public static final String FACH_LEHRER = "FACHLEHRER";
    public static final String FACH_FARBE = "FACHFARBE";
    public static final String TABLE_LEHRER = "Lehrer_table";
    public static final String LEHRERID = "ID_L";
    public static final String LEHRERNAME = "LEHRERNAME";
    public static final String LEHRERKUERZEL = "LEHRERKUERZEL";
    public static final String LEHRERRAUM = "LEHRERRAUM";
    public static final String LEHRERMAIL = "LEHRERMAIL";




    private static final String create_Table2 = "create table " + TABLE_LEHRER + "("+ LEHRERID +"INTEGER PRIMARY KEY," + LEHRERNAME +"TEXT," + LEHRERKUERZEL + "TEXT,"+ LEHRERRAUM + "TEXT," + LEHRERMAIL + "TEXT)";
    private static final String create_Table =  "create table " + TABLE_NAME + "("+ FACH_ID + "INTEGER PRIMARY KEY," + FACH_NAME +"TEXT," + FACH_KUERZEL + " TEXT,"+ FACH_RAUM + "TEXT," + FACH_LEHRER + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        Log.d("MeineAPP", "DB angelegt");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.d("MeineAPP", "Tabelle angelegt");
        //  db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FACHNAME TEXT, FACHKUERZEL TEXT, FACHRAUM TEXT, FACHLEHRER TEXT)");
        db.execSQL(create_Table);
        db.execSQL(create_Table2);

    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_LEHRER);

        onCreate(db);
        Log.d("MeineAPP", "in upgrade");

    }

    public boolean speichereFach(String fachName, String fachKuerzel, String fachRaum, String fachLehrer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACH_NAME, fachName);
        contentValues.put(FACH_KUERZEL, fachKuerzel);
        contentValues.put(FACH_RAUM, fachRaum);
        contentValues.put(FACH_LEHRER, fachLehrer);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return  false;
        }
        else {
            return  true;
        }
    }

    public Cursor zeigeFaecher(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from "+TABLE_NAME,null);
        return res;
    }


    public boolean speicherLehrer(String lehrerName, String lehrerKuerzel, String lehrerRaum, String lehrerMail){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LEHRERNAME, lehrerName);
        contentValues.put(LEHRERKUERZEL, lehrerKuerzel);
        contentValues.put(LEHRERRAUM, lehrerRaum);
        contentValues.put(LEHRERMAIL, lehrerMail);
        long result = db.insert(TABLE_LEHRER, null, contentValues);
        if (result == -1){
            return  false;
        }
        else {
            return  true;
        }

    }


    public Cursor zeigeLehrer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from "+ TABLE_LEHRER,null);
        return res;
    }




}

