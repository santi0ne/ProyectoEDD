/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Persona implements Serializable {
    String nombre;
    String apellido;
    static ArrayListG07<Persona> listaPersonas= new ArrayListG07<>();
    private static final long serialVersionUID = 9999;

    public Persona(String nombre,String apellido) {
        this.nombre = nombre;
        this.apellido=apellido;
    }
    
    public Persona(Persona personaEdicion) {
        nombre=personaEdicion.nombre;
        apellido=personaEdicion.apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public static ArrayListG07<Persona> getPersonas(){
        return listaPersonas;
    }
    
    public static void setPersonas(ArrayListG07<Persona> listaPersonas){
        Persona.listaPersonas=listaPersonas;
    }
    
     @Override
    public boolean equals(Object obj) {
        Persona p = (Persona)obj;
        if(obj!=null){
            System.out.println("Entro a comparar");
            String nameComplete = this.toString();
            if(p.toString().equals(nameComplete)){
                System.out.println("Persona fue igual");
                return true;
            }else{
                System.out.println("Persona NO FUE igual");
                return false;
            }
        }else{
            return false;
        }
        
        
       /* if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;*/
    } 
    
    public static ArrayListG07<Persona> lecturaPersonas() {
        
        ArrayListG07<Persona> listaPersonas= new ArrayListG07<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/personas.txt"))){

            String linea;

            while((linea=bufferedReader.readLine())!=null){
                
                String[] info=linea.split(",");
                
                Persona persona=new Persona(info[0],info[1]);
                
                listaPersonas.addLast(persona);
                }

            
            
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
    
        return listaPersonas;
    }
    
    public void escribirPersona() {
        
        StringBuilder sb = new StringBuilder();
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/personas.txt",true))) {
            sb.append("\r\n");
            sb.append(this.nombre).append(",");  
            sb.append(this.apellido);
            bufferedW.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void reescrituraPersona(){
        
        StringBuilder sb = new StringBuilder();
        
        for(Persona p:Persona.getPersonas() ){
            sb.append(p.nombre).append(",");  
            sb.append(p.apellido);
            sb.append("\r\n");
        }    
            
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/personas.txt"))) {
            bufferedW.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
    public String toString(){
        return nombre+" "+apellido;
    }
}
