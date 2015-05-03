/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

/**
 *
 * @author Tomek
 */
public interface IDao<K, E> {
    E persist(E entity);
    void remove(E entity);
    E findById(K id);
}
