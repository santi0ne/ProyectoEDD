/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import static ec.edu.espol.util.AgregarFotoController.mostrarAlerta;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

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
    @FXML
    private Button crearAlbum;
    @FXML
    private MenuItem aggPersona;
    @FXML
    private MenuItem editarPersona;
    @FXML
    private MenuItem eliminarPersona;
    
    Album albumSeleccion=new Album();
    
   
    
    public void initialize() throws IOException {
        
        scrollBiblioteca.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollBiblioteca.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        scrollBiblioteca.setFitToWidth(true);
        biblioteca.setAlignment(Pos.CENTER);
        biblioteca.setPadding(new Insets(15, 15, 15, 15));
        biblioteca.setVgap(30);
        biblioteca.setHgap(20);

        scrollBiblioteca.setContent(biblioteca);

        
        //Iterator<Album> iteradorAlbum= Biblioteca.getListaAlbumes().iterator();
        
        for(Album al: Biblioteca.getListaAlbumes()){
            
            Album album=al;
            

            VBox vboxalbum = new VBox();
            ImageView imgview = null;
            try{
                //agrego la imagen de la miniatura
                InputStream input = App.class.getResource("imágenes/miniaturaAlbum.jpg").openStream();
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
               if(eh.getClickCount()==1){
                   albumSeleccion=album;
               }
               if(eh.getClickCount()==2){
                try {
                    if(album.getFotosDelAlbum().size()!=0){
                    Biblioteca.setAlbumSelec(album);
                    App.setRoot("MenuAlbumes");
                    }
                    else{
                        
                       Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
                       alerta.setTitle("Diálogo de información");
                       alerta.setHeaderText("Álbum vacío");
                       alerta.setContentText("El álbum está vacío, quiere agregar una foto?");
                       Optional<ButtonType> result=alerta.showAndWait();
            
                       if(result.get()==ButtonType.OK){
                            Biblioteca.setAlbumSelec(album);
                            App.setRoot("AgregarFoto");
                       }
                       
                       else{
                           App.setRoot("MenuPrincipal");
                       }
                       
                    }
                } catch (IOException ex) {
                }
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
    
    
    @FXML
    public void Busqueda() throws IOException{
        App.setRoot("MenuBusquedaAvanzada");
    }
    
    @FXML
    public void AcercaDe() throws IOException{
        App.setRoot("MenuAcercaDeGrupo");
    }
    
    @FXML
    public void crearAlbum() throws IOException{
        App.setRoot("AgregarAlbum");
    }
    
    @FXML
    public void agregarFoto() throws IOException{
        AgregarFotoController.setEsEdicion(false);
        App.setRoot("AgregarFoto");
    }
    
    
    @FXML
    public void eliminarAlbum() throws IOException{
        
        Album albumEliminar=albumSeleccion;
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Diálogo de información");
        alerta.setHeaderText("Confirmación de eliminación de álbum");
        alerta.setContentText("Está seguro de borrar el álbum "+albumEliminar.getNombre()+" ?");
        Optional<ButtonType> result=alerta.showAndWait();
            
        if(result.get()==ButtonType.OK){

            Biblioteca.getListaAlbumes().remove(Biblioteca.getListaAlbumes().indexOf(albumEliminar));
            Album.reescrituraAlbum();
            
            FileUtils.deleteDirectory(new File("archivos/albumes/"+albumSeleccion.getNombre()));
            
            Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Diálogo de información");
            alert.setHeaderText("Resultado de la operación");
            alert.setContentText("Álbum borrado exitosamente");
            alert.showAndWait();
            
            App.setRoot("MenuPrincipal");
        }
        
    }
    
    @FXML
    public void crearPersona() throws IOException{
        AgregarPersonaController.setAccion("agregar");
        App.setRoot("AgregarPersona");
    }
    
    @FXML
    public void editarPersona() throws IOException{
        AgregarPersonaController.setAccion("editar");
        App.setRoot("AgregarPersona");
    }
    
    @FXML
    public void eliminarPersona() throws IOException{
        AgregarPersonaController.setAccion("eliminar");
        App.setRoot("AgregarPersona");
    }
    
    public static void mostrarAlerta(Alert.AlertType tipo, String msj){
        Alert alert= new Alert(tipo);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText(msj);
        alert.showAndWait();
        
    }
    
    
}
