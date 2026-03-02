package com.herotozero.repository;

import com.herotozero.model.Scientist;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class ScientistRepository {

    @PersistenceContext
    private EntityManager em;

    public Scientist findByUsername(String username) {
        List<Scientist> results = em.createQuery(
                "SELECT s FROM Scientist s WHERE s.username = :username", 
                Scientist.class)
                .setParameter("username", username)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }
}
