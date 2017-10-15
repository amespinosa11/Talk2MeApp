package com.talk2me.talk2me;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int PERMISSION_REQUEST_CODE = 1;

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
        if(!autenticar())
        {
            Toast.makeText(this,"Registro fallido",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ingresar();
        }
    }

    public boolean autenticar()
    {
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

    public void ingresar()
    {
        if (checkPermission()) {
            Log.e("permission", "Permission already granted.");
            //Se pasa a la ventana principal
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            //Falta mandar esta información y guardarla en una base de datos
        } else {

            //If your app doesn’t have permission to access external storage, then call requestPermission//
            requestPermission();
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ingresar();
                    //Toast.makeText(RegistrarseActivity.this,
                    //      "Permission accepted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this,
                            "Permission denied", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_CONTACTS);

        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            return false;
        }
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == btnIniciarSesion.getId())
        {
           asignarDatos();
        }
        if(view.getId() == btnRegistrarse.getId())
        {
            Intent intent = new Intent(this,RegistrarseActivity.class);
            startActivity(intent);
        }
    }

}
