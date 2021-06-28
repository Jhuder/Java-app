package com.example.infinity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class Pago extends AppCompatActivity {
    private String nombres [] = {"NOMBREA","NOMBREB","NOMBREC"};
    private Spinner spnrProveedor;
    private EditText txtPin;
    private EditText txtMontoPagar;
    private String pin="1";
    private String pinIngresado;

    private byte contador=3;

    private TextInputLayout tilPinPago;
    private TextInputLayout tilMontoPago;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        txtPin=findViewById(R.id.txtPinPago);
        txtMontoPagar=findViewById(R.id.txtMontoPagar);

        tilPinPago = findViewById(R.id.tilPinRegistroPago);
        tilMontoPago = findViewById(R.id.tilMontoPago);

        spnrProveedor= findViewById(R.id.spnrNombreProveedor);

        Button guardarPago =  findViewById(R.id.btnGuardarPago);
        Button cancelarPago = findViewById(R.id.btnCancelarPago);

        //Pago
        txtMontoPagar.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ){
                if (txtMontoPagar.getText().length()==0) {
                    txtMontoPagar.setError("Campo Obligatorio");
                }
                else if (txtMontoPagar.getText().toString().charAt(0)=='.'){
                    txtMontoPagar.setError("Monto no puede iniciar con punto decimal");
                }
                else{
                    txtMontoPagar.setError(null);
                }
            }

        });



        if(nombres.length!=0){

            ArrayAdapter adapter = new ArrayAdapter(this,
            android.R.layout.simple_spinner_item, nombres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnrProveedor.setAdapter(adapter);


            spnrProveedor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                    Toast.makeText(getApplicationContext(), "Proveedor:" +
                                    adapterView.getItemAtPosition(pos),
                            Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }

            });

        }else{
            Toast.makeText(getApplicationContext(), "No hay proveedores",
                    Toast.LENGTH_SHORT).show();

            finish();

        }



        guardarPago.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(esContraseniaValida() && esMontoValido()) {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(Pago.this);

                            alerta.setMessage("¿Desea guardar cambios?");
                            alerta.setCancelable(false)
                                    .setPositiveButton("SI",

                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(Pago.this, "Pago realizado con existo",
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
                            titulo.setTitle("Pago");
                            titulo.show();

                        }

                    }
                }
        );

        cancelarPago.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cancelado();
                    }
                }
        );

    }

    public void Cancelado(){
        finish();
        Toast.makeText(Pago.this, "Cancelado",
                Toast.LENGTH_SHORT).show();
    }
    public void Menu(){
        Intent principal=new Intent(this, MenuMarket.class);
        startActivity(principal);
        finish();

    }

    private boolean esContraseniaValida(){
        pinIngresado=txtPin.getText().toString();
        if(pinIngresado.equals(pin)){
            return true;
        }else{
            if(contador>0) {
                tilPinPago.setError("El PIN no esta asociado a ninguna cuenta");
                Toast.makeText(this, ("Tiene " + contador + " intento(s)."),
                        Toast.LENGTH_SHORT).show();
                contador--;

                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        tilPinPago.setError(null);
                    }
                }, 2200); //2200 millisegundos = 2.2 segundos.
            }else{
                Menu();
            }

            return false;
        }
    }
    private boolean esMontoValido(){

        if((txtMontoPagar.getText().toString().charAt(0))=='.'){
            tilMontoPago.setError("Monto incorrecto");
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    tilMontoPago.setError(null);

                }
            }, 1200); //2200 millisegundos = 2.2 segundos.
            return false;
        }
        else if(txtMontoPagar.length()!=0) {
            return true;

        }
        else{
            txtMontoPagar.setError(null);
            tilMontoPago.setError("Ingrese monto");
            new Handler().postDelayed(new Runnable() {

                public void run() {
                    tilMontoPago.setError(null);
                    txtMontoPagar.setError("Campo obligatorio");

                }
            }, 2200); //2200 millisegundos = 2.2 segundos.
            return false;
        }
    }


}
