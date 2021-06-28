package com.example.infinity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class Proveedor extends AppCompatActivity {

    private ListView listViewProveedor;

    private String nombres [] = {"PROVEEDOR1","PROVEEDOR2","PROVEEDOR3"};
    private double debito [] = {2000,12333.4,1323.59};
    private double debitoInicial[]={};
    private String ubicacion[]={"UBICACION1","UBICACION2","UBICACION3"};
    private String numeroCelular[]={"MOVIL1","MOVIL2","MOVIL3"};

    private Button btnNuevoProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);

        btnNuevoProveedor= findViewById(R.id.btnNuevoProveedor);

        if(nombres.length==0){
            Toast.makeText(this, "NO HAY PROVEEDORES",
                    Toast.LENGTH_SHORT).show();
        }

        listViewProveedor = findViewById(R.id.listViewProveedores);

        ArrayAdapter<String> AdaptadorProveedores = new ArrayAdapter<String>(this, R.layout.list_view, nombres);

        listViewProveedor.setAdapter(AdaptadorProveedores);

        listViewProveedor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(Proveedor.this , R.style.MyDialogTheme);
                alerta.setMessage( "Ubicación :    "+ubicacion[i]+"\n"+"Celular :         " +numeroCelular[i]+"\n"+"Debito: S/.    "+debito[i]);
                alerta.setCancelable(false)
                        .setPositiveButton("Volver",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                AlertDialog titulo = alerta.create();

                titulo.setTitle((String) listViewProveedor.getItemAtPosition(i));
                titulo.show();


            }
        });




    }
    public void NuevoProveedor(View view){
        Intent NuevoProveedor=new Intent(this, NuevoProveedor.class);
        startActivity(NuevoProveedor);

    }



}
