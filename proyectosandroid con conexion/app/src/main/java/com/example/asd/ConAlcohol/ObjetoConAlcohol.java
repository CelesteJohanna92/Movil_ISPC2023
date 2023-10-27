package com.example.asd.ConAlcohol;

public class ObjetoConAlcohol {

    private byte[] imagen;
    private String nombre;
    private String ingredientes;

    public ObjetoConAlcohol() {
    }

    public ObjetoConAlcohol(byte[] imagen, String nombre, String ingredientes) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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
}
