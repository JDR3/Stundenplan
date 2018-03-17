package jannikokan.de.stundenplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

  DatabaseHelper myDb;
  Button btnSlider;
  Button buttonFachErstellen;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);


       btnSlider = (Button) findViewById(R.id.buttonFreitag10);
        btnSlider.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,SliderActivityActivity.class);
               startActivity(intent);
           }
       });

       buttonFachErstellen = (Button) findViewById(R.id.buttonDonnerstag10);
        buttonFachErstellen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,FachErstellen.class);
                startActivity(i);
            }
        });


    }






}
