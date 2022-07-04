/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField nombreImagen;
    @FXML
    private TextField descripcionImagen;
   
    
    String ruta=null;
    File file=new File("");
    Image image;
    
    @FXML
    public void buscarFoto(){
        
        Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter(".jpg", "jpg");
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
        nombreImagen.setText(file.getName());
}
    
    @FXML
    public void guardarFoto() throws IOException{
        Path path1=Paths.get(ruta);
        Path path2=Paths.get("archivos//albumes//"+Biblioteca.getAlbumSelec().getNombre()+"//"+nombreImagen.getText());
        
        try {
            Files.copy(path1, path2);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        Album albumAgregar=new Album();
              
        Foto foto=new Foto(nombreImagen.getText(),image,descripcionImagen.getText());
  
        foto.escribirFoto();
        Biblioteca.getAlbumSelec().aggFotosDelAlbum(foto);
        
        
        App.setRoot("MenuAlbumes");
    }
}

