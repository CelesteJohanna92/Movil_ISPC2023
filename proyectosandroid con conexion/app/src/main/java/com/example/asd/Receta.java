package com.example.asd;

public class Receta {

    private int id;
    private int idCategoria;
    private int idUsuario;
    private String nombre;
    private String ingredientes;
    private String instrucciones;
    private String imagen;

    private String nombreCategoria;

    // Constructor
    public Receta() {
        // Constructor vacío
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public String getImagenURL() {
        return imagen;
    }

    public void setImagenURL(String imagenURL) {
        this.imagen = imagenURL;
    }

}

