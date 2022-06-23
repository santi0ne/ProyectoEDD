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
import java.util.Arrays;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tdas.CircularDoublyLinkedListG07;

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
    
    private static Foto fotoSeleccionada=new Foto();
    
    private static Album albumSeleccionado=new Album();
    @FXML
    private Button btnRegresar;
    
    
   
    
    public  void initialize() throws FileNotFoundException, IOException {
       
            for(int i=0;i<Biblioteca.getListaAlbumes().size();i++){
               
            File directorioF = new File("archivos/albumes/"+Biblioteca.getListaAlbumes().get(i).getNombre()); 
            
            String[] listaF = directorioF.list();
           
            
            CircularDoublyLinkedListG07<Foto> listaFotos=new CircularDoublyLinkedListG07<Foto>();
            for (int j = 0; j < listaF.length;j++) {
                File file = new File("archivos/albumes/"+Biblioteca.getListaAlbumes().get(i).getNombre()+"/"+listaF[j]);
                Image image = new Image(file.toURI().toString());
                
                Foto foto=new Foto(listaF[j],image);
                
                listaFotos.add(listaFotos.size(), foto);
                
        }
            Biblioteca.getListaAlbumes().get(i).setFotosDelAlbum(listaFotos);
        }
        
        albumSeleccionado=Biblioteca.getListaAlbumes().get(0);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(0);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    
    
    
    }
    
    @FXML
    public void siguienteFoto(){
        int indice=albumSeleccionado.getFotosDelAlbum().indexOf(fotoSeleccionada);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(indice+1);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void anteriorFoto(){
        int indice=albumSeleccionado.getFotosDelAlbum().indexOf(fotoSeleccionada);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(indice-1);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    public void cambiarAlbum(Album a){
        albumSeleccionado=a; 
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(0);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
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
    public void agregarFoto(){
        //TODO;
    }
    
    @FXML
    public void editarFoto(){
        //TODO;
    }
    
    @FXML
    public void eliminarFoto(){
        //TODO;
    }
    
}
