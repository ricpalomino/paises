package com.idat.rest.paises.model;

public class Pais {

    private Long id;
    private String nombre;
    private int poblacion;

    public Pais(Long id, String nombre, int poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.poblacion = poblacion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getPoblacion() {
        return poblacion;
    }
    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    

}
