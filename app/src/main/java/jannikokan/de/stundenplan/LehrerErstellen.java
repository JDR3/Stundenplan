package jannikokan.de.stundenplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LehrerErstellen extends AppCompatActivity {


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


    }
}
