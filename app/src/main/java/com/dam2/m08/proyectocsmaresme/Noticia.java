package com.dam2.m08.proyectocsmaresme;

public class Noticia {
    private String titulo;
    private String imagen = "https://firebasestorage.googleapis.com/v0/b/proyecto-cs-maresme.appspot.com/o/Hospital_Matar%C3%B3_El_Maresme_Catalonia%20(1).jfif?alt=media&token=97e0c47c-2bde-4648-bda3-df8166d1cdd4";
    private String cuerpo;

    public Noticia(){}

    public Noticia(String titulo, String imagen, String cuerpo) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.cuerpo = cuerpo;
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


}
