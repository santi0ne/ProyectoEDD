/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.util.LinkedList;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Album {
    private String nombre;
    private String descripcion;
    private static CircularDoublyLinkedListG07<Album> listaAlbumes=new CircularDoublyLinkedListG07<>();
    private CircularDoublyLinkedListG07<Foto> fotosDelAlbum=new CircularDoublyLinkedListG07();

    public Album() {}
    
    public Album(String nombre) {
        this.nombre = nombre;
    }

    public Album(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public static CircularDoublyLinkedListG07<Album> getListaAlbumes() {
        return listaAlbumes;
    }

     public CircularDoublyLinkedListG07<Foto> getFotosDelAlbum() {
        return fotosDelAlbum;
    }
     
    public void setFotosDelAlbum(CircularDoublyLinkedListG07<Foto> fotosDelAlbum) {
        this.fotosDelAlbum = fotosDelAlbum;
    }
    
    
}
