package ru.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.models.Department;
import ru.models.Worker;

import java.util.List;
import java.util.Set;

@Repository
public interface WorkerRepository extends CrudRepository<Worker, Integer> {

    List<Worker> findAllByDepartment(Department department);

    Worker findByNumber(int number);

    @Query("from Worker w where w.role.name=:name ")
    Worker findByRole(String name);

    @Query("select jw.worker from JearCalendar_Workers jw inner join JearCalendar j on jw.jearCalendar.id=j.id where substring(concat(jw.jearCalendar.date_w,'') ,6,2)=:month and jw.worker.department.id=:dep")
    Set<Worker> findWorkersByMonth(@Param("month")String month,@Param("dep")Integer dep);

    List<Worker>findAllBy();
}
