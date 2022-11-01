/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import java.time.LocalDate;
import javax.activation.DataSource;
import utils.MyDB;

/**
 *
 * @author pc
 */


public class Evenement {
   private int idEvent,prix;
   private String descri,nom;
   private Date dateEvent;

   
   
    public Evenement(int idEvent, int prix, String descri, String nom, Date dateEvent) {
        this.idEvent = idEvent;
        this.prix = prix;
        this.descri = descri;
        this.nom = nom;
        this.dateEvent = dateEvent;
    }

    public Evenement(int prix, String descri, String nom, Date dateEvent) {
        this.prix = prix;
        this.descri = descri;
        this.nom = nom;
        this.dateEvent = dateEvent;
    }

    public Evenement() {
       
    }

    

   

    public int getIdEvent() {
        return idEvent;
    }

    public int getPrix() {
        return prix;
    }

    public String getDescri() {
        return descri;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    @Override
    public String toString() {
        return nom +" / " + prix + " / " + descri + " / " + dateEvent;
    }

    public void setDateEvent(LocalDate dateEven) {
//      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
   
   
  
}
