package com.example.auted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    TextView forgetPassword;
    EditText inputPassword, inputEmail;
    Button btnLogin, startedBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        forgetPassword = findViewById(R.id.forget);
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.PASSWORD);
        btnLogin = findViewById(R.id.loginbtn);
        startedBtn = findViewById(R.id.startedBtn);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        muser = mAuth.getCurrentUser();

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, forgetPassword.class));
            }
        });
        startedBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, demoscreenone.class));
            }
            });
        btnLogin.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforLogin();
            }
        }));
        }


    private void perforLogin() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();


        if (!email.matches(emailPattern)) {
            inputEmail.setError("enter context Email");
        } else if (password.isEmpty() || password.length() < 6) {
            inputPassword.setError("enter proper password");
        } else {
            progressDialog.setMessage("please wait while login");
            progressDialog.setTitle("login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivty();
                        Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }}});
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            user.getIdToken(true)
                    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                        @Override
                        public void onComplete(@NonNull Task<GetTokenResult> task) {
                            if (task.isSuccessful()) {
                                String idToken = task.getResult().getToken();
                                // Use the ID token to authenticate with the Firebase Realtime Database
                                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                                mAuth.signInWithCustomToken(idToken)
                                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()) {
                                                    // Authentication successful
                                                    // Now you can access the Firebase Realtime Database
                                                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                                                    // Perform database operations
                                                } else {
                                                    // Authentication failed
                                                    Exception exception = task.getException();
                                                    // Handle the exception
                                                    }}});
                            } else {
                                // Error occurred while getting ID token
                                Exception exception = task.getException();
                                // Handle the exception
                            }}});}}

    private void sendUserToNextActivty() {
            Intent intent= new Intent(Login.this,welcomScreen.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }}
