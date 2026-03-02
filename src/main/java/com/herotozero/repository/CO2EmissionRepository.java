package com.herotozero.repository;

import com.herotozero.model.CO2Emission;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CO2EmissionRepository {

    @PersistenceContext
    private EntityManager em;

    public List<CO2Emission> findByCountry(String country) {
        return em.createQuery(
                "SELECT c FROM CO2Emission c WHERE c.country = :country AND c.approved = true ORDER BY c.year DESC", 
                CO2Emission.class)
                .setParameter("country", country)
                .getResultList();
    }

    public CO2Emission findByCountryAndYear(String country, Integer year) {
        List<CO2Emission> results = em.createQuery(
                "SELECT c FROM CO2Emission c WHERE c.country = :country AND c.year = :year AND c.approved = true", 
                CO2Emission.class)
                .setParameter("country", country)
                .setParameter("year", year)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public CO2Emission findLatestByCountry(String country) {
        List<CO2Emission> results = em.createQuery(
                "SELECT c FROM CO2Emission c WHERE c.country = :country AND c.approved = true ORDER BY c.year DESC", 
                CO2Emission.class)
                .setParameter("country", country)
                .setMaxResults(1)
                .getResultList();
        return results.isEmpty() ? null : results.get(0);
    }

    public List<CO2Emission> findAll() {
        return em.createQuery("SELECT c FROM CO2Emission c WHERE c.approved = true ORDER BY c.year DESC", CO2Emission.class)
                .getResultList();
    }

    public List<CO2Emission> findPending() {
        return em.createQuery("SELECT c FROM CO2Emission c WHERE c.approved = false ORDER BY c.year DESC", CO2Emission.class)
                .getResultList();
    }

    @Transactional
    public void save(CO2Emission emission) {
        if (emission.getId() == null) {
            em.persist(emission);
        } else {
            em.merge(emission);
        }
        em.flush();
    }

    @Transactional
    public void delete(CO2Emission emission) {
        em.remove(em.contains(emission) ? emission : em.merge(emission));
        em.flush();
    }

    public CO2Emission findById(Long id) {
        return em.find(CO2Emission.class, id);
    }

    public List<CO2Emission> findAllByCountryAndYear(String country, Integer year) {
        return em.createQuery(
                "SELECT c FROM CO2Emission c WHERE c.country = :country AND c.year = :year", 
                CO2Emission.class)
                .setParameter("country", country)
                .setParameter("year", year)
                .getResultList();
    }
}
