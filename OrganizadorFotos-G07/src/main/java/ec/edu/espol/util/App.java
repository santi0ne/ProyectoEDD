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

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MenuPrincipal"), 740, 480);
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
        ArrayListG07<Album> listaAlbum=Album.lecturaAlbumes();
        Persona.setPersonas(Persona.lecturaPersonas());
        
       
        for (int i = 0; i < listaAlbum.size(); i++) {
            Album album= new Album(listaAlbum.get(i).getId(),listaAlbum.get(i).getNombre(),listaAlbum.get(i).getDescripcion());
            Biblioteca.getListaAlbumes().addLast(album);
            
            for (int j = 0; j < Foto.lecturaFotos(album).size();j++) {
                
                File file = new File("archivos/albumes/"+album.getNombre()+"/"+Foto.lecturaFotos(album).get(j).getNombre());
                Image image = new Image(file.toURI().toString());
                
                Foto foto=new Foto(Foto.lecturaFotos(album).get(j).getNombre(),Foto.lecturaFotos(album).get(j).getLugar(),Foto.lecturaFotos(album).get(j).getDescripcion(),Foto.lecturaFotos(album).get(j).getFecha(),image);
                
                album.aggFotosDelAlbum(foto); 
    
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