package jannikokan.de.stundenplan;

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

public class LehrerErstellen extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTextLehrerName;
    EditText editTextLehrerKuerzel;
    EditText editTextLehrerMail;
    EditText editTextLehrerRaum;
    Button buttonLehrerSpeichern;
    Button buttonLehrerAnzeigen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lehrer_erstellen_activity);

        myDb = new DatabaseHelper(this);
        myDb.fuegeNeueTabellenHinzu();
        editTextLehrerName = (EditText) findViewById(R.id.editTextLehrerName);
        editTextLehrerKuerzel = (EditText) findViewById(R.id.editTextLehrerKuerzel);
        editTextLehrerRaum = (EditText) findViewById(R.id.editTextLehrerRaum);
        editTextLehrerMail = (EditText) findViewById(R.id.editTextLehrerMail);
        buttonLehrerSpeichern = (Button) findViewById(R.id.buttonLehrerSpeichern);

        addLehrer();
        zeigeLehrer();

    }



    public void addLehrer(){
        buttonLehrerSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean istGespeichert = myDb.speicherLehrer(editTextLehrerName.getText().toString(),
                        editTextLehrerKuerzel.getText().toString(),
                        editTextLehrerRaum.getText().toString(),
                        editTextLehrerMail.getText().toString());
                if (istGespeichert==true){
                    Toast.makeText(LehrerErstellen.this, "Lehrer wurde gespeichert.", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(LehrerErstellen.this, "Lehrer konnte nicht gespeichert werden.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    public void zeigeLehrer() {
        buttonLehrerAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.zeigeLehrer();
                if (res.getCount() == 0){
                    showToast("Fehler", "Keinen Lehrer gefunden");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID:" + res.getString(0)+ "\n");
                    buffer.append("Lehrer" + res.getString( 1) + "\n");
                    buffer.append("Kuerzel" + res.getString(2) + "\n");
                    buffer.append("Raum" + res.getString(3) + "\n");
                    buffer.append("Mail" + res.getString(4) + "\n\n");
                }
                showToast("Lehrer", buffer.toString());

            }
        });
    }


    public void showToast(String title, String Nachricht){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Nachricht);
        builder.show();
    }



}
