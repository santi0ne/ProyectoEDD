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
import javafx.scene.image.ImageView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   @FXML
    public void RegresarMenu() throws IOException{
         App.setRoot("MenuPrincipal");
    }
    
    
}
