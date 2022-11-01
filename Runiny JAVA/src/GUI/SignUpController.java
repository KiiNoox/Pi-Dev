/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.EnvoyerEmail;
import entitie.User;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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
public class SignUpController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdress;
    @FXML
    private TextField tfDn;
    @FXML
    private Label tfRole;
    @FXML
    private TextField tfEmail;
    @FXML
    private PasswordField tfPwd;
    @FXML
    private Button btnajouter;
    @FXML
    private RadioButton rclient;
    @FXML
    private RadioButton radmin;
    @FXML
    private RadioButton rcoach;
    @FXML
    private ToggleGroup Rolee;
    @FXML
    private Label errorlab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void getrole(ActionEvent event) {
        if (rclient.isSelected()) {
            tfRole.setText(rclient.getText());
        } else if (radmin.isSelected()) {
            tfRole.setText(radmin.getText());
        } else if (rcoach.isSelected()) {
            tfRole.setText(rcoach.getText());
        }
        String role = tfRole.getText();
    }

    @FXML
    private void ajouterUser(ActionEvent event) throws SQLException, IOException {
        String Nom = tfNom.getText();
        String Prenom = tfPrenom.getText();
        String Adress = tfAdress.getText();
        String Date_nais = tfDn.getText();
        String role = tfRole.getText();
        String email = tfEmail.getText();
        String password = tfPwd.getText();
        if (email.isEmpty() || password.isEmpty() || Nom.isEmpty() || Prenom.isEmpty() || Adress.isEmpty() || Date_nais.isEmpty() || role.isEmpty()) {
            errorlab.setText("Please complete all the fills");
        } else if (email.charAt(0) != '@' && email.contains("@") && email.endsWith(".com") || email.endsWith(".tn") || email.endsWith(".fr")) {
            errorlab.setText("Please enter a valide email");
            if (password.length() < 8) {
                errorlab.setText("Password is too weak, please choose atleast 8 characters");
            } else {
                String requete = ("select * from user where email=?");
                PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete);
                pst.setString(1, email);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    errorlab.setText("Username already taken, please try another username");
                }
                User u = new User(Nom, Prenom, Adress, email, password, role, Date_nais);
                UserCRUD ud = new UserCRUD();
                ud.ajouterUser2(u);
                EnvoyerEmail test = new EnvoyerEmail();
                test.envoyer(email);
            }
            Parent fxml = FXMLLoader.load(getClass().getResource("SigIn.fxml"));
            Scene scene3 = new Scene(fxml);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene3);
            window.show();
        }

    }
}
