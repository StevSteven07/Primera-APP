package com.example.nivelacionsprint2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.nivelacionsprint2.Adaptadores.ProductoAdapter;
import com.example.nivelacionsprint2.DB.DBHelper;
import com.example.nivelacionsprint2.Entidades.Producto;
import com.example.nivelacionsprint2.Servicios.ProductoService;

import java.util.ArrayList;

public class Catalogo extends AppCompatActivity {
    private ListView listViewProducts;
    private ProductoAdapter productoAdapter;
    private ArrayList<Producto> arrayProducts;
    private DBHelper dbHelper;
    private ProductoService productoService;

    @Override
    protected void onCreate(Bundle  savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        dbHelper= new DBHelper(this);
        productoService= new ProductoService();

        arrayProducts= new ArrayList<>();
        arrayProducts= productoService.cursorToArray(dbHelper.getProducts());

        productoAdapter= new ProductoAdapter(this, arrayProducts);
        listViewProducts= (ListView) findViewById(R.id.listViewProducts);

        listViewProducts.setAdapter(productoAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemAdd:
                Intent intent= new Intent(getApplicationContext(), Form.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}