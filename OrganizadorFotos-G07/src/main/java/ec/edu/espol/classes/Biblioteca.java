/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import tdas.ArrayListG07;

/**
 *
 * @author santi
 */
public class Biblioteca {
    
    private static ArrayListG07<Album> listaAlbumes=new ArrayListG07<>();
     private static Album albumSelec=new Album();
    
    public static ArrayListG07<Album> getListaAlbumes() {
        return listaAlbumes;
    }
    
    public static void setAlbumSelec(Album album) {
        albumSelec=album;
    }
    
    public static Album getAlbumSelec() {
        return albumSelec;
    }
   
}
