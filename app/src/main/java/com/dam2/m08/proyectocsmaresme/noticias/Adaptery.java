package com.dam2.m08.proyectocsmaresme.noticias;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dam2.m08.proyectocsmaresme.R;

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
        Noticia noticia = noticiaList.get(position);

        holder.titulo.setText(noticiaList.get(position).getTitulo());
        holder.cuerpo.setText(noticiaList.get(position).getCuerpo());
        Glide.with(holder.foto).load(noticia.getImagen()).into(holder.foto);
    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titulo;
        TextView cuerpo;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            titulo = itemView.findViewById(R.id.titulo);
            cuerpo = itemView.findViewById(R.id.cuerpo);
        }
    }
}

