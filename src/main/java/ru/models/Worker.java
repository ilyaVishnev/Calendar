package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "workers")
public class Worker {

    private int number;
    private String first_name;
    private String second_name;
    private String middle_name;
    private Timestamp birthdate;
    private String employment;
    private boolean remoute_workspace;
    private String address;
    private Department department;
    private Role role;
    private List<JearCalendar_Workers> jearCalendar_workersList=new ArrayList<>();

    public Worker(){}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "number_w")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Column(name = "first_name")
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "second_name")
    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    @Column(name = "middle_name")
    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    @Column(name = "birthdate")
    public Timestamp getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Timestamp birthdate) {
        this.birthdate = birthdate;
    }

    @Column(name = "employment")
    public String getEmployment() {
        return employment;
    }

    public void setEmployment(String employment) {
        this.employment = employment;
    }

    @Column(name = "remoute_workspace")
    public boolean isRemoute_workspace() {
        return remoute_workspace;
    }

    public void setRemoute_workspace(boolean remoute_workspace) {
        this.remoute_workspace = remoute_workspace;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dep",referencedColumnName = "id_dep")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_r",referencedColumnName = "id_r")
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "worker")
    public List<JearCalendar_Workers> getJearCalendar_workersList() {
        return jearCalendar_workersList;
    }

    public void setJearCalendar_workersList(List<JearCalendar_Workers> jearCalendar_workersList) {
        this.jearCalendar_workersList = jearCalendar_workersList;
    }

    @Override
    public int hashCode() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        if (this.getNumber() == worker.getNumber()) {
            return true;
        }
        return false;
    }
}
