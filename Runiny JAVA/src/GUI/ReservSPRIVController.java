/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import service.ReservCrud;
import com.mysql.jdbc.Connection;
import entitie.Reserv;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mayro
 */
public class ReservSPRIVController implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private Spinner<Integer> heure;
    @FXML
    private Spinner<Integer> minutes;
    @FXML
    private ComboBox<String> coach;
    Integer curheure;
    Integer curmin;
    final ObservableList options = FXCollections.observableArrayList();
    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";

    @FXML
    private ComboBox<String> sll;
    @FXML
    private Button mrs;
    @FXML
    private Button resSPRi;
    @FXML
    private Button ret;
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

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SpinnerValueFactory<Integer> valueFactoryh = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 18);
        valueFactoryh.setValue(9);
        heure.setValueFactory(valueFactoryh);
        curheure = heure.getValue();
        SpinnerValueFactory<Integer> valueFactorym = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59);
        valueFactoryh.setValue(00);
        minutes.setValueFactory(valueFactorym);
        curmin = minutes.getValue();
        heure.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                curheure = heure.getValue();
                curmin = minutes.getValue();
            }
        });

        remplir_coachcomb();
        remplir_salle();

    }

    @FXML
    private void reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    @FXML
    private void remplir_coachcomb() {
        coach.getItems().clear();
        Connection conn = null;
        Statement st = null;
        String fulname = "SELECT *  FROM user where role='coach'";

        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs1 = conn.createStatement().executeQuery(fulname);
            while (rs1.next()) {
                String finame = rs1.getString(1);
                String laname = rs1.getString(1);
                String funame = rs1.getString("Prenom") + " " + rs1.getString("Nom");
                coach.getItems().addAll(funame);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void remplir_salle() {
        sll.getItems().clear();
        String Sname = "SELECT * FROM salle";

        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs2 = conn.createStatement().executeQuery(Sname);
            while (rs2.next()) {
                String nsalle = rs2.getString(1);
                sll.getItems().addAll(rs2.getString("nom"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void reSpriv() {
        String params = coach.getValue();
    String s1, s2, s3;
    s1 = params.substring(0, params.indexOf(" "));
    params = params.substring(params.indexOf(" ") + 1, params.length());
    s2 = params;
    System.out.println(s1);System.out.println(s2);
        String chc = "Select * from user where Prenom ='"+s1+"' && Nom ='" + s2 + "'";
        String idsa = "Select * from salle where nom='"+sll.getValue()+"'";
        String datpick = (date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        String dt = datpick +"/" + curheure +"-"+ curmin;
        System.out.print(dt);
        Reserv sp = new Reserv();
        //getting coach's id
        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs4 = conn.createStatement().executeQuery(chc);
            while (rs4.next()) {
                int numcoach = rs4.getInt(1);
                sp.setId_coach(rs4.getInt("id_user"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //getting salle's id
        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs3 = conn.createStatement().executeQuery(idsa);
            while (rs3.next()) {
                int numsalle = rs3.getInt(1);
                sp.setId_salle(rs3.getInt("id_salle"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
        
        sp.setDate(dt);
        
        ReservCrud rsp = new ReservCrud();
        rsp.ajouter(sp);
    }
    
    @FXML
    private void mres(ActionEvent event) throws IOException {
        Parent fxml2 = FXMLLoader.load(getClass().getResource("myreservs.fxml"));
        Scene scene5 = new Scene(fxml2);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
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

