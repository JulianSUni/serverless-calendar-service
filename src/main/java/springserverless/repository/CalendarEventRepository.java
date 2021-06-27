package springserverless.repository;

import springserverless.model.CalendarEvent;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO calendarevents(start, \"end\", title, \"allday\", draggable) VALUES(:start,:end,:title,:allday,:draggable)", nativeQuery = true)
    void addCalendarEventWithQuery(@Param("start") Date start, @Param("end") Date end, @Param("title") String title, @Param("allday") Boolean allDay, @Param("draggable") Boolean draggable);

    @Modifying
    @Transactional
    @Query(value = "UPDATE calendarevents SET start = :start, \"end\" = :end, title = :title, \"allday\" = :allday, draggable = :draggable WHERE id = :id", nativeQuery = true)
    void updateCalendarEventWithQuery(@Param("start") Date start, @Param("end") Date end, @Param("title") String title, @Param("allday") Boolean allDay, @Param("draggable") Boolean draggable, @Param("id") Long id);

}
