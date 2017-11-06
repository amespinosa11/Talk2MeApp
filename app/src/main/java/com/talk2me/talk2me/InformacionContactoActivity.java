package com.talk2me.talk2me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;

public class InformacionContactoActivity extends AppCompatActivity implements View.OnClickListener{

    private ContactoEntity contactoEntity;

    private TextView textViewNombreContacto;

    private TextView textViewNumeroContacto;

    private Button btnIniciarLlamada;

    private Button btnIniciarLlamadaNormal;

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
        btnIniciarLlamada.setOnClickListener(this);

        btnIniciarLlamadaNormal = (Button) findViewById(R.id.btnIniciarLlamadaNormal);
        btnIniciarLlamadaNormal.setOnClickListener(this);


    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == btnIniciarLlamada.getId())
        {
            Intent intent = new Intent(InformacionContactoActivity.this,LlamadaActivity.class);
            //intent.putExtra("contacto", (Serializable) contactoEntity);
            startActivity(intent);
        }
        if(view.getId() == btnIniciarLlamadaNormal.getId())
        {
            Intent intent =  new Intent(InformacionContactoActivity.this,LlamadaNormalActivity.class);
            startActivity(intent);
        }

    }
}
