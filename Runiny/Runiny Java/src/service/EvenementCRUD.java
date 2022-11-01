/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
import entite.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author pc
 */
public class EvenementCRUD {
    
   
    private PreparedStatement pst;
    private ResultSet rs;
    private Connection cnx;

    public EvenementCRUD() {
         cnx=MyDB.getInstance().getConnection();
    }
    
    
    
        public void ajouterEvenement2(Evenement e) {
        String requete2 = "INSERT INTO events (prix,descri,nom,dateEven) VALUES(?,?,?,?)";

        try {
            PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete2);
            pst.setInt(1, e.getPrix());
            pst.setString(2, e.getDescri());
             pst.setString(3, e.getNom());
             pst.setDate(4, e.getDateEvent());
           
           
            pst.executeUpdate();
            System.out.println("votre evenement est ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      
}
          public List<Evenement> afficherEvenements() {
        List<Evenement> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM events";
            Statement st = new MyDB().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdEvent(rs.getInt("idEven"));
                e.setDateEvent(rs.getDate("dateEven"));
                e.setNom(rs.getString("nom"));
                e.setDescri("descri");
                e.setPrix(rs.getInt("prix"));
                myList.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;
    }
 public void modifierEvenement(Evenement t) {
        try {
            String requete5 = "UPDATE events SET nom='"+t.getNom()+"',"
                + " dateEven='"+t.getDateEvent()+"', prix='"+t.getPrix()+"', descri='"+t.getDescri()+"' "
                + "WHERE idEven ='"+t.getIdEvent()+"'";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete5);
            System.out.println("evenement modifier avec succès "+t.getIdEvent());
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
}
 }
    public void supprimerEvenement() {
        try {
            String requete4 = "DELETE FROM Events WHERE idEven='?'";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete4);
            System.out.println("Evenement supprimer avec succÃ©s");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

//   
   public boolean modifier(Evenement t) {
      
        boolean update= true;
        String query = "UPDATE events SET nom='"+t.getNom()+"',"
                + " dateEven='"+t.getDateEvent()+"', prix='"+t.getPrix()+"', descri='"+t.getDescri()+"' "
                + "WHERE idEven ='"+t.getIdEvent()+"'"; 
        try {
            Statement st =cnx.createStatement();
            st.executeUpdate(query);
            System.out.println("l'evenement a été bien modifier");
            
        } catch (SQLException ex) { 
            System.out.println(ex.getMessage());
        }
        return update;
    }
}