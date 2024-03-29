package com.dam2.m08.proyectocsmaresme.foroanonimo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Comentario implements Parcelable {
    private String id;
    private String nombre;
    private String titulo;
    private String contenido;
    private String fecha;
    private int categoria;
    private String email;

    public Comentario(String id, String nombre, String titulo, String contenido, String fecha, int categoria,String email) {
        this.id = id;
        this.nombre = nombre;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
        this.categoria = categoria;
        this.email = email;
    }


    protected Comentario(Parcel in) {
        id = in.readString();
        nombre = in.readString();
        titulo = in.readString();
        contenido = in.readString();
        fecha = in.readString();
        categoria = in.readInt();
        email = in.readString();
    }
    public static final Creator<Comentario> CREATOR = new Creator<Comentario>() {
        @Override
        public Comentario createFromParcel(Parcel in) {
            return new Comentario(in);
        }

        @Override
        public Comentario[] newArray(int size) {
            return new Comentario[size];
        }
    };


    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Comentario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fecha='" + fecha + '\'' +
                ", categoria=" + categoria +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(nombre);
        dest.writeString(titulo);
        dest.writeString(contenido);
        dest.writeString(fecha);
        dest.writeInt(categoria);
        dest.writeString(email);
    }
}
