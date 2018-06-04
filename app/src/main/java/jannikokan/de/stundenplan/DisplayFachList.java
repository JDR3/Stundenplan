package jannikokan.de.stundenplan;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;


public class DisplayFachList extends Activity {

    private DatabaseHelper myDb;
    private SQLiteDatabase db;

    //variables to hold staff records
    private ArrayList<String> fachName = new ArrayList<String>();
    private ArrayList<String> fachKuerzel = new ArrayList<String>();
    private ArrayList<String> fachRaum = new ArrayList<String>();
    private ArrayList<String> fachLehrer = new ArrayList<String>();

    private ListView fachList;
    private AlertDialog.Builder build;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_faecher);

        fachList = (ListView) findViewById(R.id.listViewFaecher);

        myDb = new DatabaseHelper(this);


    }

    @Override
    protected void onResume() {
        //refresh data for screen is invoked/displayed
        displayData();
        super.onResume();
    }

    /**
     * displays data from SQLite
     */
    private void displayData() {
        db = myDb.getWritableDatabase();
        //the SQL command to fetched all records from the table
        Cursor mCursor = db.rawQuery("SELECT * FROM "
                + DatabaseHelper.TABLE_NAME, null);

        //reset variables
        fachName.clear();
        fachKuerzel.clear();
        fachLehrer.clear();
        fachRaum.clear();

        //fetch each record
        if (mCursor.moveToFirst()) {
            do {
                //get data from field
                fachName.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.FACH_NAME)));
                fachKuerzel.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.FACH_KUERZEL)));
                fachRaum.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.FACH_RAUM)));
                fachLehrer.add(mCursor.getString(mCursor.getColumnIndex(DatabaseHelper.FACH_LEHRER)));

            } while (mCursor.moveToNext());
            //do above till data exhausted
        }

        //display to screen
        FachAdapter fachAdapter = new FachAdapter(DisplayFachList.this, fachName, fachKuerzel, fachRaum, fachLehrer);
        fachList.setAdapter(fachAdapter);
        mCursor.close();
    }//end displayData




}
