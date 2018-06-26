package jannikokan.de.stundenplan;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class StundeZuweisen extends AppCompatActivity {

    Spinner fachSpinner;
    Spinner lehrerSpinner;
    Spinner raumSpinner;
    DatabaseHelper myDb;
    Cursor res;
    ArrayList<String> faecherListe;
    ArrayList<String> lehrerListe;
    ArrayList<String> raumListe;
    ArrayAdapter<String> faecherAdapter;
    ArrayAdapter<String> lehrerAdapter;
    ArrayAdapter<String> raumAdapter;
    Toast itemToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stunde_zuweisen);


        myDb = new DatabaseHelper(this);
        fachSpinner = findViewById(R.id.spinnerFaecher);
        lehrerSpinner = findViewById(R.id.spinnerLehrer);
        raumSpinner = findViewById(R.id.spinnerRaum);
        faecherListe = new ArrayList<>();
        lehrerListe = new ArrayList<>();
        raumListe = new ArrayList<>();
        faecherAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, faecherListe);
        lehrerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lehrerListe);
        raumAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, raumListe);




        zeigeSpinnerFaecher();
        zeigeSpinnerLehrer();
        zeigeSpinnerRaum();

        fachSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Item:" + faecherListe.get(position), Toast.LENGTH_LONG).show();

                /*res.moveToPosition(position);
                myDb.speichereFachNr(res.getLong(res.getColumnIndex(DatabaseHelper.FACH_ID)));*/

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void zeigeSpinnerFaecher(){

        res = myDb.zeigeFaecher();

        while(res.moveToNext()){
            faecherListe.add(res.getString(1));
        }


        faecherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fachSpinner.setAdapter(faecherAdapter);

        }

    public void zeigeSpinnerLehrer(){

        res = myDb.zeigeLehrer();

        while(res.moveToNext()){
            lehrerListe.add(res.getString(1));
        }


        lehrerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lehrerSpinner.setAdapter(lehrerAdapter);

    }

    public void zeigeSpinnerRaum(){

        res = myDb.zeigeRaeume();

        while(res.moveToNext()){
            raumListe.add(res.getString(1));
        }


        raumAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raumSpinner.setAdapter(raumAdapter);

    }


    }


