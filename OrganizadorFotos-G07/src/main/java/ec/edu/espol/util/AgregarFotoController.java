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
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
    private TableView tablaPersonas;
    @FXML
    private TableColumn<Persona, String> listaPersonas;
    
    String ruta=null;
    File file=new File("");
    Image image;
    
   
    
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
    
    @FXML
    public void guardarFoto() throws IOException{
        try{
            
        if(image==null){
            throw new AlbumException("No se ha seleccionado una foto");
        }
        Path path1=Paths.get(ruta);
        Path path2=Paths.get("archivos//albumes//"+Biblioteca.getAlbumSelec().getNombre()+"//"+nombreFoto.getText());
        
        Foto foto=new Foto(nombreFoto.getText(),lugarFoto.getText(),descripcionFoto.getText(),fechaFoto.getValue(),image);
        
        
        
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
  
        foto.escribirFoto();
        Biblioteca.getAlbumSelec().aggFotosDelAlbum(foto);
        
        
        App.setRoot("MenuAlbumes");
        
        } catch (AlbumException ex) {
            mostrarAlerta(AlertType.ERROR,ex.getMessage());
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

