/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author mayro
 */
public interface IReserve<P> {
    void ajouter(P r);
    List<P>readAll();
    boolean modifier(P r);
    boolean supprimer(P r);
}