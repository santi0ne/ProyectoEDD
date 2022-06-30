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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private ScrollPane scrollBiblioteca;
    @FXML
    private TilePane biblioteca;
   
    
    public void initialize() throws IOException {
        
        scrollBiblioteca.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollBiblioteca.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        scrollBiblioteca.setFitToWidth(true);
        biblioteca.setAlignment(Pos.CENTER);
        biblioteca.setPadding(new Insets(15, 15, 15, 15));
        biblioteca.setVgap(30);
        biblioteca.setHgap(20);

        scrollBiblioteca.setContent(biblioteca);

        
        
  
        for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
            
            Album album=Biblioteca.getListaAlbumes().get(i);
            
            
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
            
            biblioteca.getChildren().add(vboxalbum);
           
                    
           vboxalbum.setOnMouseClicked(eh-> {
                try {
                    Biblioteca.setAlbumSelec(album);
                    App.setRoot("MenuAlbumes");
                } catch (IOException ex) {
                }
            });
        }
          
    }  
       
    @FXML
    public void Salir() throws IOException{
    // escribir codigo para cerrar proyecto
        Stage stage = (Stage) this.btnSalir.getScene().getWindow();
        stage.close();
    }
    
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
