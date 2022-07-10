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

    private ArrayListG07<Foto> listaFotos=new ArrayListG07<Foto>(); //= Foto.cargarAllFotos();
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblFecha;
    @FXML
    private Label lblPersonas;
    @FXML
    private Label lblDescripcion;
    @FXML
    private RadioButton rbLugar;
    @FXML
    private RadioButton rbFecha;
    @FXML
    private DatePicker dateCalendario;
    @FXML
    
    private ComboBox<Persona> cbbPersonas;

    private static ArrayListG07<Persona> personasFiltro=new ArrayListG07();
    @FXML
    private HBox hboxPersonas;
    @FXML
    private Button btnClean;
    @FXML
    private RadioButton rbTodos;

    /**
     * Initializes the controller class.
     */
    public void initialize() {
         inicializarScroll();
         cargarComboPersonas();
        
        ArrayListG07<Foto> listaFotos = new ArrayListG07<Foto>();

        for (Album al:Biblioteca.getListaAlbumes()) {
            
            CircularDoublyLinkedListG07<Foto> listFotos= Foto.lecturaSFotos(al);

            for (int i=0;i<listFotos.size();i++) {
                
                Foto picture=listFotos.get(i);
                
                File file1 = new File("archivos/albumes/" + al.getNombre() + "/" + picture.getNombre());
                Image image = new Image(file1.toURI().toString(), 100, 100, true, true);
                Foto foto = new Foto(picture.getNombre(),picture.getLugar(),picture.getDescripcion(),picture.getFecha(),picture.getPersonas(), image);

                listaFotos.insert(listaFotos.size(), foto);

                VBox vboxfoto = new VBox();
                ImageView imgview = null;
                try {
                    imgview = new ImageView(image);
                } catch (NullPointerException ex) {
                    imgview = new ImageView();
                }

                vboxfoto.getChildren().add(imgview);
                galeria.getChildren().add(vboxfoto);
                
                
                EventHandler eventHandler = (event)->{ 
                lblNombre.setText(foto.getNombre());
                lblFecha.setText("Fecha: "+foto.getDate().toString());
                lblDescripcion.setText("Descripción: \n"+foto.getDescripcion());
                
                StringBuilder personas = new StringBuilder();
                personas.append("Personas:  \n");
                for(Persona p:foto.getPersonas()){
                    personas.append(p.getNombre()).append(" ");  
                    personas.append(p.getApellido()).append(" ");
                    personas.append("\n");
                }
                
                lblPersonas.setText(personas.toString());
                fotoseleccionada= foto;
            };
            
            vboxfoto.setOnMouseClicked(eventHandler);
            }
            

        }
        
        

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

        if (rbLugar.isSelected() && !(rbFecha.isSelected()) && (rbTodos.isSelected())) {
            limpiarGaleria();
            listaFiltrada = filtrarLugar(listaFotos, txtCriterio.getText());
            showFotos(listaFiltrada);
        } else if (rbFecha.isSelected() && !(rbLugar.isSelected()) && (rbTodos.isSelected())) {
            limpiarGaleria();
            LocalDate d = dateCalendario.getValue();
            listaFiltrada = filtrarFecha(listaFotos,d);
            showFotos(listaFiltrada);
        } else if ((cbbPersonas.getValue()!=null)&&!(rbTodos.isSelected()) && !(rbFecha.isSelected()) && !(rbLugar.isSelected())) {
            limpiarGaleria();
            listaFiltrada = filtrarPersona(listaFotos,personasFiltro);
            showFotos(listaFiltrada);
        }else if((rbTodos.isSelected()) && !(rbFecha.isSelected()) && !(rbLugar.isSelected())){
            limpiarGaleria();
            showFotos(listaFotos);
        } else{
            LocalDate fecha = dateCalendario.getValue();
            String lugar = txtCriterio.getText();
            ArrayListG07<Persona> personas = personasFiltro;
            
            // En esta parte se debe filtrar poco a poco
                listaFiltrada = filtrarLugar(listaFotos,lugar);
                listaFiltrada = filtrarFecha(listaFiltrada,fecha);
                listaFiltrada = filtrarPersona(listaFiltrada,personas);
                showFotos(listaFiltrada);
            
        
        }
    }

    public ArrayListG07<Foto> filtrarLugar(ArrayListG07<Foto> listaFotos, String lugar) {
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();

        if (listaFotos.isEmpty()) {
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println("No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        } else {
            for (int i = 0; i < listaFotos.size(); i++) {
                if (lugar.toLowerCase().equals(listaFotos.get(i).getLugar().toLowerCase())) {
                    listaFiltrada.addLast(listaFotos.get(i));
                }
            }

        }

        return listaFiltrada;

    }

    public ArrayListG07<Foto> filtrarFecha(ArrayListG07<Foto> listaFotos,LocalDate fecha) {
        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();

        if (listaFotos.isEmpty()) {
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println("No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        } else {
            for (int i = 0; i < listaFotos.size(); i++) {
                if (fecha.equals(listaFotos.get(i).getFecha())) {
                    listaFiltrada.addLast(listaFotos.get(i));
                }
            }

        }

        return listaFiltrada;

    }

    public ArrayListG07<Foto> filtrarPersona(ArrayListG07<Foto> listaFotos, ArrayListG07<Persona> persona) {

        ArrayListG07<Foto> listaFiltrada = new ArrayListG07<Foto>();
        boolean bandera=true;

        if (listaFotos.isEmpty()) {
            //en esta parte se le debe poner una ventana de dialogo
            System.out.println("No existen fotos para buscar, esta vacia");
            return listaFiltrada = null;
        } else {

            for (int j = 0; j < listaFotos.size(); j++) {
                for (int i = 0; i < persona.size(); i++) {
                    if (!(comparePersona(persona.get(i), listaFotos.get(j)))) {
                        bandera=false;
                    }
                }
                if(bandera){
                    listaFiltrada.addLast(listaFotos.get(j));
                }
            }

        }
        return listaFiltrada;

    }

    public boolean comparePersona(Persona persona, Foto f2) {
        ArrayListG07<Persona> p = f2.getPersonas();
        for (int i = 0; i < f2.numeroPersonas(); i++) {
            if (persona.getNombre().toLowerCase().equals(p.get(i).getNombre().toLowerCase()) && persona.getApellido().toLowerCase().equals(p.get(i).getApellido().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void limpiarGaleria() {
        galeria.getChildren().clear();
    }

    public void showFotos(ArrayListG07<Foto> listaFiltrada) {

        for (int i = 0; i < listaFiltrada.size(); i++) {
            String url = listaFiltrada.get(i).getImage().getUrl();
            Image image = new Image(url, 100, 100, true, true);
            System.out.println("Se creo miniatura: " + listaFiltrada.get(i).getNombre());
            VBox vboxfoto = new VBox();
            ImageView imgview = null;
            try {
                imgview = new ImageView(image);
            } catch (NullPointerException ex) {
                imgview = new ImageView();
            }

            vboxfoto.getChildren().add(imgview);
            galeria.getChildren().add(vboxfoto);
        }
    }

    public void cargarComboPersonas() {
        
        ArrayListG07<Persona> lista=listaPersonasExistentes();
        
        for(Persona persona:lista){
            cbbPersonas.getItems().addAll(persona);
        }

    }

    public ArrayListG07<Persona> listaPersonasExistentes() {
        
        
        ArrayListG07<Persona> listaPersonasTDA= Persona.lecturaPersonas();
        

        return listaPersonasTDA;
    }
        
    @FXML
    public void comboboxEvents(ActionEvent e) {
        Object evt = e.getSource();
        Persona persona = cbbPersonas.getSelectionModel().getSelectedItem();
        
        if (evt.equals(cbbPersonas)) {
            Button nombre = new Button();
            nombre.setText(persona.getNombre()+" "+persona.getApellido());
            
            try {
                if (personasFiltro == null) {
                    hboxPersonas.getChildren().add(nombre);
                    personasFiltro.addFirst(persona);
                } else {
                    if (personasFiltro.contains(persona)) {
                        System.out.println("YA EXISTE");
                    } else {
                        hboxPersonas.getChildren().add(nombre);
                        personasFiltro.addLast(persona);
                    }
                }
            } catch (NullPointerException f) {
                System.out.println("NullPointerException thrown!");
            }

        }

    }
    
    
    @FXML
    public void limpiarPersonasFiltro() {
        hboxPersonas.getChildren().clear();
        personasFiltro = null;
    }
    


}
