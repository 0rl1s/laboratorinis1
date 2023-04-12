package com.example.laboratorinis1.persistence;

import com.example.laboratorinis1.entities.Mokykla;
import com.example.laboratorinis1.entities.Mokytojas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class MokytojaiDAO {
    @Inject
    private EntityManager em;

    public List<Mokytojas> loadAll() {return em.createNamedQuery("Mokytojas.findAll", Mokytojas.class).getResultList();}

    public void persist(Mokytojas mokytojas){
        em.persist(mokytojas);
        em.flush();
    }

    public Mokytojas update(Mokytojas mokytojas) {
        Mokytojas m = em.merge(mokytojas);
        em.flush();
        return m;
    }

    public Mokytojas findbyId(long id) {return em.find(Mokytojas.class, id);}
}
