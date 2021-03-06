package com.example.vibe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;
    private FirebaseAuth mAuth;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
    }

    public void Go(View view)
    {
        Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }
    public void Login(View view)
    {
        String email=mEmail.getText().toString();
        String password=mPassword.getText().toString();
        if(email.equals("")||password.equals(""))
            return;
        else {
            Toast.makeText(LoginActivity.this, "Login in progress", Toast.LENGTH_SHORT).show();
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(LoginActivity.this,"Login in progress",Toast.LENGTH_SHORT).show();
                    if(!task.isSuccessful())
                    {
                        Log.i("Error is ",task.getException().toString());
                        showErrorDialog("There was an error in Signing in");
                    }
                    else
                    {
                        Intent i=new Intent(LoginActivity.this,MainActivity.class);
                        finish();
                        startActivity(i);
                    }
                }
            });
        }

    }
    public void Register(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    public void showErrorDialog(String s)
    {
        new AlertDialog.Builder(this)
                .setTitle("Oops")
                .setMessage(s)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.ok,null)
                .show();
    }
}
