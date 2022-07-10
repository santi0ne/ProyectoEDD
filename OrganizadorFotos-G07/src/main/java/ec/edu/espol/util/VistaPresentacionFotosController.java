/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class VistaPresentacionFotosController implements Initializable {

    @FXML
    private Pane paneFotoFiltro;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnSalir;
    
    private Foto fotoSeleccionada = new Foto();
    private CircularDoublyLinkedListG07<Foto> listaFotosPresentacion= new CircularDoublyLinkedListG07<Foto>();
    private ListIterator<Foto> iterador;
    @FXML
    private ImageView imageFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        listaFotosPresentacion = cargarFotosFiltradas();
        mostrarFotos();
    }

    @FXML
    public void salirDePresentación() throws IOException{
         App.setRoot("MenuBusquedaAvanzada");
    }
    
    public CircularDoublyLinkedListG07<Foto> cargarFotosFiltradas(){
        CircularDoublyLinkedListG07 fotos = new CircularDoublyLinkedListG07<Foto>();
        
        for(Foto f:MenuBusquedaAvanzadaController.getListaFotosFiltradas()){
            fotos.addLast(f);
        }
        System.out.println("Se cargaron "+fotos.size()+" fotos filtradas");
        return fotos;
    
    }
    
   
    public void mostrarFotos(){
        iterador = cargarFotosFiltradas().iterator();
        fotoSeleccionada= listaFotosPresentacion.getFirst();
        Image image = new Image(fotoSeleccionada.getImage().getUrl());
        imageFoto.setImage(image);
        //nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
    public void siguienteFoto(){
        System.out.println("Entro al iterator NEXT");
        fotoSeleccionada=iterador.next();
        Image image = new Image(fotoSeleccionada.getImage().getUrl());
        imageFoto.setImage(image);
        //nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
        
    }
    
    public void anteriorFoto(){
        fotoSeleccionada=iterador.previous();
        Image image = new Image(fotoSeleccionada.getImage().getUrl());
        imageFoto.setImage(image);
        //nombreFotoSelec.setText(albumSeleccionado.getNombre()+"/"+fotoSeleccionada.getNombre());
    }
    
}
