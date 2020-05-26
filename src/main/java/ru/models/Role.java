package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "roles")
public class Role {

    private int id;
    private String name;
    private String password;
    private List<Worker>workers=new ArrayList<>();

    public Role(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id_r")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "role")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "role")
    public List<Worker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
