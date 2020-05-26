package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "departments")
public class Department {

    private int id;
    private String name;
    private List<Worker> workers = new ArrayList<Worker>();

    public Department() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id_dep")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "department")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "department")
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
