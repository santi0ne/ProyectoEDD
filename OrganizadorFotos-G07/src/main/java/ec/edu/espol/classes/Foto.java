/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import javafx.scene.image.Image;
import tdas.ArrayListG07;

/**
 *
 * @author santi
 */
public class Foto {
    private String descripcion;
    private String lugar;
    private Date fecha;
    private LocalDate date;
    private ArrayList<Persona> personas;
    private String[] listapersonas;
    private Album album;
    private Image imagen;
    private String nombre;
    private String albumName;

    public Foto(Image imagen,String nombre,String descripcion, String lugar, Date fecha, ArrayList<Persona> personas, Album album) {
        this.imagen=imagen;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;
        this.album = album;
    }
    
        public Foto(Image imagen,String nombre,String descripcion, String lugar, LocalDate date, String[] listapersonas, String album) {
        this.imagen=imagen;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.date = date;
        this.listapersonas = listapersonas;
        this.albumName = album;
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
    
    public LocalDate getDate(){
        return date;
    }
    
    public void setDate(LocalDate date){
        this.date = date;
    }

    public ArrayList<Persona> getPersonas() {
        return personas;
    }
    
      public String[] getListaPersonas() {
        return listapersonas;
    }

    public void setPersonas(ArrayList<Persona> personas) {
        this.personas = personas;
    }
    
    public int numeroPersonas() {
        return this.listapersonas.length;
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
    
     public static ArrayListG07<Foto> cargarAllFotos(){
        
        ArrayListG07<Foto> listaFotos= new ArrayListG07<Foto>();
        
         for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
               
            File directorioF = new File("archivos/albumes/"+Biblioteca.getListaAlbumes().get(i).getNombre()); 
            // System.out.println(directorioF);
            
            String[] listaF = directorioF.list();
             //System.out.println(listaF);
           
            for (int j = 0; j < listaF.length;j++) {
                File file = new File("archivos/albumes/"+Biblioteca.getListaAlbumes().get(i).getNombre()+"/"+listaF[j]);
                Image image = new Image(file.toURI().toString(),100,100,true,true);
                
                Foto foto=new Foto(listaF[j],image);
                System.out.println(foto.nombre);
                
                
                listaFotos.insert(listaFotos.size(), foto);
                
        }
            
        }
         System.out.println(listaFotos);
         return listaFotos;
        
        
    }
    
    
    
    
}
