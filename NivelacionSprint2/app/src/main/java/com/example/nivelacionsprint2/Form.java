package com.example.nivelacionsprint2;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.nivelacionsprint2.DB.DBHelper;
import com.example.nivelacionsprint2.Entidades.Producto;
import com.example.nivelacionsprint2.Servicios.ProductoService;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Form extends AppCompatActivity {
    private Button btnForm;
    private EditText editNameForm, editDrescriptionForm, editPriceForm;
    private ImageView imgForm;
    private ProductoService productoService;
    private DBHelper dbHelper;

    private ActivityResultLauncher<String> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        btnForm= (Button) findViewById(R.id.btnForm);
        editNameForm= (EditText)  findViewById(R.id.editNameForm);
        editDrescriptionForm= (EditText) findViewById(R.id.editDescriptionForm);
        editPriceForm= (EditText) findViewById(R.id.editPriceForm);
        imgForm= (ImageView) findViewById(R.id.imgForm);
        productoService= new ProductoService();
        dbHelper= new DBHelper(this);

    content= registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
        @Override
        public void onActivityResult(Uri result) {
            try {
                InputStream inputStream= getContentResolver().openInputStream(result);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgForm.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                Log.e("FileError", e.toString());
            }
        }
    });

    imgForm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            content.launch("image/*");
        }
    });

    btnForm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Producto producto= new Producto(
                    editNameForm.getText().toString(),
                    editDrescriptionForm.getText().toString(),
                    Integer.parseInt(editPriceForm.getText().toString().trim()),
                    productoService.imageViewToByte(imgForm)
            );
            dbHelper.insertProduct(producto);
            Intent intent= new Intent(getApplicationContext(), Catalogo.class);
            startActivity(intent);


        }
    });


    }
}