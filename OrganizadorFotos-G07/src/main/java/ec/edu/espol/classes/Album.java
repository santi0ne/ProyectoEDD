/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Album implements Serializable {
    private String nombre;
    private String descripcion;
    private String miniatura;
    private CircularDoublyLinkedListG07<Foto> fotosDelAlbum=new CircularDoublyLinkedListG07();
    private CircularDoublyLinkedListG07<Foto> fotosDelAlbumSinImage=new CircularDoublyLinkedListG07();
    private static final long serialVersionUID = 5555;

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
    
    public String getMiniatura(){
        return miniatura;
    }
    
    public void setMiniatura(String miniatura){
        this.miniatura=miniatura;
    }
    

     public CircularDoublyLinkedListG07<Foto> getFotosDelAlbum() {
        return fotosDelAlbum;
    }
     
    public CircularDoublyLinkedListG07<Foto> getFotosSinImage() {
        return fotosDelAlbumSinImage;
    }
     
    public void setFotosDelAlbum(CircularDoublyLinkedListG07<Foto> fotosDelAlbum) {
        this.fotosDelAlbum = fotosDelAlbum;
    }
    
    public void setFotosSinImage(CircularDoublyLinkedListG07<Foto> fotosDelAlbum) {
        this.fotosDelAlbumSinImage = fotosDelAlbum;
    }
    
    public void aggFotosDelAlbum( Foto foto ) {
        fotosDelAlbum.addLast(foto);
    }
    
    public void aggFotosSinImage( Foto foto ) {
        fotosDelAlbumSinImage.addLast(foto);
    }
    
    public String toString(){
        return nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    } 
    
    public static ArrayListG07<Album> lecturaAlbumes() throws FileNotFoundException, IOException{
        
        ArrayListG07<Album> listaAlbumes= new ArrayListG07<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/albumes.txt"))){

            String linea;
            bufferedReader.readLine();
            
            int i=0;
            
            while((linea=bufferedReader.readLine())!=null){
                
                String[] info=linea.split(",");
         
                Album album=new Album(info[0],info[1]);
                
                listaAlbumes.addLast(album);
                i++;
            }
            
        } 
    
        return listaAlbumes;
    }
    
   
    
   public void escribirAlbum() {
        
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/albumes.txt",true))) {
            sb.append("\r\n");
            sb.append(this.nombre).append(",");  
            sb.append(this.descripcion);
            bufferedW.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
   
   
   public static void borradoAlbum() {
        File archivo=new File("archivos/albumes.txt");
        archivo.delete();
        for(Album a:Biblioteca.getListaAlbumes()){
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/albumes.txt", true))) {
            sb.append("nombreAlbum,descripcion");
            sb.append("\r\n");
            sb.append(a.nombre).append(","); 
            sb.append(a.descripcion);
            if(!(Biblioteca.getListaAlbumes().indexOf(a)==(Biblioteca.getListaAlbumes().size()-1))){
            sb.append("\r\n");}
            bufferedW.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
        }
    }
    
}
