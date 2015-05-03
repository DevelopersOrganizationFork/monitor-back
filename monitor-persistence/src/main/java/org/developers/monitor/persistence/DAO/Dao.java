/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.developers.monitor.persistence.DAO;

import java.lang.reflect.ParameterizedType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tomek
 */
public abstract class Dao<K, E> implements IDao<K, E> {
    protected Class<E> entityClass;
 
    @PersistenceContext(unitName = "entityUltraManager")
    protected EntityManager entityManager;
 
    public Dao() {
	ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
	this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("entityUltraManager");
        entityManager = emf.createEntityManager();
    }
 
    @Override
    public E persist(E entity) throws Exception { 
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }
 
    @Override
    public void remove(E entity) throws Exception { 
        entityManager.remove(entity); 
    }
 
    @Override
    public E findById(K id) throws Exception { 
        return entityManager.find(entityClass, id); 
    }
}
