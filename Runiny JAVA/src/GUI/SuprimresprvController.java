/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import jfx.messagebox.MessageBox;

/**
 * FXML Controller class
 *
 * @author mayro
 */
public class SuprimresprvController implements Initializable {

    @FXML
    private Button res;
    @FXML
    private ComboBox<String> ress;
    @FXML
    private Button retour;
    @FXML
    private Button supp;
    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";
    @FXML
    private Button snc;
    @FXML
    private Button form;
    @FXML
    private Button paym;
    @FXML
    private Button ev;
    @FXML
    private Button abo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        get_reservations();
    }    
    @FXML
    private void reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();}
    @FXML
    private void ret(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("myreservs.fxml"));
                Scene scene8=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene8);
                window.show();}
    @FXML
    private void get_reservations() {
        ress.getItems().clear();
        Connection conn = null;
        Statement st = null;
        String getres = "SELECT *  FROM reservation";

        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);
            ResultSet rs1 = conn.createStatement().executeQuery(getres);
            while (rs1.next()) {
                String dateres = rs1.getString(1);
                String dater = rs1.getString("date");
                ress.getItems().addAll(dater);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void supprimerres(ActionEvent event)throws IOException{
        String resdate = ress.getValue();
        try
    {
        conn = (Connection) DriverManager.getConnection(url, login, password);
        String rmvres="Delete from reservation where date='"+resdate+"'";
        PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(rmvres);
        preparedStmt.execute();
        //dialog box
        Window primaryStage = null;
        MessageBox.show(primaryStage,
         "Reservation supprimée",
         "Information dialog",
         MessageBox.OK);
        //actualisation de la scene
        Parent fxml1;
        fxml1 = FXMLLoader.load(getClass().getResource("suprimresprv.fxml"));
                Scene scene8=new Scene(fxml1);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene8);
                window.show();
        }
    catch (Exception e)
    {
      System.err.println(e.getMessage());
    }   
    }

    @FXML
    private void seance(ActionEvent event)throws IOException {
        Parent fxml3=FXMLLoader.load(getClass().getResource("seanceshow.fxml"));
                Scene scene6=new Scene(fxml3);
                Stage window2 =(Stage)((Node)event.getSource()).getScene().getWindow();
                window2.setScene(scene6);
                window2.show();
    }
}
