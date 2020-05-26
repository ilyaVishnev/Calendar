package ru.models;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "jearcalendar_workers")
public class JearCalendar_Workers {

    private int id;
    private Marker marker;
    private JearCalendar jearCalendar;
    private Worker worker;

    public JearCalendar_Workers() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq_gen")
    @SequenceGenerator(name = "my_seq_gen", sequenceName = "GEN_SEQUENCE")
    @Column(name = "id_cw")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_m", referencedColumnName = "id_m")
    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        this.marker = marker;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_c", referencedColumnName = "id_c")
    public JearCalendar getJearCalendar() {
        return jearCalendar;
    }

    public void setJearCalendar(JearCalendar jearCalendar) {
        this.jearCalendar = jearCalendar;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "number_w", referencedColumnName = "number_w")
    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        JearCalendar_Workers jearCalendar_workers = (JearCalendar_Workers) o;
        if (this.getId() == jearCalendar_workers.getId()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }
}
