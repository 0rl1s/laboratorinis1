package com.example.laboratorinis1.usecases;

import com.example.laboratorinis1.mybatis.dao.MokyklaMapper;
import com.example.laboratorinis1.mybatis.model.Mokykla;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class MokyklosMyBatis {
    @Inject
    private MokyklaMapper mokyklaMapper;

    @Getter
    @Setter
    private Mokykla newSchool = new Mokykla();

    @Getter
    private List<Mokykla> allSchools;

    @PostConstruct
    public void init() {
        loadAllSchools();
    }

    public Mokykla findById(long id) {
        return mokyklaMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void addSchool() {mokyklaMapper.insert(newSchool);}

    private void loadAllSchools() {allSchools = mokyklaMapper.selectAll();}
}
