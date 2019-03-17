package com.example.admin.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db=new DatabaseHelper(this);
        Button btn=(Button)findViewById(R.id.reg);

        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.email);
        e3=(EditText)findViewById(R.id.password);
        e4=(EditText)findViewById(R.id.date);
        e5=(EditText)findViewById(R.id.phone);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty() || e3.getText().toString().isEmpty() || e4.getText().toString().isEmpty() || e5.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fields Cant Be Empty", Toast.LENGTH_SHORT).show();
                }
                else {

                    AlertDialog.Builder builder=new AlertDialog.Builder(Register.this);

                    builder.setTitle(Html.fromHtml("<font color='#000000'>Are You Confirm..?</font>"));


                    builder.setPositiveButton(Html.fromHtml("<font color='#17b7c8'>Yes</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Register.this,"You Pressed Yes!",Toast.LENGTH_SHORT).show();
                            String s1 = e1.getText().toString().trim();
                            String s2 = e2.getText().toString().trim();
                            String s3 = e3.getText().toString().trim();
                            String s4 = e4.getText().toString().trim();
                            String s5 = e5.getText().toString().trim();


                            Boolean insert = db.insert(s1, s2, s3, s4, s5);

                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Registered Fail or Fields are empty", Toast.LENGTH_SHORT).show();

                            }
                            startActivity(new Intent(Register.this,MainActivity.class));
                        }
                    });
                    builder.setNegativeButton(Html.fromHtml("<font color='#17b7c8'>No</font>"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Register.this,"You Pressed No!",Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog dialog=builder.create();
                    dialog.show();
                }
            }
        });
    }
}
