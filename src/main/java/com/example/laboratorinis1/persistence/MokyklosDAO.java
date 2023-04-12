package com.example.lab1.persistence;

import com.example.lab1.entities.Mokykla;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MokyklosDAO {
    @Inject
    private EntityManager em;

    public List<Mokykla> findAll() {return em.createNamedQuery("Mokykla.findAll", Mokykla.class).getResultList();}

    public void persist(Mokykla mokykla) {this.em.persist(mokykla);}

    public Mokykla find(long id) {return em.find(Mokykla.class, id);}
}
