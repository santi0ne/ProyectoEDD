/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import ec.edu.espol.classes.Persona;
import exception.AlbumException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import tdas.ArrayListG07;

 /* FXML Controller class
 *
 * @author g_are
 */
public class AgregarFotoController {


    @FXML
    private Button btonCancelar;
    @FXML
    private Button btonGuardar;
    @FXML
    private Button btonBuscar;
    @FXML
    private ImageView imgSelecc;
    @FXML
    private TextField nombreFoto;
    @FXML
    private TextField descripcionFoto;
    @FXML
    private TextField lugarFoto;
    @FXML
    private DatePicker fechaFoto;
    @FXML
    private ComboBox<Persona> cbmPersonas;
    @FXML
    private FlowPane personasSeleccionadas;
    
    String ruta=null;
    File file=new File("");
    Image image;
    ArrayListG07<Persona> listaPersonasSeleccionadas= new ArrayListG07<Persona>();
    
    private static boolean esEdicion=false;
    
    
    public void initialize(){
        
        
        if(esEdicion==true){
            btonBuscar.setDisable(true);
            btonBuscar.setVisible(false);
            fechaFoto.setDisable(esEdicion);
            
            File file = new File("archivos/albumes/"+MenuAlbumesController.getAlbum().getNombre()+"/"+MenuAlbumesController.getFoto().getNombre());
            Image image = new Image(file.toURI().toString());
            imgSelecc.setImage(image);
            
            nombreFoto.setText(MenuAlbumesController.getFoto().getNombre());
            fechaFoto.setValue(MenuAlbumesController.getFoto().getFecha());
            lugarFoto.setText(MenuAlbumesController.getFoto().getLugar());
            descripcionFoto.setText(MenuAlbumesController.getFoto().getDescripcion());

            btonGuardar.setOnMouseClicked(eh->{try {
                editarFoto();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
         
        }
        else{
            
            
            for(Persona persona:Persona.lecturaPersonas()){
                cbmPersonas.getItems().addAll(persona);
            }
            
                    
            btonBuscar.setDisable(false);
            btonBuscar.setVisible(true);
            imgSelecc.imageProperty().set(null);
            btonGuardar.setOnMouseClicked(eh->{try {
                guardarFoto();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    public static void setEsEdicion(boolean esEdicion) {
        AgregarFotoController.esEdicion = esEdicion;
    }
    
    
    @FXML
    public void buscarFoto(){
       
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".imágenes(.jpg, .png) ", "jpg", "png");
        fileChooser.setFileFilter(filtro);
        fileChooser.showOpenDialog(fileChooser);
        try {
            ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
            File f = new File(ruta);
            entrada = new Scanner(f);
            while (entrada.hasNext()) {
                System.out.println(entrada.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        
        }
        
        file = new File(ruta);
        
        image = new Image(file.toURI().toString());
        
        imgSelecc.setImage(image);
        nombreFoto.setText(file.getName());
}
    
    
    public void guardarFoto() throws IOException{
        try{
            
        if(image==null){
            throw new AlbumException("No se ha seleccionado una foto");
        }
        Path path1=Paths.get(ruta);
        Path path2=Paths.get("archivos//albumes//"+Biblioteca.getAlbumSelec().getNombre()+"//"+nombreFoto.getText());
        
        ArrayListG07<Persona> listaPersonasFoto=new  ArrayListG07<Persona>();
        
        for(Persona persona:listaPersonasSeleccionadas){
            listaPersonasFoto.addLast(persona);
        }
        
        Foto foto=new Foto(nombreFoto.getText(),lugarFoto.getText(),descripcionFoto.getText(),fechaFoto.getValue(),listaPersonasFoto,image);
        
        Foto foto2=new Foto(nombreFoto.getText(),lugarFoto.getText(),descripcionFoto.getText(),fechaFoto.getValue(),listaPersonasFoto);
        
        if(foto.getNombre().equals("")){
            throw new AlbumException("Nombre vacío");
        }
       
        
        if(!foto.getNombre().substring(foto.getNombre().length()-4, foto.getNombre().length()).equals(".jpg") & !foto.getNombre().substring(foto.getNombre().length()-4, foto.getNombre().length()).equals(".png")  ){
            throw new AlbumException("Nombre sin extension(.jpg o .png)");
        }
        
        if(foto.getLugar().equals("")){
            throw new AlbumException("Información de lugar vacío");
        }
        
        if(foto.getDescripcion().equals("")){
            throw new AlbumException("Descripción vacía");
        }
        
        if(foto.getFecha()==null){
            throw new AlbumException("Fecha vacía");
        }
        
            
        Files.copy(path1, path2);
       
        
        Album albumAgregar=new Album();
  
        
        Biblioteca.getAlbumSelec().aggFotosDelAlbum(foto);
        Biblioteca.getAlbumSelec().aggFotosSinImage(foto2);
        Foto.serializarFoto();
        
        
        App.setRoot("MenuAlbumes");
        
        } catch (AlbumException ex) {
            mostrarAlerta(AlertType.ERROR,ex.getMessage());
        }
        
    }
    
    public void editarFoto() throws IOException {
        try{
            
        Foto foto= MenuAlbumesController.getFoto();
        Foto fotoSinImage=new Foto();
           
        for(int i=0;i<MenuAlbumesController.getAlbum().getFotosDelAlbum().size();i++){
            if(MenuAlbumesController.getAlbum().getFotosSinImage().get(i).equals(foto)){
                fotoSinImage=MenuAlbumesController.getAlbum().getFotosSinImage().get(i);
            }
        }
        
        
        
        Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("Diálogo de información");
        alerta.setHeaderText("Confirmación de edición");
        alerta.setContentText("Está seguro de editar los datos de ésta foto?");
        Optional<ButtonType> result=alerta.showAndWait();
            
        if(result.get()==ButtonType.OK){
            
        File fotoAnterior= new File("archivos/albumes/"+MenuAlbumesController.getAlbum().getNombre()+"/"+foto.getNombre());
        File fotoNueva= new File("archivos/albumes/"+MenuAlbumesController.getAlbum().getNombre()+"/"+nombreFoto.getText());
        
        fotoAnterior.renameTo(fotoNueva);
        
        foto.setNombre(nombreFoto.getText());
        fotoSinImage.setNombre(nombreFoto.getText());
        
        foto.setLugar(lugarFoto.getText());
        fotoSinImage.setLugar(lugarFoto.getText());
        
        foto.setDescripcion(descripcionFoto.getText());
        fotoSinImage.setDescripcion(descripcionFoto.getText());
        
        if(foto.getNombre().equals("")){
            throw new AlbumException("Nombre vacío");
        }
       
        
        if(!foto.getNombre().substring(foto.getNombre().length()-4, foto.getNombre().length()).equals(".jpg") & !foto.getNombre().substring(foto.getNombre().length()-4, foto.getNombre().length()).equals(".png")  ){
            throw new AlbumException("Nombre sin extension(.jpg o .png)");
        }
        
        if(foto.getLugar().equals("")){
            throw new AlbumException("Información de lugar vacío");
        }
        
        if(foto.getDescripcion().equals("")){
            throw new AlbumException("Descripción vacía");
        }
            Foto.serializarFoto();
            App.setRoot("MenuAlbumes");
        }
                       
        else{
            App.setRoot("MenuAlbumes");
        }
 
        
        } catch (AlbumException ex) {
            mostrarAlerta(AlertType.ERROR,ex.getMessage());
        }
    }
    
    
    @FXML
    public void comboboxEvents(ActionEvent e) {
        Object evt = e.getSource();
        Persona persona = cbmPersonas.getSelectionModel().getSelectedItem();
        
        if (evt.equals(cbmPersonas)) {
            Button nombre = new Button();
            nombre.setText(persona.getNombre()+" "+persona.getApellido());
            
            try {
                if (listaPersonasSeleccionadas == null) {
                    personasSeleccionadas.getChildren().add(nombre);
                    listaPersonasSeleccionadas.addFirst(persona);
                } else {
                    if (listaPersonasSeleccionadas.contains(persona)) {
                        System.out.println("YA EXISTE");
                    } else {
                        personasSeleccionadas.getChildren().add(nombre);
                        listaPersonasSeleccionadas.addLast(persona);
                    }
                }
            } catch (NullPointerException f) {
                System.out.println("NullPointerException thrown!");
            }

        }

    }
    

    
    
    public static void mostrarAlerta(AlertType tipo, String msj){
        Alert alert= new Alert(tipo);
        alert.setTitle("Diálogo de información");
        alert.setHeaderText("Resultado de la operación");
        alert.setContentText(msj);
        alert.showAndWait();
        
    }
    
    @FXML
    public void cancelar() throws IOException{
        App.setRoot("MenuAlbumes");
    }
}

