package com.talk2me.talk2me;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ana Maria on 14/10/2017.
 */

public class ContactosAdapter extends BaseAdapter
{

    private List<ContactoEntity> listaContactos;
    private Context context;
    private static LayoutInflater inflater = null;

    public ContactosAdapter(MainActivity mainActivity, List<ContactoEntity> contactsList)
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
        final ContactoEntity lC = listaContactos.get(i);
        if (view == null)
            view = inflater.inflate(R.layout.detail_contact, null);

        TextView nombreTextView = (TextView) view.findViewById(R.id.textViewContact);
        nombreTextView.setText(lC.getNombre());

        nombreTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent i = new Intent(context, InformacionContactoActivity.class);
                i.putExtra("contacto", (Serializable) lC);
                context.startActivity(i);
            }
        });

        return view;
    }

}
