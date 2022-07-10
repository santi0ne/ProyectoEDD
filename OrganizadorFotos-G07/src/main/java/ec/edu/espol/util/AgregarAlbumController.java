/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import exception.AlbumException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
        
        try{
        
        if(nombreAlbum.getText().equals("")){
            throw new AlbumException("Nombre vacío");
        }
        
        if(descripcionAlbum.getText().equals("")){
            throw new AlbumException("Descripción vacía");
        }
    
        Album album=new Album(nombreAlbum.getText(),descripcionAlbum.getText());
        
        File directorio = new File("archivos/albumes/"+nombreAlbum.getText());
        directorio.mkdirs();
        
        FileOutputStream fout= new FileOutputStream("archivos/albumes/"+album.getNombre()+"/infoFotos.ser");
        fout.close();
        
        album.setFotosSinImage(new CircularDoublyLinkedListG07<Foto>());
        
        Biblioteca.getListaAlbumes().addLast(album);
        Biblioteca.setAlbumSelec(album);
        //Foto.serializarFoto();
        album.escribirAlbum();
        
        
        for(Album a:Biblioteca.getListaAlbumes()){
            System.out.println(a);
        }
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Se ha creado el álbum "+album.getNombre()+" exitosamente");
        alert.showAndWait();
        
         App.setRoot("MenuPrincipal");
         
         } catch (AlbumException ex) {
            mostrarAlerta(Alert.AlertType.ERROR,ex.getMessage());
        }
    }
    
    public static void mostrarAlerta(Alert.AlertType tipo, String msj){
        Alert alert= new Alert(tipo);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText(msj);
        alert.showAndWait();
        
    }
    
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
}
