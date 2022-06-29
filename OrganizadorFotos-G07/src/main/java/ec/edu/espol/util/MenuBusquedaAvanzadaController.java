/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import tdas.ArrayListG07;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuBusquedaAvanzadaController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private VBox vboxInformacion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TilePane galeria;
    @FXML
    private TextArea txtCriterio;
    @FXML
    private Button btnBuscar;
    private  Foto fotoseleccionada = new Foto();
    private ArrayListG07<Foto> listaFotos = Foto.cargarAllFotos();
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPersonas;
    @FXML
    private Label lblDescripcion;
    @FXML
    private RadioButton rbLugar;
    @FXML
    private RadioButton rbFecha;
    @FXML
    private RadioButton rbPersona;
    @FXML
    private DatePicker dateCalendario;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       inicializarScroll();
        
        ArrayListG07<Foto> listaFotos = new ArrayListG07<Foto>();

        for (int i = 0; i < Biblioteca.getListaAlbumes().size(); i++) {
            File directorioF1 = new File("archivos/albumes/" + Biblioteca.getListaAlbumes().get(i).getNombre());

            String[] listaF = directorioF1.list();

            for (int j = 0; j < listaF.length; j++) {
                File file1 = new File("archivos/albumes/" + Biblioteca.getListaAlbumes().get(i).getNombre() + "/" + listaF[j]);
                Image image = new Image(file1.toURI().toString(), 100, 100, true, true);
                Foto foto = new Foto(listaF[j], image);
                System.out.println(foto.getNombre());

                listaFotos.insert(listaFotos.size(), foto);

                VBox vboxcausa = new VBox();
                ImageView imgview = null;
                try {
                    imgview = new ImageView(image);
                } catch (NullPointerException ex) {
                    imgview = new ImageView();
                }

                vboxcausa.getChildren().add(imgview);
                galeria.getChildren().add(vboxcausa);
                
                
                EventHandler eventHandler = (event)->{ 
                lblNombre.setText("Nombre"+foto.getNombre());
                fotoseleccionada= foto;
            };
            
            vboxcausa.setOnMouseClicked(eventHandler);
            }
            

        }
        
        

    }    
    
    
    
    @FXML
    public void RegresarMenu() throws IOException{
         App.setRoot("MenuPrincipal");
    }
    
    
    public ArrayListG07<Foto> filtarPorNombre(){
        ArrayListG07<Foto> fotosNombre = new ArrayListG07<>();
        
        return fotosNombre;
    
    }

    public ArrayListG07<Foto> filtarPorLugar() {
        ArrayListG07<Foto> fotosLugar = new ArrayListG07<>();
        
        return fotosLugar;
    }
    
    public ArrayListG07<Foto> filtarPorFecha() {
        ArrayListG07<Foto> fotosFecha = new ArrayListG07<>();
        
        return fotosFecha;
    }
    
    public void inicializarScroll() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        scrollPane.setFitToWidth(true);
        galeria.setAlignment(Pos.CENTER);
        galeria.setPadding(new Insets(15, 15, 15, 15));
        galeria.setVgap(30);
        galeria.setHgap(20);
        scrollPane.setContent(galeria);
    }
    
    @FXML
    public void buscarFotos(){
        
    }
    
    
    public ArrayListG07<Foto> filtrarLugar(){
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        return listaFiltrada;
        
    }
    
    public ArrayListG07<Foto> filtrarFecha(){
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        return listaFiltrada;
        
    }
    public ArrayListG07<Foto> filtrarPersona(){
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        return listaFiltrada;
        
    }
    
    public boolean compareLugar(String lugar,Foto f2){
        if(lugar.toLowerCase().equals(f2.getLugar().toLowerCase())){
            return true;
        }
        return false;
    }
    
    public boolean compareFecha(Date d, Foto f2) {
        if (d.equals(f2.getFecha())) {
            return true;
        }
        return false;
    }

    public boolean comparePersona(String persona, Foto f2) {
        for(int i=0;i<f2.numeroPersonas();i++){
            if(persona.toLowerCase().equals(f2.getPersonas().get(i).getNombre().toLowerCase()))
                return true;
        }
        return false;
    }
    
}
