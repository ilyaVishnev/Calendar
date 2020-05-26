package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dao.repositories.WorkerRepository;
import ru.models.Department;
import ru.models.Worker;

import java.util.List;
import java.util.Set;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> findAllByDepartment(Department department) {
        return workerRepository.findAllByDepartment(department);
    }

    public Worker findByNumber_w(Integer number) {
        return workerRepository.findByNumber(number);
    }

    public Worker findByRole(String name) {
        return workerRepository.findByRole(name);
    }

    public Set<Worker> findWorkersByMonth(String month, Integer department) {
        return workerRepository.findWorkersByMonth(month, department);
    }

    public List<Worker> findAll() {
        return workerRepository.findAllBy();
    }

    public void save(Worker worker) {
        workerRepository.save(worker);
    }
}
