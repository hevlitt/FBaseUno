package com.example.win10.fbaseuno;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLog,btnAccess;
    EditText txtemail,txtpass;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAccess=(Button)findViewById(R.id.btnAccess);
        btnLog=(Button)findViewById(R.id.btnLog);
        txtemail=(EditText)findViewById(R.id.txtEmail);
        txtpass=(EditText)findViewById(R.id.txtPassword);

        btnLog.setOnClickListener(this);
        btnAccess.setOnClickListener(this);

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.i("SESION","Sesion Iniciada con email: " + user.getEmail());
                }else{
                    Log.i("SESION","Sesion Cerrada");
                }
            }
        };
    }

    private void registrar(String email,String pass){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.i("SESION","Usuario Creado Correctamente");
                }else {
                    Log.i("SESION",task.getException().getMessage()+"");
                }
            }
        });

    }

    private void acceder(String email,String pass){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnAccess:
                String emailInit=txtemail.getText().toString();
                String passInit=txtpass.getText().toString();
                acceder(emailInit,passInit);
                Intent intent = new Intent(getApplicationContext(), RecyclerActivity.class);//Aqui
                startActivity(intent);
                finish();
                break;
            case R.id.btnLog:
                String emailReg=txtemail.getText().toString();
                String passReg=txtpass.getText().toString();
                registrar(emailReg,passReg);
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null){
            FirebaseAuth.getInstance().removeAuthStateListener(mAuthListener);
        }
    }
}
