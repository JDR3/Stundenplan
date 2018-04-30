package jannikokan.de.stundenplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Jannik on 14.03.2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Stundenplan.db";
    public static final String TABLE_NAME = "Fach_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "FACHNAME";
    public static final String COL_3 = "FACHKUERZEL";
    public static final String COL_4 = "FACHRAUM";
    public static final String COL_5 = "FACHLEHRER";
    public static final String COL_6 = "FACHFARBE";










    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, FACHNAME TEXT, FACHKUERZEL TEXT, FACHRAUM TEXT, FACHLEHRER TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean speichereFach(String fachName, String fachKuerzel, String fachRaum, String fachLehrer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, fachName);
        contentValues.put(COL_3, fachKuerzel);
        contentValues.put(COL_4, fachRaum);
        contentValues.put(COL_5, fachLehrer);
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
}
