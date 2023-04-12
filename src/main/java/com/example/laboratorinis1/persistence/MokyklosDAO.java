package com.example.laboratorinis1.persistence;

import com.example.laboratorinis1.entities.Mokykla;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MokyklosDAO {
    @Inject
    private EntityManager em;

    public List<Mokykla> findAll() {return em.createNamedQuery("Mokykla.findAll", Mokykla.class).getResultList();}

    public void persist(Mokykla mokykla) {em.persist(mokykla);}

    public void update(Mokykla mokykla) {em.merge(mokykla);}

    public Mokykla find(long id) {return em.find(Mokykla.class, id);}
}
