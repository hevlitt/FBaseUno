package com.example.win10.fbaseuno.objetos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.win10.fbaseuno.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AlumnosviewHolder>{

    List<Alumno> alumnos;

    public Adapter(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
    @NonNull
    @Override
    public AlumnosviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recycler,parent,false);
        AlumnosviewHolder holder =  new AlumnosviewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosviewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.txtn.setText(alumno.getNombre());
        holder.txtNctrl.setText(alumno.getnControl());
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    public static class AlumnosviewHolder extends RecyclerView.ViewHolder{

        TextView txtn,txtNctrl;

        public AlumnosviewHolder(View itemView) {
            super(itemView);
            txtn =(TextView) itemView.findViewById(R.id.txtNombre);
            txtNctrl = (TextView) itemView.findViewById(R.id.txtNControl);
        }
    }
}
