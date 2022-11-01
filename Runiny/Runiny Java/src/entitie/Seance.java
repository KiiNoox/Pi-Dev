/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

public class Seance {
    private int id_seance;
    private String date,type_seance;

    public Seance(int id_seance, String date, String type_seance) {
        this.id_seance = id_seance;
        this.date = date;
        this.type_seance = type_seance;
    }

    public Seance(String date, String type_seance) {
        this.date = date;
        this.type_seance = type_seance;
    }

    public Seance() {
        
    }

    public int getId_seance() {
        return id_seance;
    }

    public void setId_seance(int id_seance) {
        this.id_seance = id_seance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType_seance() {
        return type_seance;
    }

    public void setType_seance(String type_seance) {
        this.type_seance = type_seance;
    }

    @Override
    public String toString() {
        return "Seance " + "de " + type_seance + ", date " + date ;
    }
    
    
}

