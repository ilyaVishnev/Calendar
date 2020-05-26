package ru.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.JearCalendar;
import ru.models.JearCalendar_Workers;

import java.util.List;

@Repository
public interface JearCalendar_WorkersRepository extends CrudRepository<JearCalendar_Workers, Integer> {

    @Query("from JearCalendar_Workers jw inner join JearCalendar j on j.id=jw.jearCalendar.id where jw.worker.number=:worker " +
            "and substring(concat(j.date_w,'') ,6,2)=:month order by substring(concat(j.date_w,'') ,9,2)")
    public List<JearCalendar_Workers> getJearCalendarsByWorker(@Param("worker")Integer workerId, @Param("month")String month);

    public JearCalendar_Workers findById(int id);
}
