package com.example.cakeapp;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CakeAdapter extends RecyclerView.Adapter<CakeAdapter.ViewHolder> {

    SQLiteDatabase db;
    List<Cake> cakeList;
    public CakeAdapter(SQLiteDatabase _db, List<Cake> cakes)
    {
        db=_db;
        cakeList = cakes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View items= inflater.inflate(R.layout.activity_display_cake,parent,false);
        ViewHolder holder= new ViewHolder(items);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cake cake= cakeList.get(position);
        holder.txvCakeID.setText(String.valueOf(cake.getId()));
        holder.txvCakeName.setText(cake.getName ());
        holder.txvCakeDescription.setText(cake.getDescription());
        holder.txvCakeNumber.setText(cake.getNumber ());
        holder.txvPrice.setText(String.valueOf(cake.getPrice()));

        Bitmap bitmap= BitmapFactory.decodeByteArray(cake.getCover(),
                0,cake.getCover().length);
        holder.imvCakePhoto.setImageBitmap(bitmap);


    }

    @Override
    public int getItemCount() {
        return cakeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvCakeID,txvCakeDescription,txvPrice,txvCakeName,txvCakeNumber;
        ImageView imvCakePhoto;
        Button btnCart;
        ImageButton btnEdit,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvCakeID= itemView.findViewById(R.id.txvCakeID);
            txvCakeNumber= itemView.findViewById(R.id.txvCakeNumber);
            txvCakeDescription= itemView.findViewById(R.id.txvCakeDescription);
            txvCakeName= itemView.findViewById(R.id.txvCakeName);
            txvPrice= itemView.findViewById(R.id.txvPrice);
            imvCakePhoto= itemView.findViewById(R.id.imvCakePhoto);
            btnCart = itemView.findViewById(R.id.btnCart);
        }
    }
}