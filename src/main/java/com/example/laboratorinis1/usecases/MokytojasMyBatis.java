package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.mybatis.dao.MokytojasMapper;
import com.example.laboratorinis1.mybatis.model.Mokytojas;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MokytojasMyBatis {
    @Inject
    private MokytojasMapper mokytojasMapper;

    @Getter
    @Setter
    private Mokytojas newTeacher = new Mokytojas();

    @Getter
    private List<Mokytojas> allTeachers;

    @PostConstruct
    public void init() {loadAllTeachers();}

    public Mokytojas findById(long id) {return mokytojasMapper.selectByPrimaryKey(id);}

    @Transactional
    public void addTeacher() {mokytojasMapper.insert(newTeacher);}

    private void loadAllTeachers() {allTeachers = mokytojasMapper.selectAll();}
}
