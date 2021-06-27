package springserverless.service;

import springserverless.model.CalendarEvent;

public interface ICalendarEventService {

    boolean delete(Long id);
    boolean add(CalendarEvent calendarEvent);

    boolean updateCalendarEvent(CalendarEvent calendarEvent);
}
