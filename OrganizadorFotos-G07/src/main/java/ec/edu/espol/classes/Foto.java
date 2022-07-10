/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import ec.edu.espol.util.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javafx.scene.image.Image;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Foto implements Serializable{
    private String descripcion;
    private String lugar;
    private LocalDate fecha;
    private ArrayListG07<Persona> personas;
    private Image imagen;
    private String nombre;
    private static final long serialVersionUID = 1111;

    
    public Foto(String nombre,String lugar,String descripcion, LocalDate fecha,ArrayListG07<Persona> personas,Image imagen) {
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.imagen=imagen;
        this.personas = personas;

    }
    
    public Foto(String nombre,String lugar,String descripcion, LocalDate fecha, ArrayListG07<Persona> personas) {
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.lugar = lugar;
        this.fecha = fecha;
        this.personas = personas;

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public LocalDate getDate(){
        return fecha;
    }
    
    public void setDate(LocalDate date){
        this.fecha = fecha;
    }

    public ArrayListG07<Persona> getPersonas() {
        return personas;
    }
    
    /*public String[] getListaPersonas() {
        String[] listapersonas= new String[this.personas.size()];
        int i=0;
        for (String s : this.personas) {
            listapersonas[i]=s;
            i++;
        }
        return listapersonas;
    }*/

   

    public void setPersonas(ArrayListG07<Persona> personas) {
        this.personas = personas;
    }
    
    public int numeroPersonas() {
        return this.personas.size();
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
    
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
    
    
    // metodos 

    @Override
    public String toString() {
        
        String listaPersonas="";
        for(Persona p:personas){
            listaPersonas=listaPersonas+p.nombre+" "+p.apellido+"\n";
        }
        return "descripcion = " + descripcion + "\n"
               + "lugar = " + lugar + "\n"
               + "fecha = " + fecha + "\n"
               + "personas= "+ "\n"+ listaPersonas;
        
    }
    
    
    
    public static void eliminarPersona(Persona p){
        //personas.remove(p);
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
        final Foto other = (Foto) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    } 
    
    public static ArrayListG07<Foto> cargarAllFotos(){
        
        ArrayListG07<Foto> listaFotos= new ArrayListG07<Foto>();
        
         for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
               
             CircularDoublyLinkedListG07<Foto> listFotos= lecturaSFotos(Biblioteca.getListaAlbumes().get(i));
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
   
    
    
  
    public static void serializarFoto(Album album) throws IOException{
            
            FileOutputStream fout= new FileOutputStream("archivos/albumes/"+Biblioteca.getAlbumSelec().getNombre()+"/infoFotos.ser");
            ObjectOutputStream out=new ObjectOutputStream(fout);
            out.writeObject(album.getFotosSinImage());
            System.out.println(Biblioteca.getAlbumSelec());
            out.flush();
            out.close();
            
     }
    
    public static CircularDoublyLinkedListG07<Foto> lecturaSFotos(Album a){
        
       CircularDoublyLinkedListG07<Foto> listaFotos=null;
        
        try{
            ObjectInputStream in= new ObjectInputStream(new FileInputStream("archivos/albumes/"+a.getNombre()+"/infoFotos.ser"));
            listaFotos=(CircularDoublyLinkedListG07<Foto>) in.readObject();
            in.close();
            
        }
        
	catch (FileNotFoundException e){
            System.out.println(e);
        }

        catch (IOException | ClassNotFoundException e){
            System.out.println(e);
        }

        return listaFotos;
    }
    
    
}
