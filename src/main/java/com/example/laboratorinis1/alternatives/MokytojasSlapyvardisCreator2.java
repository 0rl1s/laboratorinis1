package com.example.laboratorinis1.alternatives;

import com.example.laboratorinis1.entities.Mokytojas;
import com.example.laboratorinis1.services.NameCreator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@Alternative
@ApplicationScoped
public class MokytojasSlapyvardisCreator2 implements NameCreator {
    @Override
    public String generateName(Mokytojas mokytojas) {
        if (mokytojas == null) {
            throw new NullPointerException("Mokytojo nėra");
        }
        return mokytojas.getVardas() + mokytojas.getPavarde() + "DU";
    }
}
