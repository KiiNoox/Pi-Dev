/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

//import static GUI.EventController.selectedItemm;
import entite.Evenement;
import entite.Participant;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ParticipantController implements Initializable {

    @FXML
    private ListView<String> tfliste;
    @FXML
    private ListView<Evenement> tfevent;
    @FXML
    private ListView<String> tfuser;
    @FXML
    private TextField nomev;
    @FXML
    private TextField nomus;
    @FXML
    private Button ajout;
    @FXML
    private TextField iduser;
    @FXML
    private TextField idEvent;
    
    static Evenement selectedItemm;
    //static User selectedItemm1;
    @FXML
    private Button idparticipant;
    @FXML
    private Button Pbutton;
    @FXML
    private Button evenb;
    @FXML
    private Button supp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDB ds = new MyDB();
        Connection cnx = ds.getConnection();
       // String req1 ="SELECT * from participant";
        String req = "SELECT * from events";
        try {
            //Statement st1 = cnx.createStatement();
            //ResultSet rs1 = st1.executeQuery(req1);
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
 
                String Nom = rs.getString("nom");
                String Descri = rs.getString("descri");
               
                Evenement list = new Evenement();
                list.setIdEvent(rs.getInt("idEven"));
                list.setNom(Nom);
                list.setDescri(Descri);
                list.setDateEvent(rs.getDate("dateEven"));
                list.setPrix(rs.getInt("prix"));
                tfevent.getItems().add(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
     
       String request = "SELECT * from user";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(request);
            while (rs.next()) {

                String Nom = rs.getString("Nom");
                String Prenom = rs.getString("Prenom");
                String Role = rs.getString("Role");
                String list = "   " + Nom + "           " + Prenom + "      " + Role + "      ";
                tfuser.getItems().add(list);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        String requ = "SELECT * from participant";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requ);
            while (rs.next()) {
               

               String id_parti = rs.getString("id_parti");
               String idEvent = rs.getString("idEvent");
               String id_user = rs.getString("id_user");
               String list = "      " + id_parti + "                          " + idEvent + "                        " + id_parti + "            ";
           
                
                tfliste.getItems().add(list);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }    
    }    

    @FXML
    private void Index1(MouseEvent event) {
   
        selectedItemm = tfevent.getSelectionModel().getSelectedItem();
        nomev.setText(String.valueOf(selectedItemm.getNom()));
        idEvent.setText(String.valueOf(selectedItemm.getIdEvent()));
       // selectedItemm1 = tfuser.getSelectionModel().getSelectedItem();
        //nomus.setText(String.valueOf(selectedItemm1.getRole()));
    }

    @FXML
    private void ajouterp(ActionEvent event) {
            selectedItemm = tfevent.getSelectionModel().getSelectedItem();
       // idEvent.setText(String.valueOf(selectedItemm.getidEven()));
       nomev.setText(String.valueOf(selectedItemm.getNom()));
    }

//    @FXML
//    private void index3(MouseEvent event) {
      //selectedItemm1 = tfevent.getSelectionModel().getSelectedItem();
      // iduser.setText(String.valueOf(selectedItemm.getidEven()));
       
//         
//    }

    @FXML
    private void index3(MouseEvent event) {
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
    private void event(ActionEvent event) throws IOException {
         Parent fxml=FXMLLoader.load(getClass().getResource("Event.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    @FXML
    private void supprimer(ActionEvent event) {
    }
    
}
