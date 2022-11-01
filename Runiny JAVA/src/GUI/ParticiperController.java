/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author pc
 */
public class ParticiperController implements Initializable {

    @FXML
    private ListView<String> tfliste;
    @FXML
    private Label msg;
    @FXML
    private Button Pbutton;
    @FXML
    private Button pbut;
    @FXML
    private Button idparti;

    /**
     * Initializes the controller class.
     */


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MyDB ds = new MyDB();
        Connection cnx = ds.getConnection();
        String req = "SELECT * from events";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String nom = rs.getString("nom");
                int Prix = rs.getInt("Prix");
                String descri = rs.getString("descri");
                Date dateEven = rs.getDate("dateEven");
                String list = nom + "  /  " + Prix + "  /  "  + descri + "  /  "  + dateEven ;
                tfliste.getItems().add(list);
            }
    }   catch (SQLException ex) {    
            Logger.getLogger(ParticiperController.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }    

    @FXML
    private void participer(ActionEvent event) {
        EnvoyerEmail test = new EnvoyerEmail();
    test.envoyer("samiabdlekrim98@gmail.com");
    msg.setText("Votre Participation à été effectuer avec succés, verfier ton email ");
        
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
        Parent fxml=FXMLLoader.load(getClass().getResource("Participer.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }
    
    
}
