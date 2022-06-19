/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author santi
 */
public class Foto {
    private String descripcion;
    private String lugar;
    private Date fecha;
    private ArrayList<Persona> personas;
    private Album album;

    public Foto(String descripcion, String lugar, Date fecha, ArrayList<Persona> personas, Album album) {
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;
        this.album = album;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    
}
