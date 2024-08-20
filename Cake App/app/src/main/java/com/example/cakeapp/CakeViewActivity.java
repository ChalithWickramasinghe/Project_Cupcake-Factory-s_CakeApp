package com.example.cakeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class CakeViewActivity extends AppCompatActivity {
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake_view);
        RecyclerView rcvCake= findViewById(R.id.rcvCake);
        SetDB();
        Cake cake= new Cake();
        List<Cake> cakes= cake.GetCakes(db);
        CakeAdapter adapter= new CakeAdapter (db,cakes);
        rcvCake.setLayoutManager(new LinearLayoutManager(this));
        rcvCake.setAdapter(adapter);
    }
    private void SetDB()
    {
        try {
            db=openOrCreateDatabase("CakeDB",MODE_PRIVATE,null);
            db.execSQL("Create table " +
                    "if not exists " +
                    "Cake(id integer primary key autoincrement " +
                    "      ,name text,description text,number text,price real,cover blob)");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}