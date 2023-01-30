package com.example.ShoppingList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {
    //create object of databasereference
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://shoppinglist-d78f9-default-rtdb.firebaseio.com");
    EditText username;
    EditText email;
    EditText password;
    EditText Confirmation;
    Button signupbtn;
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        Confirmation = (EditText) findViewById(R.id.Confirmation);
        signupbtn=(Button) findViewById(R.id.signupbtn);
        login=(TextView) findViewById(R.id.login);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from EditText into String variables
                String UsernameTxt=username.getText().toString();
                String emailTxt=email.getText().toString();
                String passwordTxt=password.getText().toString();
                String ConfirmationTxt=Confirmation.getText().toString();

               //check if user fill all the fields before sending data
                if(UsernameTxt.isEmpty() || emailTxt.isEmpty()||passwordTxt.isEmpty()||ConfirmationTxt.isEmpty() ){
                    Toast.makeText(Registration.this,"Please enter your username or password !!!",Toast.LENGTH_SHORT).show();

                }

                else if(!passwordTxt.equals(ConfirmationTxt)){
                    Toast.makeText(Registration.this,"Password are not matching!!",Toast.LENGTH_SHORT).show();

                }
                else{
                    //sending data to database
                    //we are using email as a unique identify of every user
                    databaseReference.child("username").child(UsernameTxt).child("password").setValue(passwordTxt);
                    databaseReference.child("username").child(UsernameTxt).child("email").setValue(emailTxt);
                    databaseReference.child("username").child(UsernameTxt).child("Confirmation").setValue(ConfirmationTxt);

                    Toast.makeText(Registration.this,"User Registered successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}