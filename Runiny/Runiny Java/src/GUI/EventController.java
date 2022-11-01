/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entite.Evenement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.EvenementCRUD;
import utils.MyDB;


/**
 * FXML Controller class
 *
 * @author pc
 */
public class EventController implements Initializable {

    @FXML
    private ListView<Evenement> tflist;
    @FXML
    private TextField tfprix;
    @FXML
    private TextField tfdesc;
    @FXML
    private TextField tfnom ;
    @FXML
    private TextField ideven;
    @FXML
    private DatePicker tfdate;
    static Evenement selectedItemm;
    @FXML
    private Button idparticipant;
    @FXML
    private Button Pbutton;
    @FXML
    private Button evenb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDB ds = new MyDB();
        Connection cnx = ds.getConnection();
//       
         EvenementCRUD cs = new EvenementCRUD();
        tflist.getItems().addAll(cs.afficherEvenements());
    }    

   
    @FXML
    private void ajouter(ActionEvent event) {
        int prix = Integer.parseInt(tfprix.getText());
        String descri = tfdesc.getText();
        String nom = tfnom.getText();
        LocalDate dateEven = tfdate.getValue();
        Evenement e = new Evenement();
        e.setNom(nom);
        e.setDescri(descri);
        e.setDateEvent(new Date (2022,03,03));
        e.setPrix(prix);
        EvenementCRUD ed = new EvenementCRUD();
        ed.ajouterEvenement2(e);
       tflist.getItems().add(e);
    }

    @FXML
    private void modifier(ActionEvent event) {
         Evenement e = new Evenement();
         EvenementCRUD ed = new EvenementCRUD();
         e.setIdEvent(Integer.parseInt(ideven.getText()));
         e.setNom(tfnom.getText());
         e.setDateEvent(new Date(2022, 3, 30));
         e.setPrix(Integer.parseInt(tfprix.getText()));
         e.setDescri(tfdesc.getText());
         ed.modifierEvenement(e);
         tflist.getItems().clear();
         EvenementCRUD cs = new EvenementCRUD();
         tflist.getItems().addAll(cs.afficherEvenements());
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        int Nom=tflist.getSelectionModel().getSelectedIndex();
        tflist.getItems().remove(Nom);
    }

    @FXML
   private void index3(MouseEvent event) {
        selectedItemm = tflist.getSelectionModel().getSelectedItem();
        tfnom.setText(String.valueOf(selectedItemm.getNom()));
        tfdesc.setText(String.valueOf(selectedItemm.getDescri()));
        tfprix.setText(String.valueOf(selectedItemm.getPrix()));
        ideven.setText(String.valueOf(selectedItemm.getIdEvent()));
        
    }

  

        @FXML
    private void gotops(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("PayementsHistory.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    private void pbutclick(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("Participer.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    @FXML
    private void evenclick(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("Event.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    
    }

  
    