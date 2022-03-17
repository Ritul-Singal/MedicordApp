package com.example.medicordapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG="LoginActivity";
    EditText email;
    EditText pass;
    Button login;
    Button clear;
    TextView forgotPassword;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    TextView newUser;
    //private Object AlterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = (Button) findViewById(R.id.loginButton);
        clear = (Button) findViewById(R.id.passwordButton);
        email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        newUser = findViewById(R.id.newuser);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on below line we are getting data from our edit text.
                String userName = email.getText().toString();
                String password = pass.getText().toString();

                if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Please enter user name and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(userName)){
                    Toast.makeText(LoginActivity.this, "Please enter email ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password.length()<6){
                    Toast.makeText(LoginActivity.this, "Password length must be greater than 6", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!userName.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(userName).matches() && !password.isEmpty()){
                    //intent pass hoga, login hone k baad and user will go to next activity
                    Toast.makeText(getApplicationContext(), "User Logged in successfuly", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "User Logged in successfuly");

                }
                else{
                    if(password.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please enter the Password", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Please enter the Password");
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "Enter Valid UserName", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Enter Valid UserName");

                    }
                }

                if(!userName.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(userName).matches() && !password.isEmpty()){
                    //intent pass hoga, login hone k baad and user will go to next activity
                    Toast.makeText(getApplicationContext(), "User Logged in successfuly", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "User Logged in successfuly");

                }
                else{
                    if(password.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please enter the Password", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Please enter the Password");
                    }

                    else{
                        Toast.makeText(getApplicationContext(), "Enter Valid UserName", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "Enter Valid UserName");

                    }
                }



                progressBar.setVisibility(view.VISIBLE);

                firebaseAuth.signInWithEmailAndPassword(userName,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!userName.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(userName).matches() && !password.isEmpty()){
                            //intent pass hoga, login hone k baad and user will go to next activity
//                            Toast.makeText(getApplicationContext(), "User Logged in successfuly", Toast.LENGTH_SHORT).show();
//                            Log.d(TAG, "User Logged in successfuly");
                            return;
                        }
                        else{
                            if(password.isEmpty()){
                                Toast.makeText(getApplicationContext(), "Please enter the Password", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Please enter the Password");
                            }

                            else{
                                Toast.makeText(getApplicationContext(), "Enter Valid UserName", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "Enter Valid UserName");

                            }
                        }
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "User Logged in successfuly", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "User Logged in successfuly");
                            //Pass the intent of next activity after login successfully.

                        }
                    }
                });

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clear button clears the edit texts
                email.setText(" ");
                pass.setText(" ");
            }
        });

        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //passing the intent of signup activity
                startActivity(new Intent()); //pass the intent
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText resetMail = new EditText(view.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
                passwordResetDialog.setTitle("Reset Password?");
                passwordResetDialog.setTitle("Enter your email to reset the password");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //extract the email to reset the password
                        String mail = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Reset Link sent to your email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Reset Link not sent to your email" +e.getMessage() , Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });

    } //oncreate closing


}// Appcompact closing




