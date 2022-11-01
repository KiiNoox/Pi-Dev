/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class payement {
    
    int id_pay;
    int montant;
    String date_pay;

    public payement() {
    }

    public payement(int id_pay, int montant, String date_pay) {
        this.id_pay = id_pay;
        this.montant = montant;
        this.date_pay = date_pay;
    }

    public payement(int montant, String date_pay) {
        this.montant = montant;
        this.date_pay = date_pay;
    }

    public int getId_pay() {
        return id_pay;
    }

    public void setId_pay(int id_pay) {
        this.id_pay = id_pay;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public String getDate_pay() {
        return date_pay;
    }

    public void setDate_pay(String date_pay) {
        this.date_pay = date_pay;
    }

    @Override
    public String toString() {
        return "payement{" + "id_pay=" + id_pay + ", montant=" + montant + ", date_pay=" + date_pay + '}';
    }
    
    
    
    
}
