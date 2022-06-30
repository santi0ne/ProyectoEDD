package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
       
        for (int i = 0; i < listaAlbum.size(); i++) {
            Album album= new Album(listaAlbum.get(i).getId(),listaAlbum.get(i).getNombre(),listaAlbum.get(i).getDescripcion());
            Biblioteca.getListaAlbumes().addLast(album);
           
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