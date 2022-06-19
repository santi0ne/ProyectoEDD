/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Foto;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
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
    private VBox listaAlbumes;
    @FXML
    private HBox menúOpciones;
    @FXML
    private Button btnAnt;
    @FXML
    private ImageView imageFoto;
    @FXML
    private Button btnSig;
    @FXML
    private Text nombreFoto;
    private TableColumn<Album, String> columnaAlbumes;
    
    private Foto fotoSeleccionada=new Foto();
    private Album albumSeleccionado=new Album();
    
   
    
    public  void initialize() throws FileNotFoundException, IOException {
       
        File directorio = new File("archivos");                                              
        String[] lista = directorio.list();
        Arrays.sort(lista);
        for (int i = 0; i < lista.length; i++) {
            Album album= new Album(lista[i]);
            Album.getListaAlbumes().add(Album.getListaAlbumes().size(), album);
            
            Button boton=new Button(album.getNombre());
            boton.setOnAction(ev->cambiarAlbum(album));
            
            listaAlbumes.getChildren().add(boton);
        }
        
        
        
        for(int i=0;i<Album.getListaAlbumes().size();i++){
               
            File directorioF = new File("archivos/"+Album.getListaAlbumes().get(i).getNombre());                                              
            String[] listaF = directorioF.list();
            Arrays.sort(listaF);
            CircularDoublyLinkedListG07<Foto> listaFotos=new CircularDoublyLinkedListG07<Foto>();
            for (int j = 0; j < listaF.length;j++) {
                File file = new File("archivos/"+Album.getListaAlbumes().get(i).getNombre()+"/"+listaF[j]);
                Image image = new Image(file.toURI().toString());
                
                Foto foto=new Foto(listaF[j],image);
                
                listaFotos.add(listaFotos.size(), foto);
                
        }
            Album.getListaAlbumes().get(i).setFotosDelAlbum(listaFotos);
        }
        
        albumSeleccionado=Album.getListaAlbumes().get(0);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(0);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFoto.setText(fotoSeleccionada.getNombre());
    
    }


    @FXML
    public void siguienteFoto(){
        int indice=albumSeleccionado.getFotosDelAlbum().indexOf(fotoSeleccionada);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(indice+1);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFoto.setText(fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void anteriorFoto(){
        int indice=albumSeleccionado.getFotosDelAlbum().indexOf(fotoSeleccionada);
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(indice-1);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFoto.setText(fotoSeleccionada.getNombre());
    }
    
    @FXML
    public void cambiarAlbum(Album a){
        albumSeleccionado=a; 
        fotoSeleccionada=albumSeleccionado.getFotosDelAlbum().get(0);
        imageFoto.setImage(fotoSeleccionada.getImage());
        nombreFoto.setText(fotoSeleccionada.getNombre());
    }
    
}
