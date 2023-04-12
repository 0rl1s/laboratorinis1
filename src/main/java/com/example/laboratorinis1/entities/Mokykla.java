package com.example.laboratorinis1.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQueries({
        @NamedQuery(name = "Mokykla.findAll", query = "select m from Mokykla as m")
})
@Getter
@Setter
@Table(name = "MOKYKLA")
public class Mokykla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PAVADINIMAS", nullable = false)
    private String pavadinimas;

    @OneToMany(mappedBy = "mokykla")
    private List<Pamoka> pamokos;

    @ManyToMany
    private Set<Mokytojas> mokytojuPamokos = new HashSet<>();

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Mokykla o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

}
