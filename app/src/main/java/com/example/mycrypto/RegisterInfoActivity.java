package com.example.mycrypto;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Register information then save into firebase
public class RegisterInfoActivity extends AppCompatActivity {


    DatabaseReference databaseRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_info);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button backbtn = findViewById(R.id.back_button_3);

        Button btnRegister = findViewById(R.id.btnSave);
        EditText txtName = findViewById(R.id.First_Name);
        EditText txtLastName = findViewById(R.id.LastName_regist);
        EditText txtCardNumRegist= findViewById(R.id.etCardNumber_regist);
        EditText txtCardName = findViewById(R.id.etName_regist);
        EditText txtCardExp=findViewById(R.id.etExpiry_regist);
        EditText txtUsername=findViewById(R.id.User_name);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RegisterInfoActivity", "Save button clicked");
                String firstname = txtName.getText().toString();
                String lastname = txtLastName.getText().toString();
                String cardnum = txtCardNumRegist.getText().toString();
                String cardname = txtCardName.getText().toString();
                String cardexp = txtCardExp.getText().toString();
                String username= txtUsername.getText().toString();
                
                if (firstname.isEmpty()) {
                    txtName.setError("Please enter your first name");
                    txtName.requestFocus();
                } else if (lastname.isEmpty()) {
                    txtLastName.setError("Please enter your last name");
                    txtLastName.requestFocus();
                } else if (username.isEmpty()) {
                    txtUsername.setError("Please Enter your user name");
                    txtUsername.requestFocus();
                } else if (cardnum.isEmpty()) {
                    txtCardNumRegist.setError("Please enter your card number");
                    txtCardNumRegist.requestFocus();
                } else if (cardname.isEmpty()) {
                    txtCardName.setError("Please enter your card name");
                    txtCardName.requestFocus();
                } else if (cardexp.isEmpty()) {
                    txtCardExp.setError("Please enter your card expiry date");
                    txtCardExp.requestFocus();
                } else {


                    // Create a UserData object with the user's data
                    ReadWriteUserDetails userData = new ReadWriteUserDetails(firstname, lastname,username, cardnum, cardname, cardexp);
                    // Push the user data to Firebase Realtime Database
                    DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Register Users");
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();

                    if (firebaseUser != null) {
                        referenceProfile.child(firebaseUser.getUid()).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterInfoActivity.this, "Register successfully", Toast.LENGTH_SHORT).show();
                                    // Navigate to main activity
                                    Intent intent = new Intent(RegisterInfoActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(RegisterInfoActivity.this, "Failed to register. Please try again.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });


        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back to main activity
                Intent intent = new Intent(RegisterInfoActivity.this, MainActivity.class);
                startActivity(intent);
                
            }
        });
    }





    
    
}



