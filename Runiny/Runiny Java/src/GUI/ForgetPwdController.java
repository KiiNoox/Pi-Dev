/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author ACER EXTENSA 15
 */
public class ForgetPwdController implements Initializable {
    String uss;
    String pass;

    @FXML
    private HBox tfuserr;
    @FXML
    private TextField tfemail;
    @FXML
    private Button searchpwd;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfpassw;
    @FXML
    private Label errorLb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        // TODO
    }    
       @FXML
    private void searchpwd(ActionEvent event) throws IOException{
      try {
            Statement st = new MyDB().getConnection().createStatement();
            tfid.setText("");
            tfpassw.setText("");
            
            
            String Login = tfemail.getText();
            if(Login.isEmpty()){
                errorLb.setText("Please insert Login");
            }
            else {
                String sql = "select Nom, Id_user, Login, Password from user where Login=?";
                PreparedStatement pst = new MyDB().getConnection().prepareStatement(sql);
                pst.setString(1,Login);
                
                ResultSet rs = pst.executeQuery();
                if(rs.next()){
                    tfnom.setText(rs.getString(1));
                    tfid.setText(rs.getString(2));
                    uss = rs.getString(3);
                    pass = rs.getString(4);
                    errorMsg.setText("");
                    
                    pst.close();
                    rs.close();
                }
                else {
                    errorLb.setText("Error: Login is incorrect");
                }
                
                
            }
            
        } catch (Exception ex) {
            System.out.println("something wrong" + ex);
        } 

        
    }
    @FXML
    private void generer(ActionEvent event) throws IOException {
          String Login = tfemail.getText();
         if(Login.equals(tfemail.getText())){
            tfpassw.setText(pass);
        }
        
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
                Parent fxml=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }

    private static class errorMsg {

        private static void setText(String please_insert_username) {
           // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public errorMsg() {
        }
    }

 
    
}
