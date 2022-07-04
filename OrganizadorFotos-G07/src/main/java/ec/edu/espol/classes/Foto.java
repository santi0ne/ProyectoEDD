/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.image.Image;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

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

    public Foto(String nombre, Image imagen, String descripcion) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }
    
    public Foto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "descripcion = " + descripcion + "\n"
               + "lugar = " + lugar + "\n"
                + "fecha = " + fecha + "\n"
                + "date = " + date + "\n"
                + "albumName = " + albumName;
    }
    
    
    
    public static void eliminarPersona(Persona p){
        //personas.remove(p);
    }
    
    public static ArrayListG07<Foto> cargarAllFotos(){
        
        ArrayListG07<Foto> listaFotos= new ArrayListG07<Foto>();
        
         for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
               
             CircularDoublyLinkedListG07<Foto> listFotos= lecturaFotos(Biblioteca.getListaAlbumes().get(i));
             for (int j = 0; j < listFotos.size();j++) {
                 File file = new File("archivos/albumes/"+Biblioteca.getListaAlbumes().get(i).getNombre()+"/"+listFotos.get(i).getNombre());
                 Image image = new Image(file.toURI().toString(),100,100,true,true);
                 
                 Foto foto=new Foto(listFotos.get(i).getNombre(),image,listFotos.get(i).getDescripcion());
                 System.out.println(foto.nombre);
                 
                 
                 listaFotos.insert(listaFotos.size(), foto);
                 
             }
            
        }
         System.out.println(listaFotos);
         return listaFotos;
        
        
    }
    
    
    public static CircularDoublyLinkedListG07<Foto> lecturaFotos(Album a) {
        
        CircularDoublyLinkedListG07<Foto> listaFotos= new CircularDoublyLinkedListG07<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/albumes/"+a.getNombre()+"/infoFotos.txt"))){

            String linea;
            bufferedReader.readLine();
            
            
            while((linea=bufferedReader.readLine())!=null){
                
                String[] info=linea.split(",");
         
                Foto foto=new Foto(info[0],info[1]);
                
                listaFotos.addLast(foto);

            }
            
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
    
        return listaFotos;
    }
    
    public void escribirFoto() {
        //String mascotas="";
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/albumes/"+Biblioteca.getAlbumSelec().getNombre()+"/infoFotos.txt", true))) {
            sb.append("\r\n");
            sb.append(this.nombre).append(","); 
            sb.append(this.descripcion);
            bufferedW.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
}
