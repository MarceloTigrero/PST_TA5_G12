package com.example.pst_ta5_g12.usuarios;

public class InicioSesion {
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Celular;
    private String Favorito;
    private String Usuario;
    private String Contrasena;

    public InicioSesion() {
    }

    public InicioSesion(String nombre, String apellido, String correo, String celular, String favorito, String usuario, String Contrasena) {
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Correo = correo;
        this.Celular = celular;
        this.Favorito = favorito;
        this.Usuario = usuario;
        this.Contrasena = Contrasena;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        this.Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        this.Correo = correo;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String celular) {
        this.Celular = celular;
    }

    public String getFavorito() {
        return Favorito;
    }

    public void setFavorito(String favorito) {
        this.Favorito = favorito;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        this.Usuario = usuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String contrasena) {
        this.Contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "InicioSesion{" +
                "nombre='" + Nombre + '\'' +
                ", apellido='" + Apellido + '\'' +
                ", correo='" + Correo + '\'' +
                ", celular='" + Celular + '\'' +
                ", favorito='" + Favorito + '\'' +
                ", usuario='" + Usuario + '\'' +
                ", contrasenia='" + Contrasena + '\'' +
                '}';
    }
}
