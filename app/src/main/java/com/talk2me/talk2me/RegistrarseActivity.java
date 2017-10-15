package com.talk2me.talk2me;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class RegistrarseActivity extends AppCompatActivity implements View.OnClickListener,ActivityCompat.OnRequestPermissionsResultCallback
{
    private static final int PERMISSION_REQUEST_CODE = 1;

    private String nombre;

    private String apellido;

    private long celular;

    private String contrasenia;

    private String contraseniaConfirmar;

    private String email;

    private AutoCompleteTextView autoCompleteTextViewNombre;

    private AutoCompleteTextView autoCompleteTextViewApellido;

    private AutoCompleteTextView autoCompleteTextViewCelular;

    private AutoCompleteTextView autoCompleteTextViewEmail;

    private AutoCompleteTextView autoCompleteTextViewContrasenia;

    private AutoCompleteTextView autoCompleteTextViewContraseniaConfirmar;

    private Button btnRegistrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        autoCompleteTextViewNombre = (AutoCompleteTextView) findViewById(R.id.nombre);

        autoCompleteTextViewApellido = (AutoCompleteTextView) findViewById(R.id.apellido);

        autoCompleteTextViewCelular = (AutoCompleteTextView) findViewById(R.id.celular);

        autoCompleteTextViewEmail = (AutoCompleteTextView) findViewById(R.id.emailRegistrarse);

        autoCompleteTextViewContrasenia = (AutoCompleteTextView) findViewById(R.id.contrasenia);

        autoCompleteTextViewContraseniaConfirmar = (AutoCompleteTextView) findViewById(R.id.contrasenia2);

        btnRegistrase = (Button) findViewById(R.id.btnRegistrarse2);
        btnRegistrase.setOnClickListener(this);

        findViewById(R.id.contraintRegistrarseLayout).setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        });
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId() == btnRegistrase.getId())
        {
            registrar();
        }

    }

    public void registrar()
    {
        nombre = autoCompleteTextViewNombre.getText().toString();
        apellido = autoCompleteTextViewApellido.getText().toString();

        String c = autoCompleteTextViewCelular.getText().toString();
        if(c.isEmpty())
        {
            celular = 0;
        }
        else
        {
            celular = Long.valueOf(c);
        }

        email = autoCompleteTextViewEmail.getText().toString();

        contrasenia = autoCompleteTextViewContrasenia.getText().toString();
        contraseniaConfirmar = autoCompleteTextViewContraseniaConfirmar.getText().toString();

        if(!validar())
        {
            Toast.makeText(this,"Registro fallido",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ingresar();
        }
    }

    public void ingresar()
    {
        if (checkPermission()) {
            Log.e("permission", "Permission already granted.");
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {

    //If your app doesn’t have permission to access external storage, then call requestPermission//

            requestPermission();
            //Se pasa a la ventana principal

            //Falta mandar esta información y guardarla en una base de datos
        }

    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(RegistrarseActivity.this,
                            "Permission accepted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegistrarseActivity.this,
                            "Permission denied", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


    private boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(RegistrarseActivity.this, Manifest.permission.READ_CONTACTS);

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

    public boolean validar()
    {
        boolean v = true;
        if(nombre.isEmpty() || nombre.length() > 50)
        {
            autoCompleteTextViewNombre.setError("Ingrese un nombre valido");
            v = false;
        }
        if(apellido.isEmpty() || apellido.length() > 40)
        {
            autoCompleteTextViewApellido.setError("Ingrese un apellido valido");
            v = false;
        }
        if(celular <10)
        {
            autoCompleteTextViewCelular.setError("Ingrese un celular valido");
            v = false;
        }
        if(email.isEmpty() || email.length() > 30 || !Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            autoCompleteTextViewEmail.setError("Ingrese una un correo valido");
            v = false;
        }
        if(contrasenia.isEmpty() || contraseniaConfirmar.isEmpty() || contrasenia.length() < 8 || contraseniaConfirmar.length() < 8)
        {
            autoCompleteTextViewContrasenia.setError("Contraseña debe contener mínimo 8 caracteres");
            autoCompleteTextViewContraseniaConfirmar.setError("Contraseña debe contener mínimo 8 caracteres");
            v = false;
        }
        if(!contrasenia.equals(contraseniaConfirmar))
        {
            autoCompleteTextViewContraseniaConfirmar.setError("Contraseñas no coinciden");
            autoCompleteTextViewContrasenia.setError("Contraseñas no coinciden");
            v = false;
        }

        return v;
    }
}
