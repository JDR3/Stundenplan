package jannikokan.de.stundenplan;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RaumErstellen extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextRaumNummer;
    EditText editTextRaumArt;
    Button buttonRaumSpeichern;
    Button buttonRaumAnzeigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raum_erstellen);

        myDb = new DatabaseHelper(this);
        myDb.fuegeNeueTabellenHinzu();
        editTextRaumNummer = (EditText) findViewById(R.id.editTextRaumNummer);
        editTextRaumArt = (EditText) findViewById(R.id.editTextRaumArt);
        buttonRaumSpeichern = (Button) findViewById(R.id.buttonRaumSpeichern);
        buttonRaumAnzeigen = (Button) findViewById(R.id.buttonRaeumeAnzeigen);

        addRaum();
        zeigeRaeume();
    }

    public void addRaum(){
        buttonRaumSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean istGespeichert = myDb.speichereRaum(editTextRaumNummer.getText().toString(),
                                        editTextRaumArt.getText().toString());
                if (istGespeichert==true){
                    Toast.makeText(RaumErstellen.this, "Raum wurde gespeichert.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(RaumErstellen.this, "Raum konnte nicht gespeichert werden.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void zeigeRaeume(){
        buttonRaumAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.zeigeRaeume();
                if (res.getCount() == 0) {
                    zeigeRaumNachricht("Fehler", "Keine Raeume gefunden");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID:" + res.getString(0)+"\n");
                    buffer.append("Raumnummer: " + res.getString(1)+"\n");
                    buffer.append("Raumart: " + res.getString(2)+"\n");

                }


                zeigeRaumNachricht("Fächer", buffer.toString());
            }
        });
    }

    public void zeigeRaumNachricht(String title, String Nachricht){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Nachricht);
        builder.show();

    }
}
