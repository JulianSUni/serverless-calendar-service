package springserverless.controller;


import springserverless.model.CalendarEvent;
import springserverless.service.ICalendarEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;


@RestController
@EnableWebMvc
public class CalendarApplicationController {

    @Autowired
    CalendarEventRepository calendarEventRepository;
    @Autowired
    private ICalendarEventService calendarEventService;

    @RequestMapping(path = "/ping", method = RequestMethod.GET)
    public Map<String, String> ping() {
        Map<String, String> pong = new HashMap<>();
        pong.put("pong", "Hello, World!");
        return pong;
    }

    /**
     * REST-Endpoint to get all CalendarEvents
     * Handles AWS event. Delivered from {@link StreamLambdaHandler#handleRequest} by constructing special proxy request.
     * @return calendarEvents
     */
    @GetMapping("/showCalendarEvents")
    public CalendarEvent getCalendarEvents(Model model) {

        return new CalendarEvent((long) 1, "title", true, true);
    }

    /**
     * REST-Endpoint to delete a CalendarEvent
     *
     * @param id Id of the calendarEvent to be deleted
     */
    @DeleteMapping(value = "/deleteEvent/{id}")
    public ResponseEntity deleteCalendarEvent(@PathVariable Long id) {
        System.out.println(id);
        boolean isRemoved = false;

        if (!isRemoved) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addCalendarEvent")
    public ResponseEntity saveCalendarEvent(@RequestBody CalendarEvent calendarEvent) {
        boolean isSaved = true;

        if (!isSaved) {
            System.out.println(calendarEvent);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(calendarEvent);
            return new ResponseEntity<>(calendarEvent, HttpStatus.OK);
        }
    }

    @PutMapping(value = "/updateEvent/{calendarEventId}")
    public ResponseEntity updateCalendarEvent(@PathVariable Long calendarEventId, @RequestBody CalendarEvent calendarEvent) {

        boolean calendarEventOld = true;
        boolean CalendarEventUpdated = true;
        if (calendarEventOld) {
            if (CalendarEventUpdated == true) {
                return new ResponseEntity<>(calendarEvent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(calendarEvent, HttpStatus.LOCKED);
            }
        } else {
            return new ResponseEntity(calendarEvent, HttpStatus.BAD_REQUEST);
        }
    }
}
