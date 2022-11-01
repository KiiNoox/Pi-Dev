/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import utils.MyDB;
import entities.payement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class PayementService {

    Connection cnx;
    PayementPdf pdf = new PayementPdf();

    public PayementService() {
        cnx = MyDB.getInstance().getConnection();

    }

    public void ajouterP(payement p) {

        try {
            String requete = "INSERT INTO payement(montant,date_pay,id_user,ab_id)"
                    + "VALUES(?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1, p.getMontant());
            pst.setString(2, p.getDate_pay());
            pst.setInt(3, 1);
            pst.setInt(4, 2);
            pst.executeUpdate();

            pdf.pdfGen(p);

            System.out.println("Payement enregistré !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public List<payement> afficherPs() {

        List<payement> p_list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM payement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                payement p = new payement();
                p.setId_pay(rs.getInt("id_pay"));
                p.setMontant(rs.getInt("montant"));
                p.setDate_pay(rs.getString("date_pay"));
                p_list.add(p);
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return p_list;
    }

    public ObservableList<payement> afficherPso() {

        ObservableList<payement> p_list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM payement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                payement p = new payement();
                p.setId_pay(rs.getInt("id_pay"));
                p.setMontant(rs.getInt("montant"));
                p.setDate_pay(rs.getString("date_pay"));
                p_list.add(p);
                PayementPdf ppdf = new PayementPdf();
                ppdf.pdfGen(p);
                System.out.println(p.toString());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return p_list;
    }

    public void supprimerPs(payement p) {

        try {
            String requete = "DELETE FROM payement WHERE"
                    + " id_pay=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, p.getId_pay());
            pst.executeUpdate();

            System.out.println("Payement supprimé avec succés !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    public void modifierPs(payement p, float montant, Date date) {

        try {
            String requete = " UPDATE payement SET montant=?, date_pay=?"
                    + "WHERE id_pay=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setFloat(1, montant);
            pst.setDate(2, date);
            pst.setInt(3, p.getId_pay());
            pst.executeUpdate();
            System.out.println("Payement modifié avec succés !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

}
