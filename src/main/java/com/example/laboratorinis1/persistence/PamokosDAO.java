package com.example.laboratorinis1.persistence;

import com.example.laboratorinis1.entities.Pamoka;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class PamokosDAO {
    @Inject
    private EntityManager em;

    @Transactional
    public List<Pamoka> loadAll() {return em.createNamedQuery("Pamoka.findAll", Pamoka.class).getResultList();}

    public void persist(Pamoka pamoka){
        em.persist(pamoka);
        em.flush();
    }

    public Pamoka findById(long id) {return em.find(Pamoka.class, id);}

    public Pamoka update(Pamoka pamoka) {
        Pamoka p = em.merge(pamoka);
        em.flush();
        return p;
    }

    public List<Pamoka> findByTeacherId(long teacherId) {
        return em.createNamedQuery("Pamoka.findByTeacherId", Pamoka.class).setParameter("mokytojas", teacherId).getResultList();
    }
}
