package com.example.ShoppingList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    //create object of databasereference
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://shoppinglist-d78f9-default-rtdb.firebaseio.com");
    EditText username;
    EditText password;
    Button loginbtn;
    TextView Registre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginbtn=(Button) findViewById(R.id.loginbtn);
        Registre=(TextView) findViewById(R.id.Registre);


        //admin and admin
    }

            public void display(View view) {


                if(username.getText().toString().isEmpty()|| password.getText().toString().isEmpty()){

                    Toast.makeText(login.this,"Please enter your username or password !!!",Toast.LENGTH_SHORT).show();

                }else{
                  databaseReference.child("username").addListenerForSingleValueEvent(new ValueEventListener() {
                      @Override
                      public void onDataChange(@NonNull DataSnapshot snapshot) {
                          //check if username is exist in firebase database
                          if(snapshot.hasChild(username.getText().toString())){
                              String getPassword=snapshot.child(username.getText().toString()).child("password").getValue(String.class);
                              if(getPassword.equals(password.getText().toString())){
                                  Toast.makeText(login.this,"Successfully logged in ",Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(login.this,Registration.class));
                                  finish();
                              }
                              else{
                                  Toast.makeText(login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                              }
                          }
                          else{
                              Toast.makeText(login.this,"vvvv Password",Toast.LENGTH_SHORT).show();
                          }

                      }

                      @Override
                      public void onCancelled(@NonNull DatabaseError error) {

                      }
                  });

                }

            }
            public void displayRegister(View view){
                 Intent intent=new Intent(this,Registration.class);
                 startActivity(intent);
            }




}