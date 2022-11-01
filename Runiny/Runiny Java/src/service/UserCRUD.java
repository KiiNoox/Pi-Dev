/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entitie.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author ACER EXTENSA 15
 */
public class UserCRUD {
    public void ajouterUser(){
         try {
            String requete = "INSERT INTO User(nom,prenom,adress,role,date_nais,login,password)"
            + "VALUES('sami','abdelkarim','rue xxx','admin', '22/10/1995','sami1995','123456sam')";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete);
            System.out.println("user ajouter avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());      
                    }
    }
     public void ajouterUser2(User u){
        try {
            String requete2 = "INSERT INTO User(Nom,Prenom,Adress,role,Date_nais,email,password)"
                    + "VALUES(?,?,?,?,?,?,?)";
            PreparedStatement pst = new MyDB().getConnection().prepareStatement(requete2);
                    pst.setString(1, u.getNom());
                    pst.setString(2, u.getPrenom());
                    pst.setString(3, u.getAdress());
                    pst.setString(4, u.getRole());
                    pst.setString(5, u.getDate_nais());
                    pst.setString(6, u.getEmail());
                    pst.setString(7, u.getPassword());
                    pst.executeUpdate();
                    System.out.println("votre user est ajouter");
                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());     
                    }
    }
      public List<User> afficherUser(){
        List<User> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT *FROM User";
            Statement st = new MyDB().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete3); 
            while (rs.next()){
                User u = new User();
                u.setId_user(rs.getInt(1));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setAdress(rs.getString("Adress"));
                u.setDate_nais(rs.getString("Date_nais"));
                u.setRole(rs.getString("role"));
                u.setEmail(rs.getString("email"));
                myList.add(u);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        
            return myList;
    }
     public void supprimerUser(){
             try {
            String requete3 = "DELETE FROM user WHERE id=?";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete3);
            System.out.println("user supprimer avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());      
                    }
         }
      public void modifierUser(){
             try {
            String requete4 = "Update user SET login='sami188' Where id='6'";
            Statement st = new MyDB().getConnection().createStatement();
            st.executeUpdate(requete4);
            System.out.println("user modifier avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());      
                    }
         }
}
