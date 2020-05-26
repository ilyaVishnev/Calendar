package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "markers")
public class Marker {

    private int id;
    private String sign;
    private List<JearCalendar_Workers> jearCalendar_workers;

    public Marker() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id_m")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "marker")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "marker")
    public List<JearCalendar_Workers> getJearCalendar_workers() {
        return jearCalendar_workers;
    }

    public void setJearCalendar_workers(List<JearCalendar_Workers> jearCalendar_workers) {
        this.jearCalendar_workers = jearCalendar_workers;
    }
}
