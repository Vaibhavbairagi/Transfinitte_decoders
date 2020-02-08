package com.example.transfinitte_decoders.adminDoc;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivityDoc extends AppCompatActivity {
    EditText email,password;
    Button login;
    CheckBox showPassword;
    String emailTxt,passwordTxt;
    public static FirebaseAuth mAuth;
    TextView createAccount,forgetPassword;
    Boolean loginMode = true;
    ProgressBar progressBar;
    FirebaseFirestore db;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doc);
        email = findViewById(R.id.login_emailid);
        showPassword = findViewById(R.id.show_hide_password);
        forgetPassword = findViewById(R.id.forgot_password);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progress_circular);
        createAccount = findViewById(R.id.createAccount);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMode=true;
                progressBar.setVisibility(View.VISIBLE);
                Login();
            }
        });
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivityDoc.this, Doctor_Activity.class));
            finish();
        }
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                loginMode = false;
                Login();
            }
        });
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!showPassword.isChecked()){
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }else{
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reset();
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void Login() {
        emailTxt = email.getText().toString();
        passwordTxt = password.getText().toString();

        if(!isEmailValid(emailTxt)){
            email.setError("Invalid Email Entered");
            progressBar.setVisibility(View.INVISIBLE);
            email.requestFocus();
            return;
        }
        if(passwordTxt.isEmpty() || passwordTxt.length()<6){
            password.setError("Invalid Password, Length must be at least 6");
            progressBar.setVisibility(View.INVISIBLE);
            password.requestFocus();
            return;
        }

        if(loginMode){
            //user wants to login
            mAuth.signInWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(LoginActivityDoc.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Login In Failed, Try Again ",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login Successful ",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent intent = new Intent(LoginActivityDoc.this, Doctor_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }
        else{
            //user wants to register
            mAuth.createUserWithEmailAndPassword(emailTxt,passwordTxt).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivityDoc.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    }else{
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivityDoc.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void Reset() {
        emailTxt = email.getText().toString();
        if(!isEmailValid(emailTxt)){
            email.setError("Invalid Email Entered");
            progressBar.setVisibility(View.INVISIBLE);
            email.requestFocus();
            return;
        }

        mAuth.sendPasswordResetEmail(emailTxt).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility(View.INVISIBLE);
                if(task.isSuccessful()){
                    Toast.makeText(LoginActivityDoc.this,"Password sent to Email",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivityDoc.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}

