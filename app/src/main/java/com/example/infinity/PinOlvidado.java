package com.example.infinity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class PinOlvidado extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtCorreo;


    private String nombre ;
    private String apellido;
    private String correo;

    private Button botonRecuperarPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_olvidado);

        txtNombre = findViewById(R.id.txtNombreRecuperar);
        txtApellidos = findViewById(R.id.txtApellidosRecuperar);
        txtCorreo = findViewById(R.id.txtCorreoRecuperar);



        botonRecuperarPin = findViewById(R.id.btnRecuperar);

        txtNombre.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        txtApellidos.setFilters(new InputFilter[] {new InputFilter.AllCaps()});


        //NOMBRE
        txtNombre.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
                if (!patron.matcher(txtNombre.getText()).matches()) {
                    txtNombre.setError("Nombre incorrecto");

                }
                else if (txtNombre.getText().length()==0){
                    txtNombre.setError("Campo Obligatorio");
                }else{
                    txtNombre.setError(null);
                }
            }

        });

        //APELLIDO
        txtApellidos.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
                if (!patron.matcher(txtApellidos.getText()).matches()) {
                    txtApellidos.setError("Apellidos incorrectos");

                }
                else if (txtApellidos.getText().length()==0){
                    txtApellidos.setError("Campo Obligatorio");
                }else{
                    txtApellidos.setError(null);
                }
            }

        });

        //CORREO
        txtCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtCorreo.getText().length()==0){
                    txtCorreo.setError("Campo Obligatorio");
                }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(txtCorreo.getText().toString()).matches()){

                    txtCorreo.setError("Correo Invalido");
                }else{
                    txtCorreo.setError(null);
                }
            }

        });


        botonRecuperarPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();

            }
        });

    }



    private boolean esCorreoValido(String correo) {

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            txtCorreo.setError("Dato incorrecto");
            return false;
        }
        return true;

    }
    private boolean esNombreValido(String nombre) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(nombre).matches() || nombre.length() > 40) {
            txtNombre.setError("Dato Incorrecto");
            return false;
        }

        return true;
    }

    private boolean esApellidoValido(String apellido) {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(apellido).matches() || apellido.length() > 50) {
            txtApellidos.setError("Dato Incorrecto");
            return false;
        }

        return true;
    }

    private void volverLogin() {

        new Handler().postDelayed(new Runnable() {

            public void run() {
                //----------------------------

                Intent intent = new Intent(PinOlvidado.this, MainActivity.class);
                startActivity(intent);
                finish();
                //----------------------------

            }
        }, 2000); //2000 millisegundos = 2 segundos.
    }


    private void validarDatos() {
        nombre = txtNombre.getText().toString();
        apellido=txtApellidos.getText().toString();
        correo=txtCorreo.getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esApellidoValido(apellido);
        boolean c = esCorreoValido(correo);


        if (a && b && c) {
            // OK, se pasa a la siguiente acción
            desabilitarCampos();
            botonRecuperarPin.setEnabled(false);
            Toast.makeText(this, "Revise bandeja de entrada del correo \n proporcionado y vuelva a iniciar sesion",
                    Toast.LENGTH_LONG).show();
            volverLogin();

        }

    }

    private void desabilitarCampos(){
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCorreo.setEnabled(false);
    }
}
