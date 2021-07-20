package com.example.loginfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {
    Button b1,b2;
    EditText e1,e2;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e2.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
        firebaseAuth=FirebaseAuth.getInstance();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Second.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1=e1.getText().toString();
                String s2=e2.getText().toString();
                if(s1.isEmpty())
                {
                    e1.setError("Email failed");
                }
                else
                {
                    if(s2.isEmpty())
                    {
                        e2.setError("Fill the password");
                        return;
                    }
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(s1,s2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent j=new Intent(MainActivity.this,Third.class);
                        startActivity(j);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this,"Login not Successful",Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                        Intent m=new Intent(MainActivity.this,Second.class);
                        startActivity(m);
                        finish();

                    }
                    }
                });
            }
        });
    }
}