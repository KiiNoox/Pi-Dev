/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entitie.Seance;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author mayro
 */
public class SeanceCrud implements ISeance<Seance> {
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

        Connection conn;
        PreparedStatement stee;
    
    public SeanceCrud() {
        conn = MyDB.getInstance().getConnection();
    }
    public void ajouter(Seance r){
        try {
            String requete = "INSERT INTO seance (id_seance,date,type_seance)"
                    + "VALUES(?,?,?)";
            PreparedStatement st = new MyDB().getConnection().prepareStatement(requete);
            
            st.setInt(1, r.getId_seance());
            st.setString(2, r.getDate());
            st.setString(3, r.getType_seance());
            st.executeUpdate();
            //
            //st.executeUpdate(requete);
            System.out.println("Seance ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public boolean supprimer(Seance r){
        boolean suppression = true;
        try {
            String requete4 = "DELETE FROM seance WHERE id_seance='"+r.getId_seance()+ "'";
            Statement st = conn.createStatement();
            st.executeUpdate(requete4);
            System.out.println("Seance supprimé avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
            suppression = false;
        }
        return suppression;
    }

    
    public boolean modifier(Seance r) {
        boolean update= true;
        String query = "UPDATE seance SET date='"+r.getDate()+"',"
                + " type_seance ='"+r.getType_seance()+"' "
                + "WHERE id_seance ='"+r.getId_seance()+"'"; 
        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate(query);
            System.out.println("la seance a été bien modifiée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            update = false;
        }
        return update;
    }
   
    public List<Seance> readAll(){
        List<Seance> myList = new ArrayList<>();
        try {
            String requete3 = "SELECT * FROM seance ";
            Statement st = new MyDB().getConnection().createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Seance r = new Seance();
                r.setDate(rs.getString("date"));
                r.setType_seance(rs.getString("type_seance"));
                myList.add(r);
                System.out.println(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList;}
    
    
    public int getId(String valeur) throws SQLException{
        int id=0;
        ResultSet rs = null;
        String sql="SELECT id_seance from Seance where type_seance ='"+valeur+"'";
        Statement ste = conn.createStatement();
        rs=ste.executeQuery(sql);
        while(rs.next()){
            id = rs.getInt("id_seance");
        }
        return id ;
        
    }
        
}
