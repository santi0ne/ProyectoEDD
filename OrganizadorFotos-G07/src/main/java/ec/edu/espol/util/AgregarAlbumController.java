/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        
        try (BufferedWriter bufferedW = new BufferedWriter(new FileWriter("archivos/albumes/"+nombreAlbum.getText()+"/infoFotos.txt"))) {
        } catch (IOException e) {
            System.out.println(e);
        }
        album.escribirAlbum();
        Biblioteca.getListaAlbumes().addLast(album);
        
         App.setRoot("MenuPrincipal");
    }
    
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
}
