/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuAcercaDeGrupoController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label txtParrafo;
    @FXML
    private Label lblDesarroladores;
    @FXML
    private ImageView imgSantiago;
    @FXML
    private ImageView imgGuillermo;
    @FXML
    private ImageView imgJefferson;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colocarImagenBoton();
    }    
    
   @FXML
    public void RegresarMenu() throws IOException{
         App.setRoot("MenuPrincipal");
    }
    
    public void colocarImagenBoton(){
        URL linkHome = getClass().getResource("/ec/edu/espol/util/imágenes/casa.png");
        Image imgHome = new Image(linkHome.toString(), 20,20, false, true);
        btnRegresar.setGraphic(new ImageView(imgHome));
    }
}
