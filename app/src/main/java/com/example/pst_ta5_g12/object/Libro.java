package com.example.pst_ta5_g12.object;

public class Libro {
    private String Nombre,Autor, Descripcion, Editorial, Imagen, Genero;

    public Libro(){
    }

    public Libro(String name,String autor, String descripcion, String editorial, String imagen, String genero) {
        this.Nombre = name;
        this.Autor = autor;
        this.Descripcion = descripcion;
        this.Editorial = editorial;
        this.Imagen = imagen;
        this.Genero = genero;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        this.Autor = autor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.Descripcion = descripcion;
    }

    public String getEditorial() {
        return Editorial;
    }

    public void setEditorial(String editorial) {
        this.Editorial = editorial;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        this.Imagen = imagen;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        this.Genero = genero;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "Name='" + Nombre + '\'' +
                ", Autor='" + Autor + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Editorial='" + Editorial + '\'' +
                ", Imagen='" + Imagen + '\'' +
                ", Genero='" + Genero + '\'' +
                '}';
    }
}
