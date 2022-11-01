/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitie;

/**
 *
 * @author ACER EXTENSA 15
 */
public class User {
    private int id_user;
    private String Nom , Prenom ,Adress, email , password,role,Date_nais;

    public User(int id_user, String Nom, String Prenom, String Adress, String email, String password, String role, String Date_nais) {
        this.id_user = id_user;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adress = Adress;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Date_nais = Date_nais;
    }

    public User(String Nom, String Prenom, String Adress, String email, String password, String role, String Date_nais) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Adress = Adress;
        this.email = email;
        this.password = password;
        this.role = role;
        this.Date_nais = Date_nais;
    }

    public User() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDate_nais() {
        return Date_nais;
    }

    public void setDate_nais(String Date_nais) {
        this.Date_nais = Date_nais;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", Nom=" + Nom + ", Prenom=" + Prenom + ", Adress=" + Adress + ", email=" + email + ", password=" + password + ", role=" + role + ", date_nais=" + Date_nais + '}';
    }

   

    

   
    
    

   
    
}
