package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.mybatis.dao.MokyklaMapper;
import com.example.laboratorinis1.mybatis.dao.MokytojasMapper;
import com.example.laboratorinis1.mybatis.dao.PamokaMapper;
import com.example.laboratorinis1.mybatis.model.Pamoka;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class PamokosMyBatis {
    @Inject
    private PamokaMapper pamokaMapper;
    @Inject
    private MokyklaMapper mokyklaMapper;
    @Inject
    private MokytojasMapper mokytojasMapper;

    @Getter
    @Setter
    private Pamoka newLesson = new Pamoka();
    @Getter
    private List<Pamoka> allLessons;

    @PostConstruct
    public void init() {loadAllLessons();}

    public Pamoka findById(long id) {return pamokaMapper.selectByPrimaryKey(id);}
    @Transactional
    public void addLesson() {pamokaMapper.insert(newLesson);}

    private void loadAllLessons() {allLessons = pamokaMapper.selectAll();}
}
