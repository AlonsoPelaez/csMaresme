package com.dam2.m08.proyectocsmaresme.noticias;

public class Noticia {
    private String titulo;
    private String imagen;
    private String cuerpo;
    private String id;
    public Noticia(){}

    public Noticia(String titulo, String imagen, String cuerpo, String id) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.cuerpo = cuerpo;
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
