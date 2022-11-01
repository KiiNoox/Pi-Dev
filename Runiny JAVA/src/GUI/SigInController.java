/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entitie.UserSession;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ACER EXTENSA 15
 */
public class SigInController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private PasswordField tfpwd;
    @FXML
    private Button btnlogin;
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
    private void Connecter(ActionEvent event) throws SQLException {
       Statement st = new MyDB().getConnection().createStatement();
        try {
            String email = tfemail.getText();
            String password = tfpwd.getText();
            
            if(email.isEmpty() || password.isEmpty()){
               errorlab.setText("Please insert Login and password");
            }
            else{
                  String requete = ("select * from user where email=?"
                    + " and password=?" + " and role='Client'");
                PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete);
                pst.setString(1,tfemail.getText());
                pst.setString(2, tfpwd.getText());
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()){
                Parent fxml=FXMLLoader.load(getClass().getResource("Home.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
                }
                else{
                    errorlab.setText(" Login or password is wrong ");
                }
            }
                if(email.isEmpty() || password.isEmpty()){
               errorlab.setText("Please insert Login and password");
            }
            else{
                  String requete = ("select * from user where email=?"
                    + " and password=?" + " and role='Admin'");
                PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete);
                pst.setString(1,tfemail.getText());
                pst.setString(2, tfpwd.getText());
                ResultSet rs = pst.executeQuery();
                 
                if(rs.next()){
                Parent fxml=FXMLLoader.load(getClass().getResource("GestionUtilis.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
                }
                else{
                    errorlab.setText(" email or password is wrong ");
                    
                }
            }
            }
        catch(Exception ex){
            System.out.println("error" + ex.toString());
        }
}

    @FXML
    private void open_pwd(ActionEvent event) throws IOException {
                Parent fxml=FXMLLoader.load(getClass().getResource("ForgetPwd.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    
       
    }

}
  