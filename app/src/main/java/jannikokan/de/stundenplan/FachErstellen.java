package jannikokan.de.stundenplan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by jannik on 27.02.2018.
 */

public class FachErstellen extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextFachName;
    EditText editTextFachKuerzel;
    EditText editTextFachRaum;
    EditText editTextFachLehrer;
    Button buttonFachSpeichern;
    Button buttonFaecherAnzeigen;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fach_erstellen_activity);

        myDb = new DatabaseHelper(this);
        myDb.fuegeNeueTabellenHinzu();
        editTextFachName = (EditText) findViewById(R.id.editTextFachName);
        editTextFachKuerzel = (EditText) findViewById(R.id.editTextFachKuerzel);
        editTextFachRaum = (EditText) findViewById(R.id.editTextFachRaum);
        editTextFachLehrer = (EditText) findViewById(R.id.editTextFachLehrer);
        buttonFachSpeichern = (Button) findViewById(R.id.buttonFachSpeichern);
        buttonFaecherAnzeigen = (Button) findViewById(R.id.buttonFaecherAnzeigen);

        addFach();
        zeigeFaecher();


    }

    public void addFach(){
        buttonFachSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean istGespeichert = myDb.speichereFach(editTextFachName.getText().toString(),
                        editTextFachKuerzel.getText().toString(),
                        editTextFachRaum.getText().toString(),
                        editTextFachLehrer.getText().toString());
                if (istGespeichert==true){
                    Toast.makeText(FachErstellen.this, "Fach wurde gespeichert.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(FachErstellen.this, "Fach konnte nicht gespeichert werden.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    /*public void zeigeFaecher(){
        buttonFaecherAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.zeigeFaecher();
                if (res.getCount() == 0) {
                    zeigeNachricht("Fehler", "Keine Fächer gefunden");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID:" + res.getString(0)+"\n");
                    buffer.append("Fach: " + res.getString(1)+"\n");
                    buffer.append("Kürzel: " + res.getString(2)+"\n");
                    buffer.append("Raum: " + res.getString(3)+"\n");
                    buffer.append("Lehrer: " + res.getString(4)+"\n\n");
                }


                zeigeNachricht("Fächer", buffer.toString());
            }
        });
    }*/

    public void zeigeFaecher(){
        buttonFaecherAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FachErstellen.this,ZeigeFaecherListe.class);
                startActivity(intent);
            }
        });
    }

    public void zeigeNachricht(String title, String Nachricht){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Nachricht);
        builder.show();

    }






}



