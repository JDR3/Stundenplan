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
    public static final String TABLE_FACH = "Fach_table";
    public static final String COL_ID = "ID_F";
    public static final String FACHNAME = "FACHNAME";
    public static final String FACHKUERZEL = "FACHKUERZEL";
    public static final String FACHRAUM = "FACHRAUM";
    public static final String FACHLEHRER = "FACHLEHRER";
    public static final String COL_6 = "FACHFARBE";
    public static final String TABLE_LEHRER = "Lehrer_table";
    public static final String LEHRERID = "ID_L";
    public static final String LEHRERNAME = "LEHRERNAME";
    public static final String LEHRERKUERZEL = "LEHRERKUERZEL";
    public static final String LEHRERRAUM = "LEHRERRAUM";
    public static final String LEHRERMAIL = "LEHRERMAIL";




private static final String create_Table_Lehrer = "create table " + TABLE_LEHRER + "("+ LEHRERID +"INTEGER PRIMARY KEY AUTOINCREMENT," + LEHRERNAME +"TEXT," + LEHRERKUERZEL + "TEXT,"+ LEHRERRAUM + "TEXT," + LEHRERMAIL + "TEXT)";
private static final String create_Table_Fach =  "create table " + TABLE_FACH + "("+ COL_ID + "INTEGER PRIMARY KEY AUTOINCREMENT," + FACHNAME +"TEXT," + FACHKUERZEL + " TEXT,"+ FACHRAUM + "TEXT," + FACHLEHRER + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

        Log.d("MeineAPP", "DB angelegt");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


       db.execSQL("create table " + TABLE_FACH + "(ID_F INTEGER PRIMARY KEY AUTOINCREMENT, FACHNAME TEXT, FACHKUERZEL TEXT, FACHRAUM TEXT, FACHLEHRER TEXT)");
        db.execSQL("create table " + TABLE_LEHRER + "(ID_L INTEGER PRIMARY KEY AUTOINCREMENT, LEHRERNAME TEXT, LEHRERKUERZEL TEXT, LEHRERRAUM TEXT, LEHRERMAIL TEXT)");

      //  db.execSQL(create_Table_Fach + create_Table_Lehrer);
       // db.execSQL();
        Log.d("MeineAPP", "Tabelle angelegt");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {


       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FACH);
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_LEHRER);

        onCreate(db);
        Log.d("MeineAPP", "in upgrade");

    }

    public boolean speichereFach(String fachName, String fachKuerzel, String fachRaum, String fachLehrer){
        Log.d("MeineAPP", "--> speichereFach");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FACHNAME, fachName);

        contentValues.put(FACHKUERZEL, fachKuerzel);
        contentValues.put(FACHRAUM, fachRaum);
        contentValues.put(FACHLEHRER, fachLehrer);

        long result = db.insert(TABLE_FACH, null, contentValues);

        boolean retval;
        if (result == -1){
            retval =  false;
        }
        else {
            retval = true;
        }
        Log.d("MeineAPP", "<-- speichereFach: " + retval);
        return retval;
    }

    public Cursor zeigeFaecher(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select *from " + TABLE_FACH,null);
        return res;
    }


    public boolean speicherLehrer(String lehrerName, String lehrerKuerzel, String lehrerRaum, String lehrerMail){
        Log.d("MeineAPP", "in Methode Speichere Lehrer");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LEHRERNAME, lehrerName);
        contentValues.put(LEHRERKUERZEL, lehrerKuerzel);
        contentValues.put(LEHRERRAUM, lehrerRaum);
        contentValues.put(LEHRERMAIL, lehrerMail);
        Log.d("MeineAPP", "Lehrername " + lehrerName + "LehrerKürzel"+lehrerKuerzel + "Lehrerraum:"+lehrerRaum + "Lehrermail:"+lehrerMail);
        long result = db.insert(TABLE_LEHRER, null, contentValues);

        boolean retval;
        if (result == -1){
            retval= false;

        }
        else {
            retval = true;
        }

        Log.d("MeineAPP", "aus Methode Speichere Lehrer"+retval);
        return retval;
    }


    public Cursor zeigeLehrer(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_LEHRER,null);
        return res;
    }




}


