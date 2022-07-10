/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Persona;
import static ec.edu.espol.util.AgregarAlbumController.mostrarAlerta;
import exception.AlbumException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private ComboBox<Persona> cmbPersonas;
    @FXML
    private TextField nombrePersona;
    @FXML
    private TextField apellidoPersona;
    
    private static  String accion;
    @FXML
    private Text labelPersona;
    
    
    public void initialize() {
        colocarImagenBoton();
        for(Persona persona:Persona.lecturaPersonas()){
                cmbPersonas.getItems().addAll(persona);
            }
        
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
        
        else if(accion.equals("editar")){
            cmbPersonas.setDisable(false);
            cmbPersonas.setVisible(true);
            
            labelPersona.setDisable(false);
            labelPersona.setVisible(true);
            
            labelPersona.setText("Seleccione la persona a editar");
            
            btonGuardar.setOnMouseClicked(eh->{try {
                editarPersona((Persona) cmbPersonas.getSelectionModel().getSelectedItem());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
        
        else{
            
            cmbPersonas.setDisable(false);
            cmbPersonas.setVisible(true);
            
            labelPersona.setDisable(false);
            labelPersona.setVisible(true);
            
            labelPersona.setText("Seleccione la persona a eliminar");
            
            nombrePersona.setDisable(true);
            apellidoPersona.setDisable(true);
            
            btonGuardar.setOnMouseClicked(eh->{try {
                eliminarPersona((Persona) cmbPersonas.getSelectionModel().getSelectedItem());
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
        
        try{
        
        if(nombrePersona.getText().equals("")){
            throw new AlbumException("Nombre vacío");
        }
        
        if(apellidoPersona.getText().equals("")){
            throw new AlbumException("Nombre vacío");
        }
    
        Persona persona= new Persona(nombrePersona.getText(),apellidoPersona.getText());
        
        Persona.getPersonas().addLast(persona);
        
        persona.escribirPersona();
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Se ha creado la persona exitosamente");
        alert.showAndWait();
        
        App.setRoot("MenuPrincipal");
        
        } catch (AlbumException ex) {
            mostrarAlerta(Alert.AlertType.ERROR,ex.getMessage());
        }
}
    
    public void editarPersona(Persona personaEditar) throws IOException{
        
        try{
        
        if(nombrePersona.getText().equals("")){
            throw new AlbumException("Nombre vacío");
        }
        
        if(apellidoPersona.getText().equals("")){
            throw new AlbumException("Nombre vacío");
        }
        
        Persona personaEdicion=Persona.getPersonas().get(Persona.getPersonas().indexOf(personaEditar));
        
        int indexPersona=Persona.getPersonas().indexOf(personaEditar);
        System.out.println(indexPersona);
    
        personaEdicion.setNombre(nombrePersona.getText());
        personaEdicion.setApellido(apellidoPersona.getText());
        
        Persona.getPersonas().remove(Persona.getPersonas().indexOf(personaEdicion));
        Persona.getPersonas().addFirst(personaEdicion);

        personaEdicion.reescrituraPersona();
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Se ha editado la persona exitosamente");
        alert.showAndWait();
        
        App.setRoot("MenuPrincipal");
        
        } catch (AlbumException ex) {
            mostrarAlerta(Alert.AlertType.ERROR,ex.getMessage());
        }
    }
    
    public void eliminarPersona(Persona personaEliminar) throws IOException{
        
        Persona.getPersonas().remove(Persona.getPersonas().indexOf(personaEliminar));

        personaEliminar.reescrituraPersona();
        
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText("Se ha eliminado la persona exitosamente");
        alert.showAndWait();
        
        App.setRoot("MenuPrincipal");
    }
    
    
    
    @FXML
    public void comboEvents(ActionEvent e) {
        
        nombrePersona.setText(cmbPersonas.getSelectionModel().getSelectedItem().getNombre());
        apellidoPersona.setText(cmbPersonas.getSelectionModel().getSelectedItem().getApellido());
    }
    
    public static void mostrarAlerta(Alert.AlertType tipo, String msj){
        Alert alert= new Alert(tipo);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText(msj);
        alert.showAndWait();
        
    }
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuPrincipal");
    }
    
    public void colocarImagenBoton(){
        URL linkGuardar = getClass().getResource("/ec/edu/espol/util/imágenes/guardar.png");
        URL linkCancel = getClass().getResource("/ec/edu/espol/util/imágenes/cancelar.png");
        
        Image imgGuardar = new Image(linkGuardar.toString(), 20,20, false, true);
        Image imgCancelar = new Image(linkCancel.toString(), 20,20, false, true);
        
        btonGuardar.setGraphic(new ImageView(imgGuardar));
        btonCancelar.setGraphic(new ImageView(imgCancelar));
    }
    
}
