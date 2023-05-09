package com.dam2.m08.proyectocsmaresme.noticias;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dam2.m08.proyectocsmaresme.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class AdaptadorNoticia extends RecyclerView.Adapter<AdaptadorNoticia.MyViewHolder> {

    private Context context;
    private List<Noticia> noticiaList;
    private LayoutInflater layoutInflater;
    private String rol;
    private static Locale defaultLocale;
    public AdaptadorNoticia(Context context, List<Noticia> noticiaList, String rol) {
        this.context = context;
        this.noticiaList = noticiaList;
        this.rol = rol;
        obtenerIdioma();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;

        layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.noticia_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Noticia noticia = noticiaList.get(position);

        holder.titulo.setText(noticiaList.get(position).getTitulo());
        holder.cuerpo.setText(noticiaList.get(position).getCuerpo());
        Glide.with(holder.foto).load(noticia.getImagen()).into(holder.foto);
        if (Objects.equals(rol, "Administrador")){
            holder.menu.setVisibility(View.VISIBLE);
        } else {
            holder.menu.setVisibility(View.INVISIBLE);
        }
        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(layoutInflater.getContext(), holder.menu);
                popupMenu.getMenuInflater().inflate(R.menu.menu_comentario, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.btn_editar_comentario:
                                Toast.makeText(layoutInflater.getContext(), "ha pulsado el boton de editar comentario", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.btn_eliminar_comentario:
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Noticias")
                                        .document(noticia.getId())
                                        .delete()
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(view.getContext(), "Correctamente eliminado", Toast.LENGTH_SHORT).show();
                                                noticiaList.remove(position);
                                                notifyItemRemoved(position);
                                            }
                                        });
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


        holder.menu.setImageDrawable((layoutInflater.getContext().getDrawable(R.drawable.menu_comentario_chatanonimo_2)));
    }
    public void obtenerIdioma(){

        if (defaultLocale == null) defaultLocale = Locale.getDefault(); //backup default locale
        String languageToLoad = Locale.getDefault().getDisplayLanguage();
        if (languageToLoad.equals("default")) languageToLoad = defaultLocale.getLanguage();
        Log.d("UsefulFunctions", "setLocale():" + languageToLoad);

        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        Toast.makeText(context.getApplicationContext(), languageToLoad, Toast.LENGTH_SHORT).show();

    }

    @Override
    public int getItemCount() {
        return noticiaList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView menu;
        TextView titulo;
        TextView cuerpo;
        ImageView foto;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.foto);
            titulo = itemView.findViewById(R.id.titulo);
            cuerpo = itemView.findViewById(R.id.cuerpo);
            menu = itemView.findViewById(R.id.menuOpcionesNoticia);
        }
    }
}

