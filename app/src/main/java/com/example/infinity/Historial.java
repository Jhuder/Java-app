package com.example.infinity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Historial extends AppCompatActivity {
    private String nombres [] = {"NOMBREA","NOMBREB","NOMBREC"};
    private Spinner spnrProveedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        spnrProveedor= findViewById(R.id.spnrNombreProveedorHistorial);


        if(nombres.length!=0){

            ArrayAdapter adapter = new ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, nombres);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spnrProveedor.setAdapter(adapter);
        }
        else{
            Toast.makeText(getApplicationContext(), "No hay proveedores",
                    Toast.LENGTH_SHORT).show();

        }
    }
}
