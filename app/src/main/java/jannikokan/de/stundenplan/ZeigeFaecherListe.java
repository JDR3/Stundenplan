package jannikokan.de.stundenplan;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ZeigeFaecherListe extends AppCompatActivity {

    DatabaseHelper myDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zeige_faecher);

        myDb = new DatabaseHelper(this);
        ListView listViewFaecher = (ListView) findViewById(R.id.listViewFaecher);

        final ArrayList<String> faecherListe = new ArrayList<>();
        Cursor res = myDb.zeigeFaecher();

        if (res.getCount() == 0){
            Toast.makeText(ZeigeFaecherListe.this, "Keine Fächer gefunden", Toast.LENGTH_LONG).show();
        } else {
            while (res.moveToNext()){
                faecherListe.add(res.getString(1));
                ListAdapter fachListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, faecherListe);
                listViewFaecher.setAdapter(fachListAdapter);
            }
        }

        listViewFaecher.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            }
        });
    }
}
