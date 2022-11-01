/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entitie.Seance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import utils.MyDB;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static java.util.Collections.list;
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
import service.SeanceCrud;

/**
 * FXML Controller class
 *
 * @author mayro
 */
public class SeanceshowrController implements Initializable {

    @FXML
    private Button ref;
    private TextField tfsnc;
    private DatePicker dt;
    @FXML
    private Spinner<Integer> HH;
    @FXML
    private Spinner<Integer> MM;
    @FXML
    private TableView<Seance> tbl;
    @FXML
    private TableColumn<Seance, String> colsnc;
    @FXML
    private TableColumn<Seance, Date> coldt;
    @FXML
    private Button editsnc;
    @FXML
    private Button deletesnc;
    Integer curheure;
    Integer curmin;
    MyDB dd = new MyDB();
        Connection conn =dd.getConnection();
    
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";
    @FXML
    private TextField ts;
    @FXML
    private DatePicker date;
    @FXML
    private Button idparticipant;
    @FXML
    private Button Pbutton;
    @FXML
    private Button evenb;
    @FXML
    private Button add;
    @FXML
    private TextField searchBox;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setspinners();
        showsnc();
        
       
    }    
    public ObservableList<Seance> getsncList(){
        ObservableList<Seance> sncList = FXCollections.observableArrayList();
        MyDB dd = new MyDB();
        Connection conn =dd.getConnection();
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
    public void showsnc(){
    ObservableList<Seance> list = getsncList();
        colsnc.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
        coldt.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));
        
        tbl.setItems(list);
        FilteredList<Seance> filteredData = new FilteredList<>(FXCollections.observableList(list));
        tbl.setItems(filteredData);
        searchBox.textProperty().addListener((observable, oldValue, newValue) ->
        filteredData.setPredicate(createPredicate(newValue)));
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
            com.mysql.jdbc.Connection conn = null;
            Statement st = null;
            String getres = "SELECT * FROM seance ORDER BY date";
        
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, login, password);
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
    private void pbutclick(ActionEvent event) throws IOException {
        Parent fxml=FXMLLoader.load(getClass().getResource("Participant.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
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

    @FXML
    private void add(ActionEvent event) {
        String datpick = (date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        String dt = datpick +"/" + curheure +"-"+ curmin;
        System.out.print(dt);
        Seance sp = new Seance();
        try {
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, login, password);
        sp.setDate(dt);
        sp.setType_seance(ts.getText());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        String ss=ts.getText();
        SeanceCrud rsp = new SeanceCrud();
        rsp.ajouter(sp);
        
         Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Seance ajoutée",
         ss,
         MessageBox.OK);
         showsnc();
         ts.clear();
         date.getEditor().clear();
         setspinners();
    }

    @FXML
    private void mouseclick(MouseEvent event) {
        Seance snce = tbl.getSelectionModel().getSelectedItem();
        ts.setText(snce.getType_seance());
        
        String dat=snce.getDate();
        
        
        String[]tmp,tmm;
        String hih, mim,time;
        tmp=dat.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tt=LocalDate.parse(tmp[0].toString(),formatter);
        formatter.format(tt);
        System.out.println(formatter.format(tt));
        date.setValue(tt);
        
        tmp=dat.split(" ");
        time=tmp[1].toString();
        tmm=time.split(":");
        hih=tmm[0].toString();
        mim=tmm[1].toString();
        Integer hihint=Integer.parseInt(hih);
        SpinnerValueFactory<Integer> valueFactoryhh = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 18);
        valueFactoryhh.setValue(hihint);
        HH.setValueFactory(valueFactoryhh);
        Integer mimint=Integer.parseInt(mim);
        SpinnerValueFactory<Integer> valueFactorymm = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
        valueFactorymm.setValue(mimint);
        MM.setValueFactory(valueFactorymm);
    }

    @FXML
    private void EDIT(ActionEvent event) {
        Seance snce = tbl.getSelectionModel().getSelectedItem();
         int id=0;
         id=snce.getId_seance();
         Seance e = new Seance();
         SeanceCrud ed = new SeanceCrud();
         e.setId_seance(id);
         e.setType_seance(ts.getText());
         String datpick = (date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
         String dt = datpick +"/" + curheure +"-"+ curmin;
         System.out.print(dt);
         e.setDate(dt);
         ed.modifier(e);
         if (id!=0){
         Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Seance modifiée",
         ts.getText(),
         MessageBox.OK);}
         showsnc();
         ts.clear();
         date.getEditor().clear();
         setspinners();
    }

    @FXML
    private void DELETE(ActionEvent event) {
        Seance snce = tbl.getSelectionModel().getSelectedItem();
         int id=0;
         String ss=snce.getType_seance();
         id=snce.getId_seance();
         Seance e=new Seance();
         SeanceCrud ed =new SeanceCrud();
         e.setId_seance(id);
         ed.supprimer(e);
         if (id!=0){
         Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Seance supprimée",
         ss,
         MessageBox.OK);}
         showsnc();
         ts.clear();
         date.getEditor().clear();
         setspinners();
    }

    private void setspinners() {
 SpinnerValueFactory<Integer> valueFactoryh = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 18);
        valueFactoryh.setValue(9);
        HH.setValueFactory(valueFactoryh);
        curheure = HH.getValue();
        SpinnerValueFactory<Integer> valueFactorym = new SpinnerValueFactory.IntegerSpinnerValueFactory(00, 59);
        valueFactoryh.setValue(00);
        MM.setValueFactory(valueFactorym);
        curmin = MM.getValue();
        HH.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                curheure = HH.getValue();
                curmin = MM.getValue();
            }
        });    }

    //Search
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
