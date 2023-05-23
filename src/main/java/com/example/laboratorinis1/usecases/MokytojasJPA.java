package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.entities.Mokytojas;
import com.example.laboratorinis1.persistence.MokytojaiDAO;
import com.example.laboratorinis1.services.NameCreator;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MokytojasJPA {
    @Inject
    private MokytojaiDAO mokytojai;
    @Getter
    @Setter
    private Mokytojas newTeacher = new Mokytojas();
    @Inject
    private NameCreator nameCreator;
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

    public String findSchools() {return mokytojai.schools();}

    @Transactional
    public void addTeacher() {
        newTeacher.setSlapyvardis(nameCreator.generateName(newTeacher));
        mokytojai.persist(newTeacher);
    }

    private void loadAllTeachers() {

        allTeachers = mokytojai.loadAll();
        if (allTeachers.size() >= 1) {
            editTeacher = allTeachers.get(0);
        }
    }
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public String saveChanges() throws InterruptedException {
        editTeacher.setSlapyvardis(nameCreator.generateName(editTeacher));
        System.out.println(editTeacher.getSlapyvardis());
        System.out.println("5s miegas");
        Thread.sleep(5000);
        try {
            mokytojai.update(editTeacher);
            return "mokytojai?faces-redirect=true";
        }
        catch (OptimisticLockException e) {
            System.out.println("Optimistinio rakinimo klaida\n" + e);
            return "edit-teacher?faces-redirect=true" + "&error=optimistic-lock-exception";
        }
    }
}
