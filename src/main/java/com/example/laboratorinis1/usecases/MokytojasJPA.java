package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.entities.Mokytojas;
import com.example.laboratorinis1.persistence.MokytojaiDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MokytojasJPA {
    @Inject
    private MokytojaiDAO mokytojai;
    @Getter
    @Setter
    private Mokytojas newTeacher = new Mokytojas();
    @Getter
    @Setter
    private Mokytojas editTeacher = new Mokytojas();
    @Getter
    private List<Mokytojas> allTeachers;

    @PostConstruct
    public void init(){
        loadAllTeachers();
    }

    public Mokytojas findById(long id) {return mokytojai.findbyId(id);}

    @Transactional
    public void addTeacher() {
        mokytojai.persist(newTeacher);
    }

    private void loadAllTeachers() {
        allTeachers = mokytojai.loadAll();
    }
}
