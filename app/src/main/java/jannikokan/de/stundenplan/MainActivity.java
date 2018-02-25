package jannikokan.de.stundenplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    //Okanhatnkleinen
// test banane syso
    //PUSHSHSSHHS
    //i<3 git hub

  Button btnSlider;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       btnSlider = (Button) findViewById(R.id.buttonFreitag10);
        btnSlider.setOnClickListener(new View.OnClickListener() {
           @Override
          public void onClick(View view) {
              Intent intent = new Intent(MainActivity.this,SliderActivityActivity.class);
               startActivity(intent);
           }
       });
    }






}
