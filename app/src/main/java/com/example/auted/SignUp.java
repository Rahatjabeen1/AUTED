package com.example.auted;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class SignUp extends AppCompatActivity {
    TextView alreadyHaveaccount;
    EditText inputEmail,inputPassword,inputConfirmPassword,inputUserName;
    Button btnsignUp;
    String emailPattern ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser muser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        alreadyHaveaccount=findViewById(R.id.txt);
        inputUserName=findViewById(R.id.USERNAME);
        inputPassword=findViewById(R.id.PASSWORD);
        inputConfirmPassword=findViewById(R.id.confirmpassword);
        inputEmail=findViewById(R.id.email);
        btnsignUp=findViewById(R.id.signup);

        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        muser=mAuth.getCurrentUser();

        alreadyHaveaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });


        btnsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perforAuth();
            }
        });
    }

    private void perforAuth() {
        String email=  inputEmail.getText().toString();
        String password=  inputPassword.getText().toString();
        String confirmPassword=  inputConfirmPassword.getText().toString();
        String username=  inputUserName.getText().toString();

        if(!email.matches(emailPattern)){
            inputEmail.setError("enter context Email");
        }else if(password.isEmpty() || password.length()<6){
            inputPassword.setError("enter proper password");
        }else if(!password.equals(confirmPassword)){
            inputConfirmPassword.setError("password not match both fields");
        }else{
            progressDialog.setMessage("please wait while registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivty();
                        Toast.makeText(SignUp.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(SignUp.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void sendUserToNextActivty() {
        Intent intent= new Intent(SignUp.this,Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}