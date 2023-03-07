package com.dam2.m08.proyectocsmaresme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context context;

    private List<Noticia> noticiaList;

    public Adaptery(Context context, List<Noticia> noticiaList) {
        this.context = context;
        this.noticiaList = noticiaList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.noticia_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Noticia movie = noticiaList.get(position);

        holder.titulo.setText(noticiaList.get(position).getTitulo());
        holder.cuerpo.setText(noticiaList.get(position).getCuerpo());
        holder.imagen.setText(noticiaList.get(position).getImagen());

    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView cuerpo;
        TextView imagen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            cuerpo = itemView.findViewById(R.id.cuerpo);
            imagen = itemView.findViewById(R.id.imagen);

        }
    }
}

