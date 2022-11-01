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
import service.SeanceCrud;
import com.mysql.jdbc.Connection;
import entitie.Seance;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Boolean.TRUE;
import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
public class AddSncController implements Initializable {

    @FXML
    private Button res;
    @FXML
    private Button ret;
    @FXML
    private Label sncadd;
    @FXML
    private TextField ts;
    @FXML
    private DatePicker date;
    @FXML
    private Spinner<Integer> HH;
    @FXML
    private Spinner<Integer> MM;
    @FXML
    private Button add;
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
    private Button editsnc;
    @FXML
    private Button deletesnc;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showsnc();
        setspinners();
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
    private void setspinners(){
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
        });
    }
    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("seanceshow.fxml"));
        Scene scene4 = new Scene(fxml);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene4);
        window.show();
    }

    @FXML
    private void add(ActionEvent event) throws SQLException {
        
        String datpick = (date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString();
        String dt = datpick +"/" + curheure +"-"+ curmin;
        System.out.print(dt);
        Seance sp = new Seance();
        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);
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
    private void seance(ActionEvent event)throws IOException {
        Parent fxml3=FXMLLoader.load(getClass().getResource("seanceshow.fxml"));
                Scene scene6=new Scene(fxml3);
                Stage window2 =(Stage)((Node)event.getSource()).getScene().getWindow();
                window2.setScene(scene6);
                window2.show();
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
    }
    private void showsnc() {
        ObservableList<Seance> list = getsncList();
        colsnc.setCellValueFactory(new PropertyValueFactory<Seance, String>("type_seance"));
        coldt.setCellValueFactory(new PropertyValueFactory<Seance, Date>("date"));
        
        tbl.setItems(list);
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
}
