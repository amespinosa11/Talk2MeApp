package com.talk2me.talk2me;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class InformacionContactoActivity extends AppCompatActivity {

    private ContactoEntity contactoEntity;

    private TextView textViewNombreContacto;

    private TextView textViewNumeroContacto;

    private Button btnIniciarLlamada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_contacto);

        if(getIntent()!=null && getIntent().getSerializableExtra("contacto")!=null)
        {
            contactoEntity = (ContactoEntity) getIntent().getSerializableExtra("contacto");
        }

        textViewNombreContacto = (TextView) findViewById(R.id.textViewNomContacto);
        textViewNombreContacto.setText(contactoEntity.getNombre());

        textViewNumeroContacto = (TextView) findViewById(R.id.textViewNumContacto);
        textViewNumeroContacto.setText(contactoEntity.getNumeroCelular());

        btnIniciarLlamada = (Button) findViewById(R.id.btnIniciarLlamada);


    }
}
