/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Persona;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author g_are
 */
public class AgregarPersonaController  {


    @FXML
    private Button btonCancelar;
    @FXML
    private Button btonGuardar;
    @FXML
    private ComboBox<?> cmbPersonas;
    @FXML
    private TextField nombrePersona;
    @FXML
    private TextField apellidoPersona;
    
    private static  String accion;
    @FXML
    private Text labelPersona;
    
    
    public void initialize() {
        
        if(accion.equals("agregar")){
            cmbPersonas.setDisable(true);
            cmbPersonas.setVisible(false);
            
            labelPersona.setDisable(true);
            labelPersona.setVisible(false);
            btonGuardar.setOnMouseClicked(eh->{try {
                agregarPersona();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

        } 
        
    }
    
    public static void setAccion(String string){
       accion=string;
    }
    
    public void agregarPersona() throws IOException{
    
        Persona persona= new Persona(nombrePersona.getText(),apellidoPersona.getText());
        
        Persona.getPersonas().addLast(persona);
        
        persona.escribirPersona();
        
        App.setRoot("MenuPrincipal");
}
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
}
