package ru.dao.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import ru.dao.repositories.JearCalendarRepository;
import ru.models.JearCalendar;
import ru.models.JearCalendar_Workers;
import ru.models.Worker;

import java.util.Set;

@Service
public class JearCalendarService {

    @Autowired
    private JearCalendarRepository jearCalendarRepository;
}
