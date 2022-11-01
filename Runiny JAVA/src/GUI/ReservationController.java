/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;
import entitie.Reserv;
import entitie.Seance;
import java.io.File;
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
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import jfx.messagebox.MessageBox;
import service.ReservCrud;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author mayro
 */
public class ReservationController implements Initializable {

    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";
       
    @FXML
    private DatePicker date;
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
    @FXML
    private ComboBox<String> coach;
    @FXML
    private ComboBox<String> sll;
    @FXML
    private Button resSPRi;
    @FXML
    private Button mod;
    @FXML
    private Button supp;
    @FXML
    private TableView<Reserv> tl;
    @FXML
    private TableColumn<Reserv, Date> ddt;
    @FXML
    private Spinner<Integer> HH;
    @FXML
    private Spinner<Integer> MM;
    Integer curheure;
    Integer curmin;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showres();
        setspinners();
        remplir_coachcomb();
        remplir_salle();
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
    private void reservation(ActionEvent event) throws IOException {
        Parent fxml;
        fxml = FXMLLoader.load(getClass().getResource("reservation.fxml"));
                Scene scene3=new Scene(fxml);
                Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(scene3);
                window.show();
    }
    private void RSPRIV(ActionEvent event) throws IOException {
        Parent fxml1=FXMLLoader.load(getClass().getResource("ReservSPRIV.fxml"));
                Scene scene4=new Scene(fxml1);
                Stage window1 =(Stage)((Node)event.getSource()).getScene().getWindow();
                window1.setScene(scene4);
                window1.show();
    }
    private void reservEvent(ActionEvent event) throws IOException {
        Parent fxml2=FXMLLoader.load(getClass().getResource("reservEvent.fxml"));
                Scene scene5=new Scene(fxml2);
                Stage window2 =(Stage)((Node)event.getSource()).getScene().getWindow();
                window2.setScene(scene5);
                window2.show();
 }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        Parent fxml3=FXMLLoader.load(getClass().getResource("home.fxml"));
                Scene scene6=new Scene(fxml3);
                Stage window2 =(Stage)((Node)event.getSource()).getScene().getWindow();
                window2.setScene(scene6);
                window2.show();
    }
    @FXML
    public void OnClickedPrint(ActionEvent event) throws IOException{
         try {

            OutputStream file = new FileOutputStream(new File("D:reservation.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            document.add(new Paragraph("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Mes Reservations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n\n\n\n\n\n"));

            document.add(new Paragraph(" ___________________________________________________________________________\n"));
            document.add(new Paragraph(" Date des Reservations :"));
            String dater=null;
            String cnom=null;
            String cprm=null;
            String snom=null;
            Connection conn = null;
        Statement st = null;
        String getres = "SELECT r.date, u.nom ,u.prenom,s.nom FROM reservation AS r "
                + "JOIN user AS u ON u.id_user=r.id_coach JOIN salle AS s ON s.id_salle=r.id_salle ORDER BY r.date";
        
            conn = (Connection) DriverManager.getConnection(url, login, password);
            ResultSet rs1 = conn.createStatement().executeQuery(getres);
            document.add(new Paragraph("Coach              "+"         "+"Date"+"         "+"Salle"+"\n___________________________________________________________________\n"));
            while (rs1.next()) {
                String dateres = rs1.getString(1);
                dater = rs1.getString("r.date");
                cnom = rs1.getString("u.nom");
                cprm = rs1.getString("u.prenom");
                snom = rs1.getString("s.nom");
                if (dater==null) {
                    document.add(new Paragraph("aucune reservation \n"));
                } else {
                    document.add(new Paragraph(cprm+" "+cnom+"         "+dater+"         "+snom+"\n"));
                }
{
            }
            }
            document.add(new Paragraph(" ___________________________________________________________________________"));

            document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Mes Reservations ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
            document.close();
            file.close();

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println(e.getMessage());
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
        rsp.ajouter1(sp);
        Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Reservation ajoutée",
         "Succée",
         MessageBox.OK);
         showres();
    }

    public ObservableList<Reserv> getresList(){
        ObservableList<Reserv> resList = FXCollections.observableArrayList();
        MyDB dd = new MyDB();
        java.sql.Connection conn =dd.getConnection();
        String query="SELECT * FROM reservation";
        Statement st;
        ResultSet rs;
        try{
            st= conn.createStatement();
            rs = st.executeQuery(query);
            Reserv res;
        while(rs.next()){
            res = new Reserv(rs.getInt("id_reser"),rs.getInt("id_coach"),rs.getInt("id_salle"),rs.getString("date"));
            System.out.println(res);
            resList.add(res);
        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resList;
        }
    private void showres(ActionEvent event) {
        ObservableList<Reserv> list = getresList();
        ddt.setCellValueFactory(new PropertyValueFactory<Reserv,Date>("date"));
        tl.setItems(list);
    }
    private void showres() {
       ObservableList<Reserv> list = getresList();
        ddt.setCellValueFactory(new PropertyValueFactory<Reserv,Date>("date"));
        tl.setItems(list);
        System.out.println(tl);
    }

    @FXML
    private void mouseclick(MouseEvent event) {
        Reserv ress = tl.getSelectionModel().getSelectedItem();
        int idc=ress.getId_coach();
        System.out.println(idc);
        int ids=ress.getId_salle();
        System.out.println(ids);
        String dat=ress.getDate();
        System.out.println(dat);
        
        Connection conn = null;
        Statement st = null;
        getcoach(idc);
        getsalle(ids);
        /////
        String[]tmp,tmm;
        String hih, mim,time;
        tmp=dat.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate tt=LocalDate.parse(tmp[0].toString(),formatter);
        formatter.format(tt);
        System.out.println(formatter.format(tt));
        date.setValue(tt);
        
        
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
    private void edit(ActionEvent event) {
        Reserv snce = tl.getSelectionModel().getSelectedItem();
         int id=0;
         id=snce.getId_reser();
         
         
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
        sp.setId_reser(id);
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
         
        
         System.out.print(dt);
         
         rsp.modifier1(sp);
         if (id!=0){
         Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Reservation modifiée",
         "Succée",
         MessageBox.OK);}
         showres();
         remplir_coachcomb();
         remplir_salle();
         date.getEditor().clear();
         setspinners();
    }

    @FXML
    private void delete(ActionEvent event) {
        Reserv rese = tl.getSelectionModel().getSelectedItem();
         int id=0;
         id=rese.getId_reser();
         Reserv e=new Reserv();
         ReservCrud ed =new ReservCrud();
         e.setId_reser(id);
         ed.supprimer(e);
         if (id!=0){
         Window primaryStage = null;
         MessageBox.show(primaryStage,
         "Reservation supprimée",
         "Succée",
         MessageBox.OK);}
         showres();
         date.getEditor().clear();
         remplir_coachcomb();
         remplir_salle();
         setspinners();
    }
    private void getsalle(int ids){
        String Sname = "SELECT * FROM salle where id_salle="+ ids;

        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs2 = conn.createStatement().executeQuery(Sname);
            while (rs2.next()) {
                String nsalle = rs2.getString(1);
                //salaff.setText(String.valueOf(rs2.getString("nom")));
                sll.setValue(String.valueOf(rs2.getString("nom")));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        }
    
    private void getcoach(int idc){
        String fulname = "SELECT *  FROM user where id_user="+idc;

        try {
            conn = (Connection) DriverManager.getConnection(url, login, password);

            ResultSet rs1 = conn.createStatement().executeQuery(fulname);
            while (rs1.next()) {
                String finame = rs1.getString(1);
                String laname = rs1.getString(1);
                String funame = rs1.getString("Prenom") + " " + rs1.getString("Nom");
                //chaff.setText(String.valueOf(funame));
                coach.setValue(String.valueOf(funame));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}