package ru.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.JearCalendar;
import ru.models.JearCalendar_Workers;
import ru.models.Marker;
import ru.models.Worker;

import java.util.List;
import java.util.Set;

@Repository
public interface JearCalendarRepository extends CrudRepository<JearCalendar, Integer> {

}
