package com.example.win10.fbaseuno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.win10.fbaseuno.objetos.Adapter;
import com.example.win10.fbaseuno.objetos.Alumno;
import com.example.win10.fbaseuno.objetos.FirebaseRef;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Alumno> alumnos;
    Adapter adapter;
    DatabaseReference myRef;
    EditText ednctrl,edname;
    Button btnins;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);
        rv=(RecyclerView)findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        alumnos = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter = new Adapter(alumnos);
        rv.setAdapter(adapter);

        btnins=findViewById(R.id.btnIns);
        ednctrl=findViewById(R.id.txtnctrl);
        edname=findViewById(R.id.txtname);


        myRef = database.getReference(FirebaseRef.TUTORIAL_REFERENCE);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alumnos.removeAll(alumnos);
                for(DataSnapshot snapshot:
                        dataSnapshot.getChildren()){
                    Alumno alumno = snapshot.getValue(Alumno.class);
                    alumnos.add(alumno);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void onClickAddAlumno(View view){
        Alumno alumno =new Alumno((ednctrl.getText().toString()),
                edname.getText().toString());
        myRef.child(FirebaseRef.ALUMNO_REFERENCE).push().setValue(alumno);
    }
}
