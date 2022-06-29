/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuBusquedaAvanzadaController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private CheckBox chckNombre;
    @FXML
    private CheckBox chckPersona;
    @FXML
    private VBox vboxInformacion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TilePane galeria;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        scrollPane.setFitToWidth(true);
        galeria.setAlignment(Pos.CENTER);
        galeria.setPadding(new Insets(15, 15, 15, 15));
        galeria.setVgap(30);
        galeria.setHgap(20);

        scrollPane.setContent(galeria);

        VBox vboxfoto = new VBox();
        VBox vboxfoto2 = new VBox();
        VBox vboxfoto3 = new VBox();
        VBox vboxfoto4 = new VBox();
            ImageView imgview = null;
            ImageView imgview2 = null;
            ImageView imgview3 = null;
            ImageView imgview4 = null;
            try{
                //agrego la imagen del aagente
                InputStream input = App.class.getResource("foto1.jpg").openStream();
                Image img = new Image(input,150,150, true, true);
                imgview = new ImageView(img);

                InputStream input2 = App.class.getResource("foto2.jpg").openStream();
                Image img2 = new Image(input2,150,150, true, true);
                imgview2 = new ImageView(img2);
                
                InputStream input3 = App.class.getResource("foto3.jpg").openStream();
                Image img3 = new Image(input3,150,150, true, true);
                imgview3 = new ImageView(img3);
                
                InputStream input4 = App.class.getResource("foto4.jpg").openStream();
                Image img4 = new Image(input4,150,150, true, true);
                imgview4 = new ImageView(img4);
                
            }catch(NullPointerException | IOException ex){
                //no hay la imagen buscada
                imgview = new ImageView();
            } 
            
            vboxfoto.getChildren().add(imgview);
            vboxfoto2.getChildren().add(imgview2);
            vboxfoto3.getChildren().add(imgview3);
            vboxfoto4.getChildren().add(imgview4);
    
            
            galeria.getChildren().add(vboxfoto);
            galeria.getChildren().add(vboxfoto2);
            galeria.getChildren().add(vboxfoto3);
            galeria.getChildren().add(vboxfoto4);
        
    }    
    
    
    
    @FXML
    public void RegresarMenu() throws IOException{
         App.setRoot("MenuPrincipal");
    }
    
}
