package com.talk2me.talk2me;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ana Maria on 14/10/2017.
 */

public class ContactosAdapter extends BaseAdapter
{

    private List<String> listaContactos;
    private Context context;
    private static LayoutInflater inflater = null;

    public ContactosAdapter(MainActivity mainActivity, List<String> contactsList)
    {
        this.listaContactos = contactsList;
        this.context = mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listaContactos.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = view;
        final String lC = listaContactos.get(i);
        if (view == null)
            view = inflater.inflate(R.layout.detail_contact, null);

        TextView nombreTextView = (TextView) view.findViewById(R.id.textViewContact);
        nombreTextView.setText(lC);

        return view;
    }

}
