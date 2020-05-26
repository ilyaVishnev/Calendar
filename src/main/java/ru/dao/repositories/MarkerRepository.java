package ru.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.JearCalendar_Workers;
import ru.models.Marker;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Repository
public interface MarkerRepository extends CrudRepository<Marker,Integer> {

    @Query("select jw.marker from JearCalendar_Workers jw inner join JearCalendar j on j.id=jw.jearCalendar.id where jw.worker.number=:worker " +
            "and substring(concat(j.date_w,'') ,6,2)=:month order by substring(concat(j.date_w,'') ,9,2)")
    public List<Marker> getJearCalendarsByWorker(@Param("worker")Integer workerId, @Param("month")String month);

    public Marker findById(int id);

    public List<Marker>findAllBy();
}
