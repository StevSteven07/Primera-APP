package com.example.nivelacionsprint2.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.example.nivelacionsprint2.Entidades.Producto;

public class DBHelper extends SQLiteOpenHelper {
    
    private SQLiteDatabase sqLiteDatabase;
    
    public DBHelper(Context context){
        super(context, "Niv1.db", null, 1);
        sqLiteDatabase= this.getWritableDatabase();
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PRODUCTS("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name VARCHAR,"+
                "description VARCHAR,"+
                "price VARCHAR,"+
                "image BLOB"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS PRODUCTS");
    }

    //****METODOS****
    public void insertProduct(Producto producto){
        String sql= "INSERT INTO PRODUCTS VALUES(null, ?, ?, ?, ?)";
        SQLiteStatement statement= sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, producto.getName());
        statement.bindString(2, producto.getDescription());
        statement.bindString(3, String.valueOf(producto.getPrice()));
        statement.bindBlob(4, producto.getImage());

        statement.executeInsert();
    }

    public Cursor getProducts(){
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTS", null);
        return  cursor;
    }

    public Cursor getProductById(String id){
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM PRODUCTS WHERE id= "+id, null);
        return  cursor;
    }

    public void deleteProductById(String id){
        sqLiteDatabase.execSQL("DELETE FROM PRODUCTS WHERE id = "+id);
    }



}
