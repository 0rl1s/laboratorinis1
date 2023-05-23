package com.example.laboratorinis1.services;

import com.example.laboratorinis1.entities.Mokytojas;

import java.io.Serializable;

public interface NameCreator extends Serializable {
    String generateName(Mokytojas mokytojas);
}
