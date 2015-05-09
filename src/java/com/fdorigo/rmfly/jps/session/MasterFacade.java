/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jps.session;

import com.fdorigo.rmfly.jpa.entities.Master;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fdorigo
 */
@Stateless
public class MasterFacade extends AbstractFacade<Master> {
    @PersistenceContext(unitName = "AirshowPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MasterFacade() {
        super(Master.class);
    }
    
}
