package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.entities.Mokykla;
import com.example.laboratorinis1.entities.Mokytojas;
import com.example.laboratorinis1.entities.Pamoka;
import com.example.laboratorinis1.persistence.MokyklosDAO;
import com.example.laboratorinis1.persistence.PamokosDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PamokosJPA {
    @Inject
    private PamokosDAO pamokos;
    @Inject
    private MokyklosDAO mokyklos;
    @Getter
    @Setter
    private Pamoka newLesson = new Pamoka();
    @Getter
    private List<Pamoka> allLessons;
    @Getter
    private List<Mokykla> allSchools;

    @PostConstruct
    public void init() {
        loadAllLessons();
        newLesson.setMokykla(new Mokykla());
        newLesson.setMokytojas(new Mokytojas());
    }

    public Pamoka findById(long id) {return pamokos.findById(id);}

    public List<Pamoka> findByTeacherId(long teacherId) {return pamokos.findByTeacherId(teacherId);}

    @Transactional
    public void addLesson() {
        long id = newLesson.getMokykla().getId();
        Mokykla mokykla = mokyklos.find(id);
        mokykla.getMokytojuPamokos().add(newLesson.getMokytojas());
        pamokos.persist(newLesson);
        mokyklos.update(mokykla);
    }

    private void loadAllLessons() {
        allLessons = pamokos.loadAll();
        allSchools = mokyklos.findAll();
    }
}
