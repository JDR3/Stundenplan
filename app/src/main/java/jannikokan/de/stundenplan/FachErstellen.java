package jannikokan.de.stundenplan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fach_erstellen_activity);

        myDb = new DatabaseHelper(this);
        editTextFachName = (EditText) findViewById(R.id.editTextFachName);
        editTextFachKuerzel = (EditText) findViewById(R.id.editTextFachKuerzel);
        editTextFachRaum = (EditText) findViewById(R.id.editTextFachRaum);
        editTextFachLehrer = (EditText) findViewById(R.id.editTextFachLehrer);
        buttonFachSpeichern = (Button) findViewById(R.id.buttonFachSpeichern);
        addFach();
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



}



