/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tdas.CircularDoublyLinkedListG07;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class AgregarAlbumController {

    @FXML
    private Button cancelar;
    @FXML
    private Button crear;
    @FXML
    private TextField nombreAlbum;
    @FXML
    private TextField descripcionAlbum;

   
    public void initialize() {
        
    }    
    
    @FXML
    public void crearAlbum() throws IOException{
    
        Album album=new Album(nombreAlbum.getText(),descripcionAlbum.getText());
        
        File directorio = new File("archivos/albumes/"+nombreAlbum.getText());
        directorio.mkdirs();
        
        FileOutputStream fout= new FileOutputStream("archivos/albumes/"+album.getNombre()+"/infoFotos.ser");
        album.setFotosSinImage(new CircularDoublyLinkedListG07<Foto>());
        Biblioteca.setAlbumSelec(album);
        Foto.serializarFoto();
        album.escribirAlbum();
        Biblioteca.getListaAlbumes().addLast(album);
        
         App.setRoot("MenuPrincipal");
    }
    
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
}
