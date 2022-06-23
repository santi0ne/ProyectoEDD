/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import java.io.IOException;
import java.io.InputStream;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import tdas.ArrayListG07;

/**
 * FXML Controller class
 *
 * @author g_are
 */
public class MenuPrincipalController  {

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
   
    
    public void initialize() throws IOException {
        
        ArrayListG07<Album> listaAlbum=Album.lecturaAlbumes();
       
        for (int i = 0; i < listaAlbum.size(); i++) {
            Album album= new Album(listaAlbum.get(i).getId(),listaAlbum.get(i).getNombre(),listaAlbum.get(i).getDescripcion());
            Biblioteca.getListaAlbumes().addLast(album);
           
        }
        
        for (int i = 0; i < listaAlbum.size(); i++) {
            System.out.println(Biblioteca.getListaAlbumes().get(i).getNombre());
           
        }

        
        for(int i=0;i<listaAlbum.size();i++){
            
            Album album=listaAlbum.get(i);
            
            
            VBox vboxalbum = new VBox();
            ImageView imgview = null;
            try{
                //agrego la imagen de la miniatura
                InputStream input = App.class.getResource("miniaturaAlbum.jpg").openStream();
                Image img = new Image(input, 100,100, true, true);
                imgview = new ImageView(img);
            }catch(NullPointerException | IOException ex){
                //no hay la imagen buscada
                imgview = new ImageView();
            } 
            
            vboxalbum.getChildren().add(imgview);
            vboxalbum.getChildren().add(new Label(album.getNombre()));
            vboxalbum.getChildren().add(new Label(album.getDescripcion()));
            
            paneAlbumes.getChildren().add(vboxalbum);
           
                    
           vboxalbum.setOnMouseClicked(eh-> {
                try {
                    verAlbumDeFotos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
          
    }  
    
    @FXML
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
