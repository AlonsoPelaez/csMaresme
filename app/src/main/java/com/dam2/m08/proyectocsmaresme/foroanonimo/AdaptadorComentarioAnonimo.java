package com.dam2.m08.proyectocsmaresme.foroanonimo;

import android.annotation.SuppressLint;
import android.content.Context;
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

import com.dam2.m08.proyectocsmaresme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class AdaptadorComentarioAnonimo extends RecyclerView.Adapter<AdaptadorComentarioAnonimo.AdaptadorComentarioAnonimoHolder> implements View.OnClickListener {

    private LayoutInflater layoutInflater;
    private List<Comentario> lista;
    private View.OnClickListener listener;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public AdaptadorComentarioAnonimo(Context mContext, List<Comentario> listaCo){
        this.lista = listaCo;
        this.layoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    @NonNull
    @Override
    public AdaptadorComentarioAnonimoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.comentario_card, parent, false);
        view.setOnClickListener(this);

        return new AdaptadorComentarioAnonimoHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorComentarioAnonimoHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imprimir(position);
        holder.imageUser.setImageDrawable(layoutInflater.getContext().getDrawable(R.drawable.logo_app_consorcimaresme));
//        holder.btn_menu_comentario.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {

//                PopupMenu popupMenu = new PopupMenu(layoutInflater.getContext(), holder.btn_menu_comentario);
//                popupMenu.getMenuInflater().inflate(R.menu.menu_comentario, popupMenu.getMenu());
//                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()){
//                            case R.id.btn_editar_comentario:
//                                editaComentario(v,position);
//                                return true;
//                            case R.id.btn_eliminar_comentario:
//                                eliminaComentario(position);
//                                return true;
//                            default:
//                                return false;
//                        }
//                    }
//                });
//                popupMenu.show();
//            }
//        });

//        holder.btn_menu_comentario.setImageDrawable(layoutInflater.getContext().getDrawable(R.drawable.menu_comentario_chatanonimo_2));
    }

    private void editaComentario(View v, int position) {
        Comentario comentario = lista.get(position);
        Intent intent= new Intent(v.getContext().getApplicationContext(), FormularioComentario.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("modo_edicion",true);
        intent.putExtra("comentario",comentario);
//        notifyItemChanged(position);
        v.getContext().startActivity(intent);
    }


    public void eliminaComentario(int position) {
        Comentario comentario = lista.get(position);
        db.collection("Comentarios").document(comentario.getId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(layoutInflater.getContext(), "se ha eliminado el comentario",Toast.LENGTH_SHORT).show();
                    lista.remove(position);
                    notifyItemRemoved(position);
                }else {
                    Toast.makeText(layoutInflater.getContext(), "ha ocurrido un error: " + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    class AdaptadorComentarioAnonimoHolder extends RecyclerView.ViewHolder{
        TextView tvTitulo, tvNombre, tvContenido, tvFecha, tvNombreCategoria;
        ImageView imageUser, btn_menu_comentario;

        public AdaptadorComentarioAnonimoHolder(@NonNull View itemView) {
            super(itemView);

            tvTitulo = itemView.findViewById(R.id.edtxt_Titulo);
            tvNombre = itemView.findViewById(R.id.cvNombre);
            tvContenido = itemView.findViewById(R.id.edtxt_Contenido);
            tvFecha = itemView.findViewById(R.id.cvFecha);
            imageUser = itemView.findViewById(R.id.imageUser);
            btn_menu_comentario = itemView.findViewById(R.id.btn_menu_comentario);
//            tvNombreCategoria = itemView.findViewById(R.id.nombre_categoria);
        }
        public void imprimir (int i){

            tvTitulo.setText(""+ lista.get(i).getTitulo());
            tvNombre.setText(""+lista.get(i).getNombre());
            tvContenido.setText(""+lista.get(i).getContenido());
            tvFecha.setText(""+lista.get(i).getFecha());
//            tvNombreCategoria.setText(""+lista.get(i).getCategoria());
        }
    }
}
