package com.example.infinity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Compra extends AppCompatActivity {
    private ListView listViewProductos;
    private ListView listViewProductos2;
    private String nombres [] = {"NOMBREA","NOMBREB","NOMBREC"};
    private String productos [] = {"PRODUCTO1","PRODUCTO2","PRODUCTO3","PRODUCTO4","PRODUCTO5"};
    private String productos2 [] = {"PRODUCTO6","PRODUCTO7","PRODUCTO8","PRODUCTO9"};

    private Spinner spnrProveedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra);


        spnrProveedor= findViewById(R.id.spnrNombreProveedor);


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


        listViewProductos = findViewById(R.id.listViewProductos);

        ArrayAdapter<String> AdaptadorProducto = new ArrayAdapter<String>(this, R.layout.grade_view_compra, productos);

        listViewProductos.setAdapter(AdaptadorProducto);

        listViewProductos2 = findViewById(R.id.listViewProductos2);

        ArrayAdapter<String> AdaptadorProducto2 = new ArrayAdapter<String>(this, R.layout.grade_view_compra, productos2);

        listViewProductos2.setAdapter(AdaptadorProducto2);


    }
}
