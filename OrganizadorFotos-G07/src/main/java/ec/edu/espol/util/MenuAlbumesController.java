/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.*;
import java.util.ListIterator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author g_are
 */
public class MenuAlbumesController {


    @FXML
    private MenuBar menuBar;
    @FXML
    private HBox menúOpciones;
    @FXML
    private Button btnAnt;
    @FXML
    private ImageView imageFoto;
    @FXML
    private Button btnSig;
    @FXML
    private Text nombreFotoSelec;
    @FXML
    private Menu menuArchivos;
    @FXML
    private MenuItem itemAggAlbum;
    @FXML
    private MenuItem itemAggFotos;
    @FXML
    private MenuItem itemEdAlbum;
    @FXML
    private Button btnEliminarFoto;
    @FXML
    private Button btnEditarFoto;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnInfo;
    
    private static Foto fotoSeleccionada=new Foto();
    
    private static Album albumSeleccionado=new Album();
    
    private ListIterator<Foto> iterador;
      

    public  void initialize() throws FileNotFoundException, IOException {
        cargarFotos(Biblioteca.getAlbumSelec());
    }
    
    public void cargarFotos(Album a) throws IOException{
        Album album=new Album();
                    
            for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
                if(Biblioteca.getListaAlbumes().get(i).equals(a)){
                    album=Biblioteca.getListaAlbumes().get(i);
                }
            }
            
        mostrarFotos(album);
        iterador= albumSeleccionado.getFotosDelAlbum().iterator();
    }
    
    //Iterator<Foto> iterador= albumSeleccionado.getFotosDelAlbum().iterator();
    
    public void mostrarFotos(Album a){
        albumSeleccionado=a;
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().getLast();
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void siguienteFoto(){
        
        fotoSeleccionada=iterador.next();
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void anteriorFoto(){
        fotoSeleccionada=iterador.previous();
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    public void cambiarAlbum(Album a){
        albumSeleccionado=a; 
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(0);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void regresarMenu() throws IOException{
        App.setRoot("MenuPrincipal");
    } 
    
    @FXML
    public void agregarAlbum(){
        //TODO;
    }
    
    @FXML
    public void editarAlbum(){
        //TODO;
    }
    
    public void eliminarAlbum(){
        //TODO;
    }
    
    @FXML
    public void agregarFoto() throws IOException{
        App.setRoot("AgregarFoto");
    }
    
    @FXML
    public void editarFoto(){
        //TODO;
    }
    
    @FXML
    public void eliminarFoto(){
        //TODO;
    }
    
    @FXML
    public void informacionFoto(){
        //todo;
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle("Informacion de foto");
        dialogo.setHeaderText(fotoSeleccionada.getNombre());
        dialogo.setContentText(fotoSeleccionada.toString());
        dialogo.initStyle(StageStyle.UTILITY);
        dialogo.showAndWait();
    }
}
