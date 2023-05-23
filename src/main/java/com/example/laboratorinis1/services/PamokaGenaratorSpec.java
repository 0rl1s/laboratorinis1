package com.example.laboratorinis1.services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;
@Specializes
@ApplicationScoped
public class PamokaGenaratorSpec extends PamokaGenerator {
    public long generateId() {
        try {
            Random rand = new Random();
            long id = rand.nextLong();
            Thread.sleep(3000);
            return id;
        }
        catch (InterruptedException e) {
            return 0;
        }
    }
}
