/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Album {
    private String nombre;
    private String descripcion;
    private String miniatura;
    private int idAlbum;
    private CircularDoublyLinkedListG07<Foto> fotosDelAlbum=new CircularDoublyLinkedListG07();

    public Album() {}
    
    public Album(String nombre) {
        this.nombre = nombre;
    }

    public Album(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
     public Album(int idAlbum,String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idAlbum=idAlbum;
    }
     
    public int getId() {
        return idAlbum;
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
    

     public CircularDoublyLinkedListG07<Foto> getFotosDelAlbum() {
        return fotosDelAlbum;
    }
     
    public void setFotosDelAlbum(CircularDoublyLinkedListG07<Foto> fotosDelAlbum) {
        this.fotosDelAlbum = fotosDelAlbum;
    }
    
    public static ArrayListG07<Album> lecturaAlbumes() throws FileNotFoundException, IOException{
        
        ArrayListG07<Album> listaAlbumes= new ArrayListG07<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/albumes.txt"))){

            String linea;
            bufferedReader.readLine();
            
            int i=0;
            
            while((linea=bufferedReader.readLine())!=null){
                
                String[] info=linea.split(",");
         
                Album album=new Album(Integer.valueOf(info[0]),info[1],info[2]);
                
                listaAlbumes.addLast(album);
                i++;
            }
            
        } 
    
        return listaAlbumes;
    }
    
   
    
   //Aqui se debe implementar el codigo para leer los nombres de las carpetas de albumes.
    //por el momento hemos creado 4 directamente, PERO!! tiene que leer los datos y devolver
    // una lista de algumes lleno.
   /*public static List<Album> leerAlbumes(){
        List<Album> albumes = Arrays.asList(
                new Album(1,"Album1", "Ejemplo del album 1", "miniaturaAlbum.jpg"),
                new Album(2,"Album2", "Ejemplo de album 2", "miniaturaAlbum1.png"),
                new Album(3,"Album3", "Ejempli del album 3", "miniaturaAlbum2.png"),
                new Album(4,"Album1", "Ejemplo del album 1", "miniaturaAlbum3.png")
        );
        return albumes;
    }*/
    
}
