package com.example.win10.fbaseuno;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.win10.fbaseuno.objetos.Adapter;
import com.example.win10.fbaseuno.objetos.Alumno;
import com.google.firebase.auth.FirebaseAuth;;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    RecyclerView rv;
    List<Alumno> alumnos;
    Adapter adapter;
    DatabaseReference myRef;
    FirebaseDatabase database;
    EditText ednctrl,edname;
    Button btnins,btnout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_activity);

        rv=(RecyclerView)findViewById(R.id.recycler);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        alumnos = new ArrayList<>();
        adapter = new Adapter(alumnos);
        rv.setAdapter(adapter);

        btnins=findViewById(R.id.btnIns);
        btnout=findViewById(R.id.btnOut);
        ednctrl=findViewById(R.id.txtnctrl);
        edname=findViewById(R.id.txtname);

        btnout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RecyclerActivity.this,MainActivity.class));
            }
        });

        btnins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAddAlumno();
            }
        });


        myRef = FirebaseDatabase.getInstance().getReference("alumnos");
        database=FirebaseDatabase.getInstance();
        getData();


    }

    public void getData(){
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Alumno data= new Alumno();
                data = dataSnapshot.getValue(Alumno.class);
                alumnos.add(data);
                rv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void onClickAddAlumno(){
        String name=edname.getText().toString();
        String nctrl=ednctrl.getText().toString();

        if(!TextUtils.isEmpty(name)){
            String id = myRef.push().getKey();
            Alumno an=new Alumno(name,nctrl);
            myRef.child(id).setValue(an);
            Toast.makeText(RecyclerActivity.this, "Se insertaron los datos",
                    Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(RecyclerActivity.this, "campo nombre vacio",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
