package com.talk2me.talk2me;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class LlamadaActivity extends AppCompatActivity implements View.OnClickListener
{
    private ImageView imageView1;

    private ImageView imageView2;

    private ImageView imageView3;

    private ImageView imageView4;

    private ImageView imageView5;

    private ImageView imageView6;

    private TextView textViewHola;

    private TextView textViewBuenosDias;

    private TextView textViewComoEstas;

    private TextView textViewBien;

    private TextView textViewYTu;

    private TextView textViewAdios;

    private ContactoEntity contactoEntity;

    private TextView textViewNombreContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llamada);

        imageView1 = (ImageView) findViewById(R.id.imagePrueba1);
        imageView1.setOnClickListener(this);
        imageView2 = (ImageView) findViewById(R.id.imagePrueba2);
        imageView2.setOnClickListener(this);
        imageView3 = (ImageView) findViewById(R.id.imagePrueba3);
        imageView3.setOnClickListener(this);
        imageView4 = (ImageView) findViewById(R.id.imagePrueba4);
        imageView4.setOnClickListener(this);
        imageView5 = (ImageView) findViewById(R.id.imagePrueba5);
        imageView5.setOnClickListener(this);
        imageView6 = (ImageView) findViewById(R.id.imagePrueba6);
        imageView6.setOnClickListener(this);

        textViewNombreContacto = (TextView) findViewById(R.id.textViewNombreUsuarioLlamada);
        //textViewNombreContacto.setText(contactoEntity.getNombre());

        textViewHola = (TextView) findViewById(R.id.textHola);
        textViewBuenosDias = (TextView) findViewById(R.id.textBuenosDias);
        textViewComoEstas = (TextView) findViewById(R.id.textComoestas);
        textViewBien = (TextView) findViewById(R.id.textBien);
        textViewYTu = (TextView) findViewById(R.id.yTu);
        textViewAdios = (TextView) findViewById(R.id.Adios);

        //if(getIntent()!=null && getIntent().getSerializableExtra("contacto")!=null)
        //{
          //  contactoEntity = (ContactoEntity) getIntent().getSerializableExtra("contacto");
        //}

        //Se asignan la imagenes
        //Picasso.with(LlamadaActivity.this)
          //      .load("https://restorando-res.cloudinary.com/image/upload/s--198bKmFu--/c_fit,f_auto,h_500,w_700/v1438704568/restaurant_photos/r/9013/73529/restaurante_marquez_zona-t_hfa_1827.jpg")
            //    .resize(50,50)
              //  .centerCrop()
               // .into(imageView1);
        imageView1.setImageResource(R.drawable.captura);

        imageView2.setImageResource(R.drawable.buenosdias);

        imageView3.setImageResource(R.drawable.comoesta);

        imageView4.setImageResource(R.drawable.bien);

        imageView5.setImageResource(R.drawable.tu);

        imageView6.setImageResource(R.drawable.gracias);
    }

    @Override
    public void onClick(View view)
    {
        if(imageView1.getId() == view.getId())
        {
            textViewHola.setText("Hola");
        }
        if(imageView2.getId() == view.getId())
        {
            textViewBuenosDias.setText("Buenos días");
        }
        if(imageView3.getId() == view.getId())
        {
            textViewComoEstas.setText("Cómo estás?");
        }
        if(imageView4.getId() == view.getId())
        {
            textViewBien.setText("Bien");
        }
        if(imageView5.getId() == view.getId())
        {
            textViewYTu.setText("y tú?");
        }
        if(imageView6.getId() == view.getId())
        {
            textViewAdios.setText("Adios");
        }
    }
}
