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

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuAgregarBibliotecaController implements Initializable {

    @FXML
    private Button btnAgregarFotos;
    @FXML
    private Button btncCancelarAlbum;
    @FXML
    private Button btnCrearFoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }


    @FXML
    public void agregarFotos(){
    
    }
    
    
    @FXML
    public void cancelarAlbum() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
    @FXML
    public void crearAlbum(){
    
    }
    
}
