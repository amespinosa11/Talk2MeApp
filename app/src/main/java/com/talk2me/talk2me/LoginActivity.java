package com.talk2me.talk2me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String email;

    private String contrasenia;

    private AutoCompleteTextView autoCompleteTextViewEmailLogin;

    private AutoCompleteTextView autoCompleteTextViewContrasenia;

    private Button btnIniciarSesion;

    private Button btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnIniciarSesion = (Button) findViewById(R.id.btnIniciarSesion);
        btnIniciarSesion.setOnClickListener(this);

        btnRegistrarse = (Button) findViewById(R.id.btnRegistrarse);
        btnRegistrarse.setOnClickListener(this);

        autoCompleteTextViewEmailLogin = (AutoCompleteTextView) findViewById(R.id.email);
        autoCompleteTextViewContrasenia = (AutoCompleteTextView) findViewById(R.id.password);

        findViewById(R.id.constraintLoginLayout).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }

    public void asignarDatos()
    {
        email = autoCompleteTextViewEmailLogin.getText().toString();
        contrasenia = autoCompleteTextViewContrasenia.getText().toString();
    }

    public boolean autenticar()
    {
        asignarDatos();
        boolean valido = false;
        if(email.isEmpty())
        {
            autoCompleteTextViewEmailLogin.setError("Ingrese un correo valido");
        }
        if(contrasenia.isEmpty())
        {
            autoCompleteTextViewContrasenia.setError("Contraseña incorrecta");
        }
        if(email.equals("talk@me.com"))
        {
            valido = true;
        }
        return valido;
    }


    @Override
    public void onClick(View view)
    {
        if(view.getId() == btnIniciarSesion.getId())
        {
            if(autenticar())
            {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }
        if(view.getId() == btnRegistrarse.getId())
        {
            Intent intent = new Intent(this,RegistrarseActivity.class);
            startActivity(intent);
        }
    }
}
