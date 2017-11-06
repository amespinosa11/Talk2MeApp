package com.talk2me.talk2me;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ArrayList<ContactoEntity> listaContactos;

    private ListView listViewlistaContactos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaContactos = new ArrayList<>();

        listViewlistaContactos = (ListView) findViewById(R.id.lisViewContactos);

        importarContactos();

        ContactosAdapter itemsAdapter = new ContactosAdapter(MainActivity.this,listaContactos);
        listViewlistaContactos.setAdapter(itemsAdapter);
        listViewlistaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick (AdapterView < ? > parent, View view,int position, long id) {
                Intent intent = new Intent(MainActivity.this,InformacionContactoActivity.class);
                intent.putExtra("contacto", (Serializable) listaContactos.get(position));
                startActivity(intent);
            }
        });





    }

    public void importarContactos()
    {
        ProgressDialog progressDialog =  new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Cargando Contactos");
        progressDialog.setTitle("Espera...");
        progressDialog.show();

        StringBuilder builder = new StringBuilder();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);
        ContactoEntity contacto = null;
        if(cursor.getCount() > 0)
        {
            while(cursor.moveToNext())
            {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));

                if(hasPhoneNumber > 0)
                {
                    Cursor cursor2 = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[] {id},null);

                    while(cursor2.moveToNext())
                    {
                        String phoneNumber = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        builder.append("Contacto: ").append(name).append("Numero : ").append(phoneNumber).append("\n\n");
                        contacto = new ContactoEntity(name,phoneNumber);

                        if(!listaContactos.contains(contacto))
                        {
                            listaContactos.add(contacto);
                        }
                    }
                    cursor2.close();
                }
            }

        }
        cursor.close();
        progressDialog.hide();
        //textViewContactos.setText(builder);
        //Toast.makeText(this,"Si pasa importar contactos",Toast.LENGTH_SHORT).show();
    }

}
