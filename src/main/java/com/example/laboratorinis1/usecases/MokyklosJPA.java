package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.entities.Mokykla;
import com.example.laboratorinis1.persistence.MokyklosDAO;
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
    private MokyklosDAO mokyklos;

    @Getter
    @Setter
    private Mokykla newSchool = new Mokykla();

    @Getter
    private List<Mokykla> allSchools;

    @PostConstruct
    public void init() {loadAllSchools();}

    public Mokykla findById (Long id) {return mokyklos.find(id);}

    @Transactional
    public void addSchool (){mokyklos.persist(newSchool);}

    private void loadAllSchools(){allSchools = mokyklos.findAll();}
}
