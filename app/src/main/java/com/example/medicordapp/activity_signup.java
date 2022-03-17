package com.example.medicordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class activity_signup extends AppCompatActivity {

    EditText mFullname, mPhone, mEmail, mPassword, mAddress;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFullname = findViewById(R.id.fullname);
        mPhone = findViewById(R.id.mobileno);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mAddress = findViewById(R.id.address);
        mRegisterBtn = findViewById(R.id.register);
        mLoginBtn = findViewById(R.id.createlogin);

        fAuth = FirebaseAuth.getInstance();

        if(fAuth.getCurrentUser() != null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email id is required..");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required..");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password must be >= 6 characters..");
                    return;
                }

                //register the user in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Toast.makeText(activity_signup.this, "User Created.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else{
                            Toast.makeText(activity_signup.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            }
        });


    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.i("pause", "Signup activity is paused");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("stop", "Signup activity is stopped");

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("start","Signup activity is started");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        //savedInstanceState.putString("name",this.name);
        Log.i("saveinstance","Signup activity is onSaveInstaceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "Signup activity is resumed");

    }
}