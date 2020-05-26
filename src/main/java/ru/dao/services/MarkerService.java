package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.dao.repositories.MarkerRepository;
import ru.models.Marker;

import java.util.List;

@Service
public class MarkerService {

    @Autowired
    private MarkerRepository markerRepository;

    public List<Marker> getJearCalendarsByWorker(@Param("worker") Integer workerId, @Param("month") String month) {
        return markerRepository.getJearCalendarsByWorker(workerId, month);
    }

    public Marker findById(int id) {
        return markerRepository.findById(id);
    }

    public List<Marker> findAll() {
        return markerRepository.findAllBy();
    }
}
