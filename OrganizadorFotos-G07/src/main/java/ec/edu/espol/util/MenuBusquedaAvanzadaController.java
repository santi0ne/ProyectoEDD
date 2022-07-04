/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
import tdas.CircularDoublyLinkedListG07;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuBusquedaAvanzadaController  {

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
    
    private ArrayListG07<Foto> listaFotos; //= Foto.cargarAllFotos();
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
    
    public void initialize() {
       inicializarScroll();
        
        ArrayListG07<Foto> listaFotos = new ArrayListG07<Foto>();

        for (int i = 0; i < Biblioteca.getListaAlbumes().size(); i++) {
            
            CircularDoublyLinkedListG07<Foto> listFotos= Foto.lecturaFotos(Biblioteca.getListaAlbumes().get(i));

            for (int j = 0; j < listFotos.size(); j++) {
                File file1 = new File("archivos/albumes/" + Biblioteca.getListaAlbumes().get(i).getNombre() + "/" + listFotos.get(j).getNombre());
                Image image = new Image(file1.toURI().toString(), 100, 100, true, true);
                Foto foto = new Foto(listFotos.get(j).getNombre(),listFotos.get(j).getLugar(),listFotos.get(j).getDescripcion(),listFotos.get(j).getFecha(), image);

                listaFotos.insert(listaFotos.size(), foto);

                VBox vboxfoto = new VBox();
                ImageView imgview = null;
                try {
                    imgview = new ImageView(image);
                } catch (NullPointerException ex) {
                    imgview = new ImageView();
                }

                vboxfoto.getChildren().add(imgview);
                galeria.getChildren().add(vboxfoto);
                
                
                EventHandler eventHandler = (event)->{ 
                lblNombre.setText(foto.getNombre());
                lblFecha.setText("Fecha: "+foto.getDate().toString());
                lblDescripcion.setText("Descripción: "+foto.getDescripcion());
                fotoseleccionada= foto;
            };
            
            vboxfoto.setOnMouseClicked(eventHandler);
            }
            

        }
        
        

    }    
    
    
    
    @FXML
    public void RegresarMenu() throws IOException{
        limpiarGaleria();
        App.setRoot("MenuPrincipal");
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
        ArrayListG07<Foto> listaFiltrada = null;
        if(rbLugar.isSelected()){
            limpiarGaleria();
            listaFiltrada = filtrarLugar(txtCriterio.getText());
            showFotos(listaFiltrada);
        }else if(rbPersona.isSelected()){
            limpiarGaleria();
            listaFiltrada = filtrarPersona(txtCriterio.getText());
            showFotos(listaFiltrada);
        }else if(rbFecha.isSelected()){
            limpiarGaleria();
            LocalDate d = dateCalendario.getValue();
            txtCriterio.setText(d.toString());
            listaFiltrada = filtrarFecha(d);
            showFotos(listaFiltrada);
        }
    }
    
    
    public ArrayListG07<Foto> filtrarLugar(String lugar){
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        
        if(listaFotos.isEmpty()){
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println( "No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        }else{
            for(int i=0;i<listaFotos.size();i++){
                if(lugar.toLowerCase().equals(listaFotos.get(i).getLugar().toLowerCase())){
                    listaFiltrada.addLast(listaFotos.get(i));
                }
            }
        
        }
        
        return listaFiltrada;
        
    }
    
    public ArrayListG07<Foto> filtrarFecha(LocalDate fecha){
          ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        
        if(listaFotos.isEmpty()){
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println( "No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        }else{
            for(int i=0;i<listaFotos.size();i++){
                if(fecha.equals(listaFotos.get(i).getFecha())){
                    listaFiltrada.addLast(listaFotos.get(i));
                }
            }
        
        }
        
        return listaFiltrada;
        
        
    }
    
    public ArrayListG07<Foto> filtrarPersona(String persona){
         ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        
        if(listaFotos.isEmpty()){
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println( "No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        }else{
            for(int i=0;i<listaFotos.size();i++){
                if(comparePersona(persona,listaFotos.get(i))){
                    listaFiltrada.addLast(listaFotos.get(i));
                }
            }
        
        }
        
        return listaFiltrada;
        
    }
    
    public boolean comparePersona(String persona, Foto f2) {
       ArrayListG07<String> p = f2.getPersonas();
        for(int i=0;i<f2.numeroPersonas();i++){
            if(persona.toLowerCase().equals(p.get(i).toLowerCase()))
                return true;
        }
        return false;
    }
    
    public void limpiarGaleria(){
        galeria.getChildren().clear();
    }
    
    public void showFotos(ArrayListG07<Foto> listaFiltrada) {
   
        for (int i = 0; i < listaFiltrada.size(); i++) {
            String url = listaFiltrada.get(i).getImage().getUrl();
            Image image = new Image(url, 100, 100, true, true);
            System.out.println("Se creo miniatura: " + listaFiltrada.get(i).getNombre());
            VBox vboxfoto = new VBox();
            ImageView imgview = null;
            try {
                imgview = new ImageView(image);
            } catch (NullPointerException ex) {
                imgview = new ImageView();
            }

            vboxfoto.getChildren().add(imgview);
            galeria.getChildren().add(vboxfoto);
        }
    }
    
    
    
}
