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
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
    private MenuItem itemEdFoto;
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
    
    private Iterator<Album> iteradorAlbum;
    
      

    public  void initialize() throws FileNotFoundException, IOException {
        if(Biblioteca.getAlbumSelec().getFotosDelAlbum().size()==1){
            btnAnt.setDisable(true);
            btnAnt.setVisible(false);
            btnSig.setDisable(true);
            btnSig.setVisible(false);    
        }
        
        else{
            btnAnt.setDisable(false);
            btnAnt.setVisible(true);
            btnSig.setDisable(false);
            btnSig.setVisible(true);    
        }
        cargarFotos(Biblioteca.getAlbumSelec());
    }
    
    public static Foto getFoto(){
        return fotoSeleccionada;
    }
    
    public static Album getAlbum(){
        return albumSeleccionado;
    }
    
    public void cargarFotos(Album a) throws IOException{
       iteradorAlbum= Biblioteca.getListaAlbumes().iterator();
       
        Album album=new Album();
                    
            for(Album al:Biblioteca.getListaAlbumes()){
                if(al.equals(a)){
                    album=al;
                }
            }
            
        mostrarFoto(album);
        
        iterador= albumSeleccionado.getFotosDelAlbum().iterator();
        
    }
    
    
    public void mostrarFoto(Album a){
        albumSeleccionado=a;
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().getFirst();
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
    
  
    
    @FXML
    public void regresarMenu() throws IOException{
        App.setRoot("MenuPrincipal");
    } 
    
    @FXML
    public void crearAlbum() throws IOException{
        App.setRoot("AgregarAlbum");
    }
    
    public void editarAlbum(){
        //TODO;
    }
    
    
    @FXML
    public void agregarFoto() throws IOException{
        AgregarFotoController.setEsEdicion(false);
        App.setRoot("AgregarFoto");
    }
    
    @FXML
    public void editarFoto() throws IOException{
        AgregarFotoController.setEsEdicion(true);
        App.setRoot("AgregarFoto");
    }
    
    @FXML
    public void eliminarFoto(){
        Foto fotoEliminar=fotoSeleccionada;
        Foto fotoEliminarSinImage=new Foto();
        
        for(int i=0;i<MenuAlbumesController.getAlbum().getFotosDelAlbum().size();i++){
            if(MenuAlbumesController.getAlbum().getFotosSinImage().get(i).equals(fotoEliminar)){
                fotoEliminarSinImage=MenuAlbumesController.getAlbum().getFotosSinImage().get(i);
            }
        }
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Diálogo de información");
        alerta.setHeaderText("Confirmación de eliminación");
        alerta.setContentText("Está seguro de borrar ésta foto?");
        Optional<ButtonType> result=alerta.showAndWait();
            
        if(result.get()==ButtonType.OK){
        
        MenuAlbumesController.getAlbum().getFotosDelAlbum().remove(MenuAlbumesController.getAlbum().getFotosDelAlbum().indexOf(fotoEliminar));
        MenuAlbumesController.getAlbum().getFotosSinImage().remove(MenuAlbumesController.getAlbum().getFotosSinImage().indexOf(fotoEliminarSinImage));
        File borrar= new File("archivos/albumes/"+albumSeleccionado.getNombre()+"/"+fotoEliminar.getNombre());
        borrar.delete();
        try {
            Foto.serializarFoto();
            App.setRoot("MenuAlbumes");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        }
        
        else{
            
        }
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
