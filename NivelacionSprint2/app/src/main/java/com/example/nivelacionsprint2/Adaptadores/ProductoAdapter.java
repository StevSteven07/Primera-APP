package com.example.nivelacionsprint2.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nivelacionsprint2.Entidades.Producto;
import com.example.nivelacionsprint2.Info;
import com.example.nivelacionsprint2.R;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> arrayProducts;

    public ProductoAdapter(Context context, ArrayList<Producto> arrayProducts) {
        this.context = context;
        this.arrayProducts = arrayProducts;
    }

    @Override
    public int getCount() {
        return arrayProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater= LayoutInflater.from(this.context);
        view= layoutInflater.inflate(R.layout.product_template, null);

        ImageView imgProduct=(ImageView) view.findViewById(R.id.imageProduct);
        TextView textNameProduct= (TextView) view.findViewById(R.id.textNAmeProduct);
        TextView textDescriptionProduct= (TextView) view.findViewById(R.id.textDescriptionProduct);
        TextView textPriceProduct= (TextView) view.findViewById(R.id.textPriceProduct);
        
        Producto producto= arrayProducts.get(i);


        Bitmap bitmap= BitmapFactory.decodeByteArray(producto.getImage(), 0, producto.getImage().length);
        imgProduct.setImageBitmap(bitmap);
        textNameProduct.setText(producto.getName());
        textDescriptionProduct.setText(producto.getDescription());
        textPriceProduct.setText(String.valueOf(producto.getPrice()));

        imgProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context.getApplicationContext(), Info.class);
                intent.putExtra("name", producto.getName());
                intent.putExtra("description", producto.getDescription());
                intent.putExtra("price", producto.getPrice());
                intent.putExtra("image", producto.getImage());
                context.startActivity(intent);
            }
        });
        return view;
    }
}
