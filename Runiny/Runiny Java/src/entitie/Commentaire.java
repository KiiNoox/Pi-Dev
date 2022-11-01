/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared.entities;
import java.sql.*;
import java.util.*;
import java.util.Date;
/**
 *
 * @author seif
 */
public class Commentaire {
    private int id;
    private int id_guest;
    private int id_publication;
    private String nom;
    private String comment;
    private Date date_com;
    private int note;

    public Commentaire(int id, int id_guest, int id_publication, String nom, String comment, int note) {
        this.id = id;
        this.id_guest = id_guest;
        this.id_publication = id_publication;
        this.nom = nom;
        this.comment = comment;
        this.note = note;
    }
    
    
    public Commentaire() {
        
    }

    public Commentaire(int id_guest, String nom, String comment, Date date_com) {
        this.id_guest = id_guest;
        this.nom = nom;
        this.comment = comment;
        this.date_com = date_com;
    }

    public Commentaire(int id, int id_guest, int id_publication, String nom, String comment, Date date_com) {
        this.id = id;
        this.id_guest = id_guest;
        this.id_publication = id_publication;
        this.nom = nom;
        this.comment = comment;
        this.date_com = date_com;
    }

    public Commentaire(int id, int id_guest, int id_publication, String nom, String comment) {
        this.id = id;
        this.id_guest = id_guest;
        this.id_publication = id_publication;
        this.nom = nom;
        this.comment = comment;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNote() {
        return note;
    }

    public Commentaire(int id, int id_guest, int id_publication, String nom, String comment, Date date_com, int note) {
        this.id = id;
        this.id_guest = id_guest;
        this.id_publication = id_publication;
        this.nom = nom;
        this.comment = comment;
        this.date_com = date_com;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getId_guest() {
        return id_guest;
    }

    public int getId_publication() {
        return id_publication;
    }

    public String getNom() {
        return nom;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate_com() {
        return date_com;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_guest(int id_guest) {
        this.id_guest = id_guest;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }
    

//    @Override
//    public String toString() {
//        return "Commentaire{ id_guest=" + id_guest + ",  nom=" + nom + ", comment=" + comment + ", date_com=" + date_com + '}';
//    }
//    

//    @Override
//    public String toString() {
//        return "Commentaire{" + "id=" + id + ", id_guest=" + id_guest + ", id_publication=" + id_publication + ", nom=" + nom + ", comment=" + comment + ", date_com=" + date_com + '}';
//    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", id_guest=" + id_guest + ", id_publication=" + id_publication + ", nom=" + nom + ", comment=" + comment + ", date_com=" + date_com + ", note=" + note + '}';
    }

   
    
    
    
    
    
}
