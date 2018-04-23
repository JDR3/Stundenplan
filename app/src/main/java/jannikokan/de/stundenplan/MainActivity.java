package jannikokan.de.stundenplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextFachName;
    EditText editTextFachKuerzel;
    EditText editTextFachRaum;
    EditText editTextFachLehrer;
    Button buttonFachSpeichern;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                   Toast.makeText(MainActivity.this, "Fach wurde gespeichert.", Toast.LENGTH_LONG).show();
               }
               else {
                   Toast.makeText(MainActivity.this, "Fach konnte nicht gespeichert werden.", Toast.LENGTH_LONG).show();
               }
            }
        });

    }



}