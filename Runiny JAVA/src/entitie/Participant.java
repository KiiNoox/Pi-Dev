/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author pc
 */
public class Participant {
      private int id_parti ,idEvent,id_user;

    public Participant(int id_parti, int idEvent, int id_user) {
        this.id_parti = id_parti;
        this.idEvent = idEvent;
        this.id_user = id_user;
    }

    public Participant(int idEvent, int id_user) {
        this.idEvent = idEvent;
        this.id_user = id_user;
    }

    public Participant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId_parti() {
        return id_parti;
    }

    public void setId_parti(int id_parti) {
        this.id_parti = id_parti;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Participant{" + "id_parti=" + id_parti + ", idEvent=" + idEvent + ", id_user=" + id_user + '}';
    }
    

  

}
