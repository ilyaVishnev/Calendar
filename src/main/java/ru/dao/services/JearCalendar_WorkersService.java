package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.dao.repositories.JearCalendar_WorkersRepository;
import ru.models.JearCalendar_Workers;

import java.util.List;

@Service
public class JearCalendar_WorkersService {

    @Autowired
    private JearCalendar_WorkersRepository jearCalendar_workersRepository;

    public List<JearCalendar_Workers> getJearCalendarsByWorker(@Param("worker") Integer workerId, @Param("month") String month) {
        return jearCalendar_workersRepository.getJearCalendarsByWorker(workerId, month);
    }

    public JearCalendar_Workers findById(int id) {
        return jearCalendar_workersRepository.findById(id);
    }

    public void save(JearCalendar_Workers jearCalendar_workers) {
        jearCalendar_workersRepository.save(jearCalendar_workers);
    }
}
