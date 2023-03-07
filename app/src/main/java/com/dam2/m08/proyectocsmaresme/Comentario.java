package com.dam2.m08.proyectocsmaresme;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Comentario implements Parcelable {
    private int id;
    private String nombre;
    private String titulo;
    private String contenido;
    private String fecha;

    public Comentario(int id, String nombre, String titulo, String contenido, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fecha = fecha;
    }
    public Comentario(){}


    protected Comentario(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        titulo = in.readString();
        contenido = in.readString();
        fecha = in.readString();
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


    public int getId() {
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

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(titulo);
        dest.writeString(contenido);
        dest.writeString(fecha);
    }
}
