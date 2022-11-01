package service;

import entitie.Reserv;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

public class ReservCrud implements IReserve<Reserv>{
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

        Connection conn;
        PreparedStatement stee;
    public ReservCrud(){
        conn = MyDB.getInstance().getConnection();
    }
    public void ajouter(Reserv r){
        try {
            String requete = "INSERT INTO reservation (id_reser,id_user,id_coach,id_salle,date)"
                    + "VALUES(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(requete);
            
            st.setInt(1, r.getId_reser());
            st.setInt(2, r.getId_user());
            st.setInt(3, r.getId_coach());
            st.setInt(4, r.getId_salle());
            st.setString(5, r.getDate());
            st.executeUpdate();
            System.out.println("Reservation ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    public void ajouter1(Reserv r){
        try {
            String requete = "INSERT INTO reservation (id_reser,id_user,id_coach,id_salle,date)"
                    + "VALUES(?,1,?,?,?)";
            PreparedStatement st = conn.prepareStatement(requete);
            
            st.setInt(1, r.getId_reser());
            
            st.setInt(2, r.getId_coach());
            st.setInt(3, r.getId_salle());
            st.setString(4, r.getDate());
            st.executeUpdate();
            System.out.println("Reservation ajoutée avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
        }
    }
    
    public boolean supprimer(Reserv r){
        boolean suppression = true;
        try {
            String requete4 = "DELETE FROM reservation WHERE id_reser='"+r.getId_reser()+ "'";
            Statement st = conn.createStatement();
            st.executeUpdate(requete4);
            System.out.println("Reservation supprimé avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
            suppression = false;
        }
        return suppression;
    }
    public boolean modifier(Reserv r){
        boolean update= true;
        try {
            String requete5 = "Update reservation SET id_user='"+r.getId_user()+"', id_coach='"+r.getId_coach()+"',id_salle='"+r.getId_salle()+"',date='"+r.getDate()+"' Where id_reser="+r.getId_reser();
            Statement st = conn.createStatement();
            st.executeUpdate(requete5);
            System.out.println("Reservation modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
            update = false;
        }
        return update;
    }
    public boolean modifier1(Reserv r){
        boolean update= true;
        try {
            String requete5 = "Update reservation SET id_user=1, id_coach='"+r.getId_coach()+"',id_salle='"+r.getId_salle()+"',date='"+r.getDate()+"' Where id_reser="+r.getId_reser();
            Statement st = conn.createStatement();
            st.executeUpdate(requete5);
            System.out.println("Reservation modifiée avec succés");
        } catch (SQLException ex) {
            System.err.println (ex.getMessage());
            update = false;
        }
        return update;
    }
    
        public List<Reserv> readAll(){
        List<Reserv> myList1 = new ArrayList<>();
        try {
            String requete3 = "SELECT date FROM reservation";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()){
                Reserv r = new Reserv();
                //r.setId_reser(rs.getInt(1));
                //r.setId_user(rs.getInt(1));
                //r.setId_even(rs.getInt(1));
                //r.setId_salle(rs.getInt(1));
                r.setDate(rs.getString("date"));
                myList1.add(r);
                System.out.println(r);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return myList1;
    
    }

   
    public int getId(String valeur,Integer val) throws SQLException{
        int id=0;
        ResultSet rs = null;
        ResultSet rs1 = null;
        String sqle="SELECT id_user from user where nom='"+valeur+"' OR prenom ='"+valeur+"'";
        Statement ste1 = conn.createStatement();
        rs1=ste1.executeQuery(sqle);
        while(rs.next()){
            val = rs.getInt("id_user");
        }
        String sql="SELECT id_reser from reservation where id_coach ='"+val+"'";
        Statement ste = conn.createStatement();
        rs=ste.executeQuery(sql);
        while(rs.next()){
            id = rs.getInt("id_seance");
        }
        return id ;
        
    }

}
