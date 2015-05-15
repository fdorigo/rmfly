/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jps.session;

import com.fdorigo.rmfly.jpa.entities.Master;
import com.fdorigo.rmfly.jpa.entities.Record;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fdorigo
 */
@Stateless
public class RecordFacade extends AbstractFacade<Record> {
    @PersistenceContext(unitName = "AirshowPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecordFacade() {
        super(Record.class);
    }
    
    public Record buildFromMaster(String id) {
        LOG.info("Building: " + id);
        
        if (em.find(Record.class, id) == null)
        {
            Master m = em.find(Master.class, id);
            LOG.info("Master: " + m.getName());
            Record r = new Record(id, m.getName());
            return r;
        }
        
        return null;
    }
    private static final Logger LOG = Logger.getLogger(RecordFacade.class.getName());
    
}
