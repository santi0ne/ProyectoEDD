/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;

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
    private Image imagen;
    private String nombre;

    public Foto(Image imagen,String nombre,String descripcion, String lugar, Date fecha, ArrayList<Persona> personas, Album album) {
        this.imagen=imagen;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;
        this.album = album;
    }
    
    public Foto(){};

    public Foto(String nombre, Image imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
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
    
    public Image getImage() {
        return imagen;
    }
    
    public void setImage(Image imagen) {
        this.imagen=imagen;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombte) {
        this.nombre=nombre;
    }
    
    
    // metodos 
    public static void eliminarPersona(Persona p){
        //personas.remove(p);
    }
    
    
}
