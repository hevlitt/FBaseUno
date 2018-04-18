package com.example.win10.fbaseuno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.win10.fbaseuno.objetos.Alumno;
import com.example.win10.fbaseuno.objetos.FirebaseRef;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Database extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(FirebaseRef.ALUMNO_REFERENCE);
        myRef.child(FirebaseRef.ALUMNO_REFERENCE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Alumno alumno = dataSnapshot.getValue(Alumno.class);
                Log.i("ALUMNO",alumno.getNombre());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
