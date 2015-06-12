/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fdorigo.rmfly.jpa.session;

import com.fdorigo.rmfly.jpa.entities.Judge;
import java.util.ArrayList;
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
public class JudgeFacade extends AbstractFacade<Judge> {

    @PersistenceContext(unitName = "AirshowPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Judge getByFirstLast(Judge newJudge) {
        TypedQuery<Judge> query
                = em.createNamedQuery("Judge.findByFirstLastName", Judge.class);
        query.setParameter("firstName", newJudge.getFirstName());
        query.setParameter("lastName", newJudge.getLastName());
        Judge results = query.getResultList().get(0);
        return results;
    }

    public boolean exists(Judge newJudge) {
        boolean retVal = false;
        String newName = newJudge.getFirstName() + " " + newJudge.getLastName();

        for (String name : getAllNames()) {
            if (name.equalsIgnoreCase(newName)) {
                retVal = true;
                break;
            }
        }

        return retVal;
    }

    public List<String> getAllNames() {
        List<Judge> judges = findAll();
        List<String> names = new ArrayList<>();

        judges.stream().forEach((j) -> {
            names.add(j.getFirstName() + " " + j.getLastName());
        });

        return names;
    }

    public JudgeFacade() {
        super(Judge.class);
    }
}
