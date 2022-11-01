/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author mayro
 */
import java.util.List;

/**
 *
 * @author MSI
 */
public interface ISeance<T>  {
    void ajouter(T r);
    List<T>readAll();
    boolean modifier(T r);
    boolean supprimer(T r);
}

