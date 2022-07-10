package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import ec.edu.espol.classes.Persona;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    static ArrayListG07<Album> listaAlbum;

    public static Scene getScene() {
        return scene;
    }
    
    

    public static ArrayListG07<Album> getListaAlbum() {
        return listaAlbum;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuPrincipal"), 800, 600);
        scene.getStylesheets().add("ec/edu/espol/css/InterfazCSS.css");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    
    
    public static void cargarArchivos() throws IOException{
        listaAlbum=Album.lecturaAlbumes();
        Persona.setPersonas(Persona.lecturaPersonas());
        
       
        for (Album al:listaAlbum) {
           
            Album album= new Album(al.getNombre(),al.getDescripcion());
            Biblioteca.getListaAlbumes().addLast(album);
         
            
            if(!Foto.lecturaSFotos(album).isEmpty()){
            for (int j = 0; j < Foto.lecturaSFotos(album).size();j++) {
                
                Foto picture=Foto.lecturaSFotos(album).get(j);
                album.setFotosSinImage(Foto.lecturaSFotos(album));
       
                
                File file = new File("archivos/albumes/"+album.getNombre()+"/"+picture.getNombre());
                Image image = new Image(file.toURI().toString());
                
                Foto foto=new Foto(picture.getNombre(),picture.getLugar(),picture.getDescripcion(),picture.getFecha(),picture.getPersonas(),image);
                
                album.aggFotosDelAlbum(foto); 
    
            }
            }
           
        }
    }

    public static void main(String[] args) {
        try {
            cargarArchivos();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        launch();
    }
    

}