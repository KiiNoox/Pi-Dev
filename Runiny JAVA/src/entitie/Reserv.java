/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitie;

import com.mysql.jdbc.Connection;
import java.sql.Statement;

public class Reserv {
    private int id_reser;
    private int id_user;
    private int id_even;
    private int id_coach;
    private int id_salle;
    private String date;
    public static Reserv user;
 
    
    Connection conn = null;
    Statement st = null;
    String url = "jdbc:mysql://localhost:3306/bdr";
    String login = "root";
    String password = "";

    public Reserv(int id_reser, int id_user, int id_even,int id_coach, int id_salle, String date) {
        this.id_reser = id_reser;
        this.id_user = id_user;
        this.id_even = id_even;
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }
    public Reserv(int id_reser,int id_coach, int id_salle, String date) {
        this.id_reser = id_reser;
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }

    public Reserv(int id_user, int id_even,int id_coach, int id_salle, String date) {
        this.id_user = id_user;
        this.id_even = id_even;
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }
    
    public Reserv(int id_coach, int id_salle, String date) {
        this.id_coach = id_coach;
        this.id_salle = id_salle;
        this.date = date;
    }
    public Reserv() {
    }
public Reserv(int id_reser, String date) {
        this.id_reser = id_reser;
        this.date = date;
    }

    public int getId_coach() {
        return id_coach;
    }

    public void setId_coach(int id_coach) {
        this.id_coach = id_coach;
    }

    public int getId_reser() {
        return id_reser;
    }

    public void setId_reser(int id_reser) {
        this.id_reser = id_reser;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public int getId_salle() {
        return id_salle;
    }

    public void setId_salle(int id_salle) {
        this.id_salle = id_salle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Reservation " +
                ", salle=" + id_salle +
                ", date='" + date ;
    }
}
