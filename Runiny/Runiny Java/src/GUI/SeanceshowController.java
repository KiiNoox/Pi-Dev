/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import service.SeanceCrud;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import entitie.Seance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import jfx.messagebox.MessageBox;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author mayro
 */
public class SeanceshowController implements Initializable {

    Integer curheure;
    Integer curmin;
    
    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";
    @FXML
    private Button snc;
    @FXML
    private TableView<Seance> tbl;
    @FXML
    private TableColumn<Seance, String> colsnc;
    @FXML
    private TableColumn<Seance, Date> coldt;
    @FXML
    private Button ref;
    @FXML
    private TextField searchBox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showsnc();
    }    
    
    @FXML
    private void reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/reservation.fxml"));
        Scene scene3 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene3);
        window.show();
    }
    @FXML
    private void snc(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/seanceshow.fxml"));
        Scene scene4 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    private void addsnc(ActionEvent event) throws IOException {
        Parent fxml1;
        fxml1 = FXMLLoader.load(getClass().getResource("GUI/addsnc.fxml"));
        Scene scene5 = new Scene(fxml1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene5);
        window.show();
    }

    private void deletesnc(ActionEvent event) throws IOException {
        Parent fxml1;
        fxml1 = FXMLLoader.load(getClass().getResource("GUI/deletesnc.fxml"));
        Scene scene6 = new Scene(fxml1);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene6);
        window.show();
    }

    private void retour(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getClassLoader().getResource("GUI/home.fxml"));
        Scene scene4 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    @FXML
    private void OnClickedPrint(ActionEvent event) throws DocumentException, FileNotFoundException, SQLException, IOException {
            String url = "jdbc:mysql://localhost:3306/bdr";
            String login = "root";
            String password = "";
            OutputStream file = new FileOutputStream(new File("D:Seances.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Mes Reservations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n\n\n\n\n"));

            document.add(new Paragraph(" ___________________________________________________________________________\n"));
            document.add(new Paragraph(" Date des Seances :"));
            String dater=null;
            String type=null;
            Connection conn = null;
            Statement st = null;
            String getres = "SELECT * FROM seance ORDER BY date";
        
            conn = (Connection) DriverManager.getConnection(url, login, password);
            ResultSet rs1 = conn.createStatement().executeQuery(getres);
            document.add(new Paragraph("DATE"+"       "+"SEANCE"+"\n"));
            while (rs1.next()) {
                String dateres = rs1.getString(1);
                dater = rs1.getString("date");
                type = rs1.getString("type_seance");
                if (dater==null) {
                    document.add(new Paragraph("aucune seance \n"));
                } else {
                    document.add(new Paragraph(dater+"       "+type+"\n___________________________________________\n"));
                }
           
            }

            document.add(new Paragraph(" ___________________________________________________________________________"));

            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Mes Seances ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
            document.close();
            file.close();

        }

    @FXML
    private void snc(MouseEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("GUI/seanceshow.fxml"));
        Scene scene4 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    public ObservableList<Seance> getsncList(){
        ObservableList<Seance> sncList = FXCollections.observableArrayList();
        MyDB dd = new MyDB();
        java.sql.Connection conn =dd.getConnection();
        String query="SELECT * FROM seance";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs = st.executeQuery(query);
            Seance snc;
        while(rs.next()){
            snc = new Seance(rs.getInt("id_seance"), rs.getString("date"), rs.getString("type_seance"));
            sncList.add(snc);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return sncList;
        }
    @FXML
    private void showsnc(ActionEvent event) {
        ObservableList<Seance> list = getsncList();
        colsnc.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
        coldt.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));
        
        tbl.setItems(list);
        FilteredList<Seance> filteredData = new FilteredList<>(FXCollections.observableList(list));
        tbl.setItems(filteredData);
        searchBox.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
    }
    private void showsnc() {
        ObservableList<Seance> list = getsncList();
        colsnc.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
        coldt.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));
        
        tbl.setItems(list);
        FilteredList<Seance> filteredData = new FilteredList<>(FXCollections.observableList(list));
        tbl.setItems(filteredData);
        searchBox.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
    }
    /////// recherche
    private boolean searchFindsOrder(Seance snc, String searchText){
    return (snc.getType_seance().toLowerCase().contains(searchText.toLowerCase())) ||
            (snc.getDate().toLowerCase().contains(searchText.toLowerCase())) ||
            Integer.valueOf(snc.getId_seance()).toString().equals(searchText.toLowerCase());
}
    private ObservableList<Seance> filterList(List<Seance> list, String searchText){
    List<Seance> filteredList = new ArrayList<>();
    for (Seance seancer : list){
        if(searchFindsOrder(seancer, searchText)) filteredList.add(seancer);
    }
    return FXCollections.observableList(filteredList);
}
    private Predicate<Seance> createPredicate(String searchText){
    return seance -> {
        if (searchText == null || searchText.isEmpty()) return true;
        return searchFindsOrder(seance, searchText);
    };
}
    }
    

