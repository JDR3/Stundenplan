package jannikokan.de.stundenplan;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class FachBearbeiten extends AppCompatActivity {

    private static final String TAG = "FachBearbeitenActivity";
    Button buttonSpeichern;
    Button buttonLöschen;
    EditText editTextFachNameBearbeiten;
    EditText editTextFachKuerzelBearbeiten;
    EditText editTextFachLehrerBearbeiten;
    EditText editTextFachRaumBearbeiten;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fach_bearbeiten_activity);

        buttonSpeichern = (Button) findViewById(R.id.buttonSpeichern);
    }

}
