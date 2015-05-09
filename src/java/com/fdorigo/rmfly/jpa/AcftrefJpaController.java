/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa;

import com.fdorigo.rmfly.jpa.entities.Acftref;
import com.fdorigo.rmfly.jpa.exceptions.NonexistentEntityException;
import com.fdorigo.rmfly.jpa.exceptions.PreexistingEntityException;
import com.fdorigo.rmfly.jpa.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author fdorigo
 */
public class AcftrefJpaController implements Serializable {

    public AcftrefJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acftref acftref) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(acftref);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAcftref(acftref.getCode()) != null) {
                throw new PreexistingEntityException("Acftref " + acftref + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acftref acftref) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            acftref = em.merge(acftref);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = acftref.getCode();
                if (findAcftref(id) == null) {
                    throw new NonexistentEntityException("The acftref with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Acftref acftref;
            try {
                acftref = em.getReference(Acftref.class, id);
                acftref.getCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acftref with id " + id + " no longer exists.", enfe);
            }
            em.remove(acftref);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acftref> findAcftrefEntities() {
        return findAcftrefEntities(true, -1, -1);
    }

    public List<Acftref> findAcftrefEntities(int maxResults, int firstResult) {
        return findAcftrefEntities(false, maxResults, firstResult);
    }

    private List<Acftref> findAcftrefEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acftref.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Acftref findAcftref(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acftref.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcftrefCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acftref> rt = cq.from(Acftref.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
