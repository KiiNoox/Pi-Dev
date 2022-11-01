/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entitie.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import service.UserCRUD;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ACER EXTENSA 15
 */
public class GestionUtilisController implements Initializable {

    @FXML
    private ListView<String> tflist;
    @FXML
    private ToggleGroup Rolee;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfadress;
    @FXML
    private TextField tfdate;
    @FXML
    private TextField tflog;
    @FXML
    private TextField tfpwd;
    @FXML
    private Label fRole;
    @FXML
    private RadioButton coach;
    @FXML
    private RadioButton admin;
    @FXML
    private RadioButton client;
    @FXML
    private Button idparticipant;
    @FXML
    private Button Pbutton;
    @FXML
    private Button evenb;

    /**
     * Initializes the controller class.
     */
   
      @FXML
    private void add(ActionEvent event) {
        String Nom = tfnom.getText();
        String Prenom = tfprenom.getText();
        String Adress = tfadress.getText();
        String Date_nais = tfdate.getText();
        String role=fRole.getText();
        String email=tflog.getText();
        String password=tfpwd.getText();
        User u = new User();
        u.setNom(Nom);
        u.setPrenom(Prenom);
        u.setAdress(Adress);
        u.setDate_nais(Date_nais);
        UserCRUD ud = new UserCRUD();
        ud.ajouterUser2(u);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDB ds = new MyDB();
        Connection cnx = ds.getConnection();
        String req = "SELECT * from user";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String Nom = rs.getString("Nom");
                String Prenom = rs.getString("Prenom");
                String Adress = rs.getString("Adress");
                String role = rs.getString("role");
                String email = rs.getString("email");
                String list = Nom + "  /  " + Prenom + "  /  " + Adress + "  /  " + role + "  /  " + email;
                tflist.getItems().add(list);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());;
        }
    }

    @FXML
    private void delete(ActionEvent event) {
        String requete = "DELETE FROM user WHERE Nom=?";
        int Nom = tflist.getSelectionModel().getSelectedIndex();
        tflist.getItems().remove(Nom);
    }

     @FXML
    private void getrole(ActionEvent event) {
           if (client.isSelected()) {
            fRole.setText(client.getText());
        } else if (admin.isSelected()) {
            fRole.setText(admin.getText());
        } else if (coach.isSelected()) {
           fRole.setText(coach.getText());
        
        String role = fRole.getText();
    }

  

}

    @FXML
    private void modifier(ActionEvent event) {
    }


     @FXML
    private void gotops(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("PayementsHistory.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    @FXML
    private void pbutclick(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("Participant.fxml"));
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
    private void reserva(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("myreservs.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    
    }

    @FXML
    private void snc(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("seanceshowr.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    
    }
}
