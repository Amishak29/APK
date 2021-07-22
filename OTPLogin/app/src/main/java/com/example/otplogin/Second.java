package com.example.otplogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class Second extends AppCompatActivity {
    EditText e1;
    Button b1;
    FirebaseAuth firebaseAuth;
    String phone;
    String otpid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        phone=getIntent().getStringExtra("mobile").toString();
        e1=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button2);
        firebaseAuth=FirebaseAuth.getInstance();
        genotp();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(e1.getText().toString().isEmpty())
                {
                    Toast.makeText(Second.this,"Plz enter your otp",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(e1.getText().toString().length()!=6)
                    {
                        Toast.makeText(Second.this,"Invalid OTP",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otpid,e1.getText().toString());
                        signinwithPhoneAuthCredential(credential);
                    }
                }
            }
        });
    }private void genotp()
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,
                60,
                TimeUnit.SECONDS,
                this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull @NotNull String s, @NonNull @NotNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        otpid=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull @org.jetbrains.annotations.NotNull PhoneAuthCredential phoneAuthCredential) {
                        signinwithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull @org.jetbrains.annotations.NotNull FirebaseException e) {
                        Toast.makeText(Second.this,"Verification Failed",Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }private void signinwithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Second.this,"Database Updated",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Second.this,Third.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(Second.this,"OTP mismatched",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}