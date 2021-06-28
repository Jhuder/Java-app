package com.example.infinity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {


    private String mensaje="Desea salir sin guardar cambios ?";
    //Layout
    private TextInputLayout tilNombre;
    private TextInputLayout tilApellidos;
    private TextInputLayout tilNumero;
    private TextInputLayout tilCorreo;
    private TextInputLayout tilPin1;
    private TextInputLayout tilPin2;


    //Text
    private EditText txtNombre;
    private EditText txtApellidos;
    private EditText txtNumero;
    private EditText txtCorreo;
    private EditText txtPin1;
    private EditText txtPin2;

    //botones
    private Button botonLogin;
    private Button botonRegistro;

    private String nombre ;
    private String apellido;
    private String correo;
    private String numero;
    private String pin1;
    private String pin2;



    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);



        //Layout
        tilNombre= findViewById(R.id.tilNombre);
        tilApellidos= findViewById(R.id.tilApellidos);
        tilNumero = findViewById(R.id.tilNumero);
        tilCorreo = findViewById(R.id.tilCorreo);
        tilPin1 = findViewById(R.id.tilPin1);
        tilPin2 = findViewById(R.id.tilPin2);

        //text
        txtNombre = findViewById(R.id.txtNombre);
        txtApellidos = findViewById(R.id.txtApellidos);
        txtNumero = findViewById(R.id.txtNumero);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPin1 = findViewById(R.id.txtPin1);
        txtPin2 = findViewById(R.id.txtPin2);

        //BOTONES
        botonLogin=findViewById(R.id.btnIngresar);
        botonRegistro = findViewById(R.id.btnRegistro);

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

        //NUMERO
        txtNumero.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtNumero.getText().length()==0){
                    txtNumero.setError("Campo Obligatorio");
                }else if(txtNumero.getText().length()>13 || txtNumero.getText().length()<9){
                    txtNumero.setError("Numero inexistente");

                }else{
                    txtNumero.setError(null);
                }
            }

        });

        //CORREO
        txtCorreo.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtCorreo.getText().length()==0){
                    txtCorreo.setError("Campo Obligatorio");
                }else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(tilCorreo.getEditText().getText().toString()).matches()){

                    txtCorreo.setError("Correo Invalido");
                }else{
                    txtCorreo.setError(null);
                }
            }

        });

        //PIN1
        txtPin1.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override

            public void onFocusChange(View v, boolean hasFocus ) {
                String pin1=tilPin1.getEditText().getText().toString();
                esContraseniaValida1(pin1);

                if (hasFocus) {
                 tilPin1.setError(null);
                }
                if(!hasFocus && pin1.length()==8){
                    tilPin1.setError(null);
                }


            }

        });

        //PIN2
        txtPin2.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                pin1=tilPin1.getEditText().getText().toString();
                pin2=tilPin2.getEditText().getText().toString();
                esContraseniaValida2(pin1,pin2);

                if (hasFocus) {
                    tilPin2.setError(null);
                }
            }

        });



        // Referencia Botón Registro

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarDatos();
            }
        });

        // Referencia Botón Login



            botonLogin.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);

                        alerta.setMessage(mensaje);
                            alerta.setCancelable(false)
                            .setPositiveButton("SI",

                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Login();
                                            finish();
                                        }
                                    })
                            .setNegativeButton("CANCELAR",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });

                    AlertDialog titulo = alerta.create();
                    titulo.setTitle("Registro");
                    titulo.show();
                }


            });


    }

    public void Login(){
        Intent login=new Intent(this, MainActivity.class);
        startActivity(login);
        finish();
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

    private boolean esNumeroValido(String numero) {
        if (!android.util.Patterns.PHONE.matcher(numero).matches()) {
            txtNumero.setError("Dato Incorrecto");
            return false;

        }
        return true;

    }



    private boolean esCorreoValido(String correo) {

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            txtCorreo.setError("Dato incorrecto");
            return false;
        }
        return true;

    }

    private boolean esContraseniaValida1(String contrasenia1){
        if (contrasenia1.length()==0){
            tilPin1.setError("Ingrese PIN");
            return false;
        }
        else if(contrasenia1.length()!=8 ) {
            tilPin1.setError("El PIN debe contener 8 caracteres");
            return false;
        }
        return true;
    }


    private boolean esContraseniaValida2(String contrasenia1, String contrasenia2){
        if (contrasenia2.length()==0){
            tilPin2.setError("Ingrese PIN");
            return false;
        }
        else if(contrasenia2.length()==8 && contrasenia1.equals(contrasenia2)){
            return true;
        }
        else if(contrasenia2.length()!=8 && contrasenia1.equals(contrasenia2)){
            tilPin2.setError("El PIN debe contener 8 digitos");
            return false;

        }else{
            tilPin2.setError("El PIN no es igual");
            return false;
        }

    }

    private void validarDatos() {
         nombre = tilNombre.getEditText().getText().toString();
         apellido=tilApellidos.getEditText().getText().toString();
         correo=tilCorreo.getEditText().getText().toString();
         numero=tilNumero.getEditText().getText().toString();
         pin1=tilPin1.getEditText().getText().toString();
         pin2=tilPin2.getEditText().getText().toString();

        boolean a = esNombreValido(nombre);
        boolean b = esApellidoValido(apellido);
        boolean c = esCorreoValido(correo);
        boolean d = esNumeroValido(numero);
        boolean e = esContraseniaValida1(pin1);
        boolean f = esContraseniaValida2(pin1,pin2);

        if(txtPin1.isFocusable() && e && f){
            tilPin1.setError(null);
            tilPin2.setError(null);
        }


        if (a && b && c && d && e && f) {
            // OK, se pasa a la siguiente acción
            desabilitarCampos();
            textLogin();
            mensaje="INICIAR SESION";
             botonRegistro.setEnabled(false);
            Toast.makeText(this, "Se guardo el registro", Toast.LENGTH_LONG).show();
        }

    }

    private void desabilitarCampos(){
        txtNombre.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtNumero.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtPin1.setEnabled(false);
        txtPin2.setEnabled(false);
    }

 private void textLogin(){
     AlertDialog.Builder alerta = new AlertDialog.Builder(Registro.this);

     alerta.setMessage("INICIAR SESION");
     alerta.setCancelable(false)
             .setPositiveButton("SI",
                     new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             Login();
                             finish();
                         }
                     })
             .setNegativeButton("CANCELAR",
                     new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             dialog.cancel();
                         }
                     });

     AlertDialog titulo = alerta.create();
     titulo.setTitle("LOGIN");
     titulo.show();
 }



}
