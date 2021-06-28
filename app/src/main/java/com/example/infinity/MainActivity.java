package com.example.infinity;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private byte contador=3;
    private String pin="1";

    private EditText txtPin;
    private TextInputLayout tilPin;
    private Button LogIn;
    private String pinIngresado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtPin=findViewById(R.id.txtPass);
        LogIn=findViewById(R.id.btnIniciarSesion);
        tilPin = findViewById(R.id.tilPass);

        txtPin.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus ) {
                    if (hasFocus) {
                    tilPin.setError(null);
                }
            }

        });

    }

    private boolean esContraseniaValida(){
        pinIngresado=txtPin.getText().toString();
        if(pinIngresado.equals(pin)){
            return true;
        }else{
            if(contador>0) {
                tilPin.setError("El PIN no esta asociado a ninguna cuenta");
                Toast.makeText(this, ("Tiene " + contador + " intento(s)."),
                        Toast.LENGTH_SHORT).show();
                contador--;

                new Handler().postDelayed(new Runnable() {

                    public void run() {
                        tilPin.setError(null);
                       }
                }, 2200); //2200 millisegundos = 2.2 segundos.
            }else{
                finish();
            }

        return false;
     }
    }




   public void Principal(View view){
     Intent menu=new Intent(this, MenuMarket.class);
       if(esContraseniaValida()){
           startActivity(menu);
           finish();
       }
    }

    public void Registro(View view){
        Intent registro=new Intent(this, Registro.class);
        startActivity(registro);
        finish();
    }

    public void ContreniaOlvidada(View view){
        Intent contraseniaOlvidada=new Intent(this, PinOlvidado.class);
        startActivity(contraseniaOlvidada);
        finish();
    }

}
