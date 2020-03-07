package com.example.transfinitte_decoders.adminDoc;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.transfinitte_decoders.MainActivity;
import com.example.transfinitte_decoders.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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
    RadioGroup radioGroup;
    RadioButton radioButton;
    private static final String TAG = "LoginActivity";
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;
    Boolean student;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_doc);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        }
        email = findViewById(R.id.login_emailid);
        showPassword = findViewById(R.id.show_hide_password);
        forgetPassword = findViewById(R.id.forgot_password);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.loginBtn);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        progressBar = findViewById(R.id.progress_circular);
        createAccount = findViewById(R.id.createAccount);
        radioGroup = findViewById(R.id.radio_group_stu);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                onRadioButtonClicked(radioButton);
                Log.e("TAG", "yes");
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginMode=true;
                progressBar.setVisibility(View.VISIBLE);
                Login();
            }
        });
        if (mAuth.getCurrentUser() != null) {
            if (mAuth.getCurrentUser().getEmail().substring(0, 3).equals("doc")) {
                startActivity(new Intent(LoginActivityDoc.this, Doctor_Activity.class));
                finish();
            }
            else{
                startActivity(new Intent(LoginActivityDoc.this, MainActivity.class));
                finish();
            }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {

            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
            } else { //Permission is not available
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void Login() {
        emailTxt = email.getText().toString();
        passwordTxt = password.getText().toString();

        if(emailTxt.equals("admin")&&passwordTxt.equals("admin")){
            Intent out = new Intent(this,ambulance.class);
            progressBar.setVisibility(View.GONE);
            startActivity(out);
            return;
        }

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
                        if(student == false){
                            Log.e("TAG", "doctor");
                            Intent intent = new Intent(LoginActivityDoc.this, Doctor_Activity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Intent intent = new Intent(LoginActivityDoc.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
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
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.student:
                if(checked){
                    Log.e("TAG", "available");
                    student = true;
                    break;
                }
            case R.id.doctor:
                if(checked){
                    Log.e("TAG", "doctor");
                    student = false;
                    break;
                }
        }
    }
}

