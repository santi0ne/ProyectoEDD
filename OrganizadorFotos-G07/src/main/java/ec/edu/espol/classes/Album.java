/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Album {
    private String nombre;
    private String descripcion;
    private String miniatura;
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
    
       public Album(String nombre, String descripcion, String miniatura) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.miniatura = miniatura;
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
    
    public String getMiniatura(){
        return miniatura;
    }
    
    public void setMiniatura(String miniatura){
        this.miniatura=miniatura;
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
    
   
    
   //Aqui se debe implementar el codigo para leer los nombres de las carpetas de albumes.
    //por el momento hemos creado 4 directamente, PERO!! tiene que leer los datos y devolver
    // una lista de algumes lleno.
   public static List<Album> leerAlbumes(){
        List<Album> albumes = Arrays.asList(
                new Album("Album1", "Ejemplo del album 1", "miniaturaAlbum.jpg"),
                new Album("Album2", "Ejemplo de album 2", "miniaturaAlbum1.png"),
                new Album("Album3", "Ejempli del album 3", "miniaturaAlbum2.png"),
                new Album("Album1", "Ejemplo del album 1", "miniaturaAlbum3.png")
        );
        return albumes;
    }
    
}
