package com.example.laboratorinis1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Mokytojas.findAll", query = "select m from Mokytojas m")
})
@Getter
@Setter
@Table(name = "MOKYTOJAS")
public class Mokytojas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "VARDAS")
    private String vardas;
    @Column(name = "PAVARDE")
    private String pavarde;

    @OneToMany(mappedBy = "mokytojas")
    private List<Pamoka> pamokos;

    @ManyToMany(mappedBy = "mokytojuPamokos")
    private Set<Mokykla> mokyklos;

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Mokytojas o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
