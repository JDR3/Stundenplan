package jannikokan.de.stundenplan;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity{

    private static final String TAG = "ListDataActivity";

    DatabaseHelper myDb;

    private ListView myListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);

        myListView = (ListView) findViewById(R.id.listView);
        myDb = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView(){
        Log.d(TAG, "populateListView: Displaying data in the Listview");

        Cursor res =  myDb.zeigeFaecher();
        ArrayList<String> listData = new ArrayList<>();
        while(res.moveToNext()){
            listData.add(getString(2));
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        myListView.setAdapter();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String fachName = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You clicked on" + fachName);

                Cursor res = myDb.getItemID(fachName);

                int itemID = -1;
                while (res.moveToNext()){
                    itemID = res.getInt(0);
                }
                if(itemID >-1){
                    Log.d(TAG, "onItemClick: The ID is:" +itemID);
                    Intent fachBearbeiten = new Intent(ListDataActivity.this, FachBearbeiten.class);
                    fachBearbeiten.putExtra("id",itemID);
                    fachBearbeiten.putExtra("fachName",fachName);
                    startActivity(fachBearbeiten);
                }
                else{
                    toastNachricht("Keine ID mit diesem Namen");
                }
            }
        });
    }

    private void toastNachricht(String nachricht){
        Toast.makeText(this, nachricht, Toast.LENGTH_LONG).show();
    }
}
