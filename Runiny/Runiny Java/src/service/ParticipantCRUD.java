/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Participant;
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
public class ParticipantCRUD {

    public ParticipantCRUD() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        public void ajouterParticipant(Participant p) {
         
        try {
            String requete2 = "INSERT INTO Participant (id_parti,id_user,idEvent)"
                    + "VALUES(?,?,?)";
            PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete2);
                    pst.setInt(1, p.getId_parti());
                    pst.setInt(2, p.getId_user());
                    pst.setInt(3, p.getIdEvent());
                    pst.executeUpdate();
                    System.out.println("votre participant est ajouter");
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());     
                    }

    
}
      public List<Participant> afficherParticipant(){
        List<Participant> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM participant";
            Statement st = new MyDB().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            while (rs.next()){
                Participant p = new Participant() ;
                p.setId_parti(rs.getInt(1));
                p.setIdEvent(rs.getInt("idEvent"));
                p.setId_user(rs.getInt("id_user"));
               
                myList.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        
            return myList;
}
       public void supprimerParticipant(){
             try {
            String requete4 = "DELETE FROM participant WHERE Id_parti='12'";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete4);
            System.out.println("participant supprimer avec succÃ©s");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());      
                    }
       }
 

}

