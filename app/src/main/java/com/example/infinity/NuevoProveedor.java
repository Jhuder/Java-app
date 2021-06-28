package com.example.infinity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class NuevoProveedor extends AppCompatActivity {
    //Text
    private EditText txtNombre;
    private EditText txtMontoInicial;
    private EditText txtUbicacion;
    private EditText txtCelular;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_proveedor);

        txtNombre = findViewById(R.id.txtNombreProveedor);
        txtMontoInicial = findViewById(R.id.txtMontoInicial);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtCelular = findViewById(R.id.txtCelular);

        txtNombre.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        txtUbicacion.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

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
        //MONTO
        txtMontoInicial.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtMontoInicial.getText().length()==0) {
                    txtMontoInicial.setError("Campo Obligatorio");
                }
                else if (txtMontoInicial.getText().toString().charAt(0)=='.'){
                    txtMontoInicial.setError("Monto no puede iniciar con punto decimal");
                }
                else{
                    txtMontoInicial.setError(null);
                }
            }

        });

        //UBICACION
        txtUbicacion.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                 if (txtUbicacion.getText().length()!=0) {


                     Pattern patron = Pattern.compile("^[a-zA-Z0-9 ]+$");
                     if (!patron.matcher(txtUbicacion.getText()).matches()) {
                         txtUbicacion.setError("Ubicacion incorrecta");
                     } else {
                         txtUbicacion.setError(null);
                     }
                 }
            }

        });

        //NUMERO
        txtCelular.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtCelular.getText().length()!=0) {

                    if (txtCelular.getText().length() > 13 || txtCelular.getText().length() < 9) {
                        txtCelular.setError("Numero no valido");

                    } else {
                        txtCelular.setError(null);
                    }
                }else{
                    txtCelular.setError(null);
                }
            }

        });



        Button guardarProveedor =  findViewById(R.id.btnGuardarProveedor);
        Button cancelarProveedor = findViewById(R.id.btnCancelarProveedor);

        guardarProveedor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(esNombreValido()&& esMontoValido()&&esUbicacionValido()&&esNumeroValido()) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(NuevoProveedor.this);

                            alerta.setMessage("¿Desea guardar cambios?");
                            alerta.setCancelable(false)
                                    .setPositiveButton("SI",

                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(NuevoProveedor.this, "Proveedor Guardado",
                                                            Toast.LENGTH_SHORT).show();
                                                    Menu();
                                                }
                                            })
                                    .setNegativeButton("NO",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Cancelado();

                                                }
                                            });

                            AlertDialog titulo = alerta.create();
                            titulo.setTitle("Nuevo Proveedor");
                            titulo.show();


                        }
                    }
                }
        );
        cancelarProveedor.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(NuevoProveedor.this, "Cancelado",
                                Toast.LENGTH_SHORT).show();
                        Cancelado();
                    }
                }
        );


    }

    private boolean esNombreValido() {
        Pattern patron = Pattern.compile("^[a-zA-Z ]+$");
        if (!patron.matcher(txtNombre.getText().toString()).matches() || txtNombre.getText().toString().length() > 90) {
            txtNombre.setError("Dato Incorrecto");
            return false;
        }

        return true;
    }
    private boolean esMontoValido(){

        if((txtMontoInicial.getText().toString().charAt(0))=='.'){

            return false;
        }
        else if(txtMontoInicial.length()!=0) {
            return true;

        }
        else{
             return false;
        }
    }

    private boolean esUbicacionValido() {
        if(txtUbicacion.getText().toString().length()==0){
            return true;
        }else{
        Pattern patron = Pattern.compile("^[a-zA-Z0-9 ]+$");
        if (!patron.matcher(txtUbicacion.getText().toString()).matches()) {
            txtUbicacion.setError("Dato Incorrecto");
            return false;
        }

        return true;
    }

    }

    private boolean esNumeroValido() {
        if (txtCelular.getText().toString().length() == 0) {
            return true;
        } else {
            if (!android.util.Patterns.PHONE.matcher(txtCelular.getText().toString()).matches()) {
                txtCelular.setError("Dato Incorrecto");
                return false;

            }
            return true;

        }
    }
    public void Menu(){
        Intent principal=new Intent(this, MenuMarket.class);
        startActivity(principal);
        finish();
    }

    public void Cancelado(){
        Toast.makeText(NuevoProveedor.this, "Cancelado",
                Toast.LENGTH_SHORT).show();
        finish();
    }


}
