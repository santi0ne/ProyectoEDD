/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 *
 * @author santi
 */
public class Persona {
    String nombre;
    static ArrayListG07<Persona> listaPersonas= new ArrayListG07<>();

    public Persona(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static void setPersonas(ArrayListG07<Persona> listaPersonas){
        Persona.listaPersonas=listaPersonas;
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    } 
    
    public static ArrayListG07<Persona> lecturaPersonas() {
        
        ArrayListG07<Persona> listaPersonas= new ArrayListG07<>();
        
        try(BufferedReader bufferedReader= new BufferedReader(new FileReader ("archivos/personas.txt"))){

            String linea;

            while((linea=bufferedReader.readLine())!=null){
                
                Persona persona=new Persona(linea);
                
                listaPersonas.addLast(persona);
                }

            
            
        } catch (IOException ex) { 
            ex.printStackTrace();
        }
    
        return listaPersonas;
    }
    
    public String toString(){
        return nombre;
    }
}
