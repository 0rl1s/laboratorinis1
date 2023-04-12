package com.example.lab1.usecases;

import com.example.lab1.entities.Mokykla;
import com.example.lab1.persistence.MokyklosDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MokyklosJPA {

    @Inject
    private MokyklosDAO mokyklosDAO;

    @Getter
    @Setter
    private Mokykla newSchool = new Mokykla();

    @Getter
    private List<Mokykla> allSchools;

    @PostConstruct
    public void init() {loadAllSchools();}

    public Mokykla findById (Long id) {return mokyklosDAO.find(id);}

    @Transactional
    public void addSchool (){
        this.mokyklosDAO.persist(newSchool);
    }

    private void loadAllSchools(){
        this.allSchools = mokyklosDAO.findAll();
    }
}
