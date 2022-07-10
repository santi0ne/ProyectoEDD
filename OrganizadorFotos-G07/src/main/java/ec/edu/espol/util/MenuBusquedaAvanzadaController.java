/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.util;

import ec.edu.espol.classes.Album;
import ec.edu.espol.classes.Biblioteca;
import ec.edu.espol.classes.Foto;
import ec.edu.espol.classes.Persona;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import tdas.ArrayListG07;
import tdas.CircularDoublyLinkedListG07;

/**
 * FXML Controller class
 *
 * @author jeras
 */
public class MenuBusquedaAvanzadaController {

    @FXML
    private Button btnRegresar;
    @FXML
    private VBox vboxInformacion;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TilePane galeria;
    @FXML
    private TextArea txtCriterio;
    @FXML
    private Button btnBuscar;
    private Foto fotoseleccionada = new Foto();

    private ArrayListG07<Foto> listaFotos = new ArrayListG07<Foto>();

    @FXML
    private RadioButton rbLugar;
    @FXML
    private RadioButton rbFecha;
    @FXML
    private DatePicker dateCalendario;
    @FXML

    private ComboBox<Persona> cbbPersonas;

    private static ArrayListG07<Persona> personasFiltro = new ArrayListG07();
    @FXML
    private Button btnClean;
    @FXML
    private RadioButton rbTodos;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblLugar;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblPersonas;
    @FXML
    private TilePane panePersonSelect;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        inicializarScroll();
        cargarComboPersonas();
        for (Album al : Biblioteca.getListaAlbumes()) {

            CircularDoublyLinkedListG07<Foto> listFotos = Foto.lecturaSFotos(al);

            for (int i = 0; i < listFotos.size(); i++) {

                Foto picture = listFotos.get(i);

                File file1 = new File("archivos/albumes/" + al.getNombre() + "/" + picture.getNombre());
                Image image = new Image(file1.toURI().toString(), 180, 180, true, true);
                Foto foto = new Foto(picture.getNombre(), picture.getLugar(), picture.getDescripcion(), picture.getFecha(), picture.getPersonas(), image);

                listaFotos.addLast(foto);

                VBox vboxfoto = new VBox();
                ImageView imgview = null;
                try {
                    imgview = new ImageView(image);
                } catch (NullPointerException ex) {
                    imgview = new ImageView();
                }

                vboxfoto.getChildren().add(imgview);
                galeria.getChildren().add(vboxfoto);

                EventHandler eventHandler = (event) -> {
                    lblNombre.setText("Nombre:\n" + foto.getNombre());
                    lblFecha.setText("Fecha:\n" + foto.getFecha().toString());
                    lblLugar.setText("Lugar:\n" + foto.getLugar());
                    lblPersonas.setText("Personas:\n" + foto.toStringPersonas());
                    lblDescripcion.setText("Descripcion:\n" + foto.getDescripcion());
                    fotoseleccionada = foto;
                };

                vboxfoto.setOnMouseClicked(eventHandler);
            }

        }
        System.out.println("Tenemos una lista Inicial de: " + listaFotos.size());

    }

    @FXML
    public void RegresarMenu() throws IOException {
        limpiarGaleria();
        App.setRoot("MenuPrincipal");
    }

    public void inicializarScroll() {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
        scrollPane.setFitToWidth(true);
        galeria.setAlignment(Pos.CENTER);
        galeria.setPadding(new Insets(15, 15, 15, 15));
        galeria.setVgap(30);
        galeria.setHgap(20);
        scrollPane.setContent(galeria);
    }

    @FXML
    public void buscarFotos() {

        ArrayListG07<Foto> listaFiltrada = null;
        if (rbTodos.isSelected()) {
            presentarTodasLasFotos();
        } else if (rbLugar.isSelected() && !(rbFecha.isSelected()) && !(rbTodos.isSelected())) {
            limpiarGaleria();
            listaFiltrada = filtrarLugar(listaFotos, txtCriterio.getText());
            showFotos(listaFiltrada);
        } else if (rbFecha.isSelected() && !(rbLugar.isSelected()) && !(rbTodos.isSelected())) {
            limpiarGaleria();
            LocalDate d = dateCalendario.getValue();
            listaFiltrada = filtrarFecha(listaFotos, d);
            showFotos(listaFiltrada);
        } else if (rbFecha.isSelected() && rbLugar.isSelected()) {
            limpiarGaleria();
            listaFiltrada = filtrarLugar(listaFotos,txtCriterio.getText());
            LocalDate d = dateCalendario.getValue();
            listaFiltrada = filtrarFecha(listaFiltrada,d);
            showFotos(listaFiltrada);
        } else if(personasFiltro!=null && !(rbFecha.isSelected()) && !(rbLugar.isSelected()) && !(rbTodos.isSelected())){
            limpiarGaleria();
            listaFiltrada = filtrarPersona(listaFotos,personasFiltro);
            showFotos(listaFiltrada);        
        }
    }

    public ArrayListG07<Foto> filtrarLugar(ArrayListG07<Foto> listaFotos, String lugar) {
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();

        if (listaFotos.isEmpty()) {
            return listaFiltrada = null;
        } else {
            if(personasFiltro.isEmpty()){
                for (int i = 0; i < listaFotos.size(); i++) {
                    if (lugar.toLowerCase().equals(listaFotos.get(i).getLugar().toLowerCase())) {
                        listaFiltrada.addLast(listaFotos.get(i));
                    }
                }
            
            }else{
                listaFotos = filtrarPersona(listaFotos,personasFiltro);
                for (int i = 0; i < listaFotos.size(); i++) {
                    if (lugar.toLowerCase().equals(listaFotos.get(i).getLugar().toLowerCase())) {
                        listaFiltrada.addLast(listaFotos.get(i));
                    }
                }
            }


        }

        return listaFiltrada;

    }

    public ArrayListG07<Foto> filtrarFecha(ArrayListG07<Foto> listaFotos, LocalDate fecha) {
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();

        if (listaFotos.isEmpty()) {
            System.out.println("No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        } else {
            if (personasFiltro.isEmpty()) {
                for (int i = 0; i < listaFotos.size(); i++) {
                    if (fecha.equals(listaFotos.get(i).getFecha())) {
                        listaFiltrada.addLast(listaFotos.get(i));
                    }
                }

            } else {
                listaFotos = filtrarPersona(listaFotos,personasFiltro);
                for (int i = 0; i < listaFotos.size(); i++) {
                    if (fecha.equals(listaFotos.get(i).getFecha())) {
                        listaFiltrada.addLast(listaFotos.get(i));
                    }
                }
            }
        }

        return listaFiltrada;

    }

    public ArrayListG07<Foto> filtrarPersona(ArrayListG07<Foto> listaFotos, ArrayListG07<Persona> persona) {

        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        boolean personasFound = true;

        if (personasFiltro != null) {
            if (personasFiltro.size() == 1) {
                for (Foto f : listaFotos) {
                    for (Persona p : personasFiltro) {
                        if (comparePersona(p, f)) {
                            listaFiltrada.addLast(f);
                        }
                    }

                }

            } else {
                for(Foto f: listaFotos){
                    for(int i=0;i<personasFiltro.size();i++){
                        if(!(comparePersona(personasFiltro.get(i),f))){
                            personasFound=false;
                        }else{
                            personasFound=true;
                        }
                    }
                    if(personasFound){
                        listaFiltrada.addLast(f);
                    }
                }
                
            }

        }

        return listaFiltrada;

    }

    public boolean comparePersona(Persona persona, Foto f2) {
      
        for (Persona p : f2.getPersonas()) {
            
            if (persona.equals(p)) {
                return true;
            }

        }

        return false;
    }

    public void limpiarGaleria() {
        galeria.getChildren().clear();
    }

    public void showFotos(ArrayListG07<Foto> listaFiltrada) {
        for (Foto f : listaFiltrada) {
            Image image = new Image(f.getImage().getUrl(), 180, 180, true, true);
            VBox vboxfoto = new VBox();
            ImageView imgview = null;

            try {
                imgview = new ImageView(image);
            } catch (NullPointerException ex) {
                imgview = new ImageView();
            }

            vboxfoto.getChildren().add(imgview);
            galeria.getChildren().add(vboxfoto);

            EventHandler eventHandler = (event) -> {
                lblNombre.setText("Nombre:\n" + f.getNombre());
                lblFecha.setText("Fecha:\n" + f.getFecha().toString());
                lblLugar.setText("Lugar:\n" + f.getLugar());
                lblPersonas.setText("Personas:\n" + f.toStringPersonas());
                lblDescripcion.setText("Descripcion:\n" + f.getDescripcion());
                fotoseleccionada = f;
            };

            vboxfoto.setOnMouseClicked(eventHandler);

        }

    }

    public void cargarComboPersonas() {

        ArrayListG07<Persona> lista = listaPersonasExistentes();

        for (Persona persona : lista) {
            cbbPersonas.getItems().addAll(persona);
        }

    }

    public ArrayListG07<Persona> listaPersonasExistentes() {

        ArrayListG07<Persona> listaPersonasTDA = Persona.lecturaPersonas();

        return listaPersonasTDA;
    }

    @FXML
    public void comboboxEvents(ActionEvent e) {
        Object evt = e.getSource();
        Persona persona = cbbPersonas.getSelectionModel().getSelectedItem();

        if (evt.equals(cbbPersonas)) {
            Button nombre = new Button();
            nombre.setText(persona.getNombre() + " " + persona.getApellido());

            try {
                if (personasFiltro == null) {
                    panePersonSelect.getChildren().add(nombre);
                    personasFiltro.addFirst(persona);
                } else {
                    if (personasFiltro.contains(persona)) {
                    } else {
                        panePersonSelect.getChildren().add(nombre);
                        personasFiltro.addLast(persona);
                    }
                }
            } catch (NullPointerException f) {
                System.out.println("NullPointerException thrown!");
            }

        }

    }

    public void radioButtonTodosEvents(ActionEvent e) {
        if (rbTodos.isSelected()) {
            rbLugar.setSelected(false);
            rbFecha.setSelected(false);
        }
    }

    public void radioButtonLugarEvents(ActionEvent e) {
        if (rbLugar.isSelected()) {
            rbTodos.setSelected(false);
        }
    }

    public void radioButtonFechaEvents(ActionEvent e) {
        if (rbFecha.isSelected()) {
            rbTodos.setSelected(false);
        }
    }

    
    
    @FXML
    public void limpiarPersonasFiltro() {
        panePersonSelect.getChildren().clear();
        System.out.println("Habian :"+personasFiltro.size()+" Personas\n");
        txtCriterio.clear();
        lblNombre.setText("-");
        lblFecha.setText("-");
        lblLugar.setText("-");
        lblPersonas.setText("-");
        lblDescripcion.setText("-");
  
        presentarTodasLasFotos();
         try {
                personasFiltro = null;
                 System.out.println("Ahora hay :"+personasFiltro.size()+" Personas\n");
                } catch (NullPointerException ex) {
                    personasFiltro = new ArrayListG07<Persona>();
                    System.out.println("Ahora hay :"+personasFiltro.size()+" Personas\n");
                }
        
    }

    public void presentarTodasLasFotos() {
        limpiarGaleria();

        for (Foto f : listaFotos) {
                Image image = new Image(f.getImage().getUrl(), 180, 180, true, true);
                VBox vboxfoto = new VBox();
                ImageView imgview = null;
                
                try {
                    imgview = new ImageView(image);
                } catch (NullPointerException ex) {
                    imgview = new ImageView();
                }
                
            vboxfoto.getChildren().add(imgview);
            galeria.getChildren().add(vboxfoto);

            EventHandler eventHandler = (event) -> {
                lblNombre.setText("Nombre:\n" + f.getNombre());
                lblFecha.setText("Fecha:\n" + f.getFecha().toString());
                lblLugar.setText("Lugar:\n" + f.getLugar());
                lblPersonas.setText("Personas:\n" + f.toStringPersonas());
                lblDescripcion.setText("Descripcion:\n" + f.getDescripcion());
                fotoseleccionada = f;
            };

            vboxfoto.setOnMouseClicked(eventHandler);
                
        }

    }

}
