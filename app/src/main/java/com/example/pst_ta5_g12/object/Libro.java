package com.example.pst_ta5_g12.object;

public class Libro {
    private String autor,descripcion,editorial,imagen;

    public Libro(){
    }
    public Libro(String autor,String descripcion,String editorial,String imagen){
        this.autor=autor;
        this.descripcion=descripcion;
        this.editorial=editorial;
        this.imagen=imagen;
    }
    //getter
    public String getAutor(){
        return autor;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public String getEditorial(){
        return editorial;
    }
    public String getImagen(){
        return imagen;
    }
    //setter
    public void setAutort(String autor){
        this.autor=autor;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
    public void setEditorial(String editorial){
        this.editorial=editorial;
    }
    public void setImagen(String imagen){
        this.imagen=imagen;
    }
}
