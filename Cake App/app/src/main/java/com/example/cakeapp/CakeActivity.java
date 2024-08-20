package com.example.cakeapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CakeActivity extends AppCompatActivity {
    EditText edtCakeID, edtCakeName,edtCakePrice,edtDescription;
    Button btnAddCake, btnUpdateCake, btnViewCake;
    ImageButton ibtnCakeCover;
    ImageView imvCakeCover;
    SQLiteDatabase db;
    Bitmap pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cake);
        edtCakeID=findViewById(R.id.edtCakeID);
        edtCakeName=findViewById(R.id.edtCakeName);
        edtCakePrice=findViewById(R.id.edtCakePrice);
        edtDescription=findViewById(R.id.edtDescription);
        btnAddCake=findViewById(R.id.btnAddCake);
        btnUpdateCake=findViewById(R.id.btnUpdateCake);
        btnViewCake=findViewById(R.id.btnViewCake);
        ibtnCakeCover=findViewById(R.id.ibtnCakeCover);
        imvCakeCover=findViewById(R.id.imvCakeCover);
        SetDB();


        ActivityResultLauncher launcher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {

                        Intent intent=o.getData();
                        pic = (Bitmap) intent.getExtras().get("data");
                        imvCakeCover.setImageBitmap(pic);

                    }
                });
        ActivityResultLauncher launcher2=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {

                        Intent intent=o.getData();
                        Uri uri=intent.getData();
                        imvCakeCover.setImageURI(uri);
                        try {
                            pic=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                });

        ibtnCakeCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ibtnCakeCover.getContext());
                builder.setMessage("Please select the option you wish to use !");
                builder.setTitle("Select a cake image");
                builder.setPositiveButton("Use the camera",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                launcher.launch(intent);
                            }
                        });
                builder.setNegativeButton("Use the gallery",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/");
                                launcher2.launch(intent);
                            }
                        });
                AlertDialog dialog= builder.create();
                dialog.show();
            }
        });

        btnAddCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Cake cake= new Cake ();
                    cake.setNumber (edtCakeID.getText().toString());
                    cake.setName (edtCakeName.getText().toString());
                    cake.setDescription(edtDescription.getText().toString());
                    cake.setPrice(Double.valueOf(edtCakePrice.getText().toString()));
                    ByteArrayOutputStream stream= new ByteArrayOutputStream();
                    pic.compress(Bitmap.CompressFormat.JPEG,80,stream);
                    pic.compress(Bitmap.CompressFormat.PNG,80,stream);
                    byte[] cover=stream.toByteArray();
                    cake.setCover(cover);

                    cake.Save(db);
                    Toast.makeText(getApplicationContext(),
                            "Cake Deatails added",Toast.LENGTH_LONG).show();

                }
                catch (Exception ex)
                {
                    Toast.makeText(getApplicationContext(),
                            ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        btnViewCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), CakeViewActivity.class);
                startActivity(intent);
            }
        });
    }
    private void SetDB()
    {
        try {
            db=openOrCreateDatabase("CakeDB",MODE_PRIVATE,null);
            db.execSQL("CREATE TABLE IF NOT EXISTS cake (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, description TEXT, price REAL, number TEXT, cover BLOB)");
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}