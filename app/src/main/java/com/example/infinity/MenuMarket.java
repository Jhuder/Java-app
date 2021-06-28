package com.example.infinity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MenuMarket extends AppCompatActivity {

   private TextView mensajeConexion;
   private TextView usuario;
   private TextView fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //FINDVIEW
        usuario= findViewById(R.id.lblUsuario);
        fecha= findViewById(R.id.lblFecha);
        mensajeConexion= findViewById(R.id.lblNombre);
        datos();
        String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        fecha.setText(date);

    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.desplegable_bar,  menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean  onOptionsItemSelected(MenuItem item){
        int id=item.getItemId();
        if(id==R.id.barAcercaDe){
            AlertDialog.Builder alerta = new AlertDialog.Builder(MenuMarket.this);
            alerta.setMessage( "2019");
            alerta.setCancelable(true);

            AlertDialog titulo = alerta.create();

            titulo.setTitle("MarketDay");

            titulo.show();
        }

        return super.onOptionsItemSelected(item);

    }

    public static boolean compruebaConexion(Context context){
        boolean connected = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {

            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }

    public void datos(){
        if (compruebaConexion(getApplicationContext())){
            mensajeConexion.setText("Conectado");
            mensajeConexion.setTextSize(14);
            mensajeConexion.setTextColor(Color.parseColor("#7ee827"));


        }else{
            //No existe conectividad.
            Toast.makeText(this, "Conectese a una red inalambrica \n o active datos moviles",
                    Toast.LENGTH_LONG).show();
            mensajeConexion.setText("No hay conexion");
            mensajeConexion.setTextSize(16);
            mensajeConexion.setTextColor(Color.parseColor("#FF0000"));

        }

    }

    public void Proveedor(View view){
        Intent proveedor=new Intent(this, Proveedor.class);
        startActivity(proveedor);

    }
    public void Comprar(View view){
        Intent compra=new Intent(this, Compra.class);
        startActivity(compra);

    }
    public void Pagar(View view){
        Intent pago=new Intent(this, Pago.class);
        startActivity(pago);

    }

    public void Clientes(View view){
        Intent cliente=new Intent(this, Cliente.class);
        startActivity(cliente);
    }
    public void HistorialCompras(View view){
        Intent historial=new Intent(this, Historial.class);
        startActivity(historial);
    }


}
