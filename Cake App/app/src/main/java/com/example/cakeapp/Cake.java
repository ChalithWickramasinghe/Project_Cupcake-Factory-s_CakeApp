package com.example.cakeapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Cake {
    private String Number;
    private int Id;
    private String Name;
    private String Description;
    private double Price;
    private byte[] Cover;

    public void setNumber(String number) {
        //if(number.length() >= 13)
        this.Number = number;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(double price) {
        if(Price >= 0)
            Price = price;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCover(byte[] cover) {
        Cover = cover;
    }

    public int getId() {
        return Id;
    }

    public String getNumber() {
        return Number;
    }

    public String getDescription() {
        return Description;
    }

    public String getName() {
        return Name;
    }

    public byte[] getCover() {
        return Cover;
    }

    public double getPrice() {
        return Price;
    }
    public void Save(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
            values.put("name",Name);
            values.put("description",Description);
            values.put("price",Price);
            values.put("number",Number);
            values.put("cover",Cover);
            db.insert("cake",null,values);
        }
        catch (Exception ex){

            throw ex;
        }
    }
    public void Update(SQLiteDatabase db)
    {
        try {
            ContentValues values= new ContentValues();
            values.put("name",Name);
            values.put("description",Description);
            values.put("price",Price);
            values.put("number",Number);
            values.put("cover",Cover);
            db.update("cake",values,"id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public void Delete(SQLiteDatabase db)
    {
        try {

            db.delete("cake","id= "+Id,null);
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
    public List<Cake> GetCakes(SQLiteDatabase db)
    {
        try
        {
            List<Cake> cakes= new ArrayList<Cake>();
            String query="select id,name,number,description, price, cover from cake";
            Cursor cursor= db.rawQuery(query,null);
            if(cursor.moveToFirst())
            {
                do {
                    Cake cake= new Cake();
                    cake.setId(cursor.getInt(0));
                    cake.setName (cursor.getString(1));
                    cake.setNumber (cursor.getString(2));
                    cake.setDescription(cursor.getString(3));
                    cake.setPrice(cursor.getDouble(4));
                    cake.setCover(cursor.getBlob(5));
                    cakes.add(cake);


                }while (cursor.moveToNext());
            }
            return cakes;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }



}