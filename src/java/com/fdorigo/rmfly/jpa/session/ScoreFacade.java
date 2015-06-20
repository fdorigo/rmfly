/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa.session;

import com.fdorigo.rmfly.jpa.entities.Record;
import com.fdorigo.rmfly.jpa.entities.Score;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author fdorigo
 */
@Stateless
public class ScoreFacade extends AbstractFacade<Score> {

    @PersistenceContext(unitName = "AirshowPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ScoreFacade() {
        super(Score.class);
    }

    public List<Score> getScoresByNnumber(Record nnumber) {
        TypedQuery<Score> query
                = em.createNamedQuery("Score.findByNnumber", Score.class);
        query.setParameter("nnumber", nnumber);
        List<Score> results = query.getResultList();
        return results;
    }

    public Integer exists(String judgeName, String nnumber) {
        Integer retVal = null;
        List<Score> scores = findAll();

        for (Score s : scores) {
            if (s.getNnumber().getNnumber().equals(nnumber) && s.getJudgeName().equalsIgnoreCase(judgeName)) {
                retVal = s.getId();
                break;
            }
        }

        return retVal;
    }
}
