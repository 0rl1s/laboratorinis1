package com.example.laboratorinis1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Pamoka.findAll", query = "select p from Pamoka p"),
        @NamedQuery(name = "Pamoka.findByTeacherId", query = "select p from Pamoka p where p.mokytojas.id =: mokytojas")
})
@Getter
@Setter
@Table(name = "PAMOKA")
public class Pamoka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PAVADINIMAS")
    private String pavadinimas;

    @ManyToOne
    @JoinColumn(name = "MOKYTOJO_ID", nullable = false)
    private Mokytojas mokytojas;

    @ManyToOne
    @JoinColumn(name = "MOKYKLOS_ID", nullable = false)
    private Mokykla mokykla;

    @Version
    @Column(name = "opt_lock_version")
    private int optLockVersion;

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Pamoka o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
