package jannikokan.de.stundenplan;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FachAdapter extends BaseAdapter {
    private Context mContext;
    //list fields to be displayed
    private ArrayList<String> fachName;
    private ArrayList<String> fachKuerzel;
    private ArrayList<String> fachRaum;
    private ArrayList<String> fachLehrer;


    public FachAdapter(Context c, ArrayList<String> fachName, ArrayList<String> fachKuerzel, ArrayList<String> fachRaum, ArrayList<String> fachLehrer) {
        this.mContext = c;
        //transfer content from database to temporary memory
        this.fachName = fachName;
        this.fachKuerzel = fachKuerzel;
        this.fachRaum = fachRaum;
        this.fachLehrer = fachLehrer;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return fachName.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int pos, View child, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.fach_reihe, null);
            mHolder = new Holder();

            //link to TextView
            mHolder.textViewFachNameAnzeigen = (TextView) child.findViewById(R.id.textViewFachNameAnzeigen);
            mHolder.textViewFachKuerzelAnzeigen = (TextView) child.findViewById(R.id.textViewFachKuerzelAnzeigen);
            mHolder.textViewFachRaumAnzeigen = (TextView) child.findViewById(R.id.textViewFachRaumAnzeigen);
            mHolder.textViewFachLehrerAnzeigen = (TextView) child.findViewById(R.id.textViewFachLehrerAnzeigen);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }
        //transfer to TextView in screen
        mHolder.textViewFachNameAnzeigen.setText(fachName.get(pos));
        mHolder.textViewFachKuerzelAnzeigen.setText(fachKuerzel.get(pos));
        mHolder.textViewFachRaumAnzeigen.setText(fachRaum.get(pos));
        mHolder.textViewFachLehrerAnzeigen.setText(fachLehrer.get(pos));


        return child;
    }

    public class Holder {
        TextView textViewFachNameAnzeigen;
        TextView textViewFachKuerzelAnzeigen;
        TextView textViewFachRaumAnzeigen;
        TextView textViewFachLehrerAnzeigen;
    }

}

