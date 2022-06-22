/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class MenuPrincipalController implements Initializable {

    @FXML
    private MenuItem menuNuevaBiblioteca;
    @FXML
    private MenuItem menuBusqueda;
    @FXML
    private MenuItem menuAcercaDeG07;
    @FXML
    private Button btnSalir;
    @FXML
    private Button btnEliminarBiblioteca;
    @FXML
    private Button btnModificarBiblioteca;
    @FXML
    private FlowPane paneAlbumes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         List<Album> albumes = Album.leerAlbumes();
        for(Album a : albumes){
            VBox vboxalbum = new VBox();
            ImageView imgview = null;
            try{
                //agrego la imagen de la miniatura
                InputStream input = App.class.getResource(a.getMiniatura()).openStream();
                Image img = new Image(input, 100,100, true, true);
                imgview = new ImageView(img);
            }catch(NullPointerException | IOException ex){
                //no hay la imagen buscada
                imgview = new ImageView();
            } 
            
            vboxalbum.getChildren().add(imgview);
            vboxalbum.getChildren().add(new Label(a.getNombre()));
            vboxalbum.getChildren().add(new Label(a.getDescripcion()));
            
            paneAlbumes.getChildren().add(vboxalbum);
            
            EventHandler eventHandler = (event)->{
                // EN ESTE BLOQUE DE CODIGO SE ESCRIBIRA LA ACCION QUE
                //SE REALIZARA CUANDO SE CLICKEA UN ALBUM y se muestra las fotos dentro del album
            };
                    
            vboxalbum.setOnMouseClicked(eventHandler);
        }
        
        
        
        
        
        
    }  
    
    private void verAlbumDeFotos() throws IOException {
        App.setRoot("MenuAlbumes");
    }
    
    public void Salir() throws IOException{
    // escribir codigo para cerrar proyecto
    }
    
    @FXML
    public void EliminarBiblioteca(){
    
    }
    
    @FXML
    public void ModificarBiblioteca(){
    
    }

    
    // Metodos de la barra de menu
    
    @FXML
    public void NuevaBiblioteca() throws IOException{
          App.setRoot("MenuAgregarBiblioteca");
    }
    
    
    @FXML
    public void Busqueda() throws IOException{
        App.setRoot("MenuBusquedaAvanzada");
    }
    
    @FXML
    public void AcercaDe() throws IOException{
        App.setRoot("MenuAcercaDeGrupo");
    }
    
    
}
