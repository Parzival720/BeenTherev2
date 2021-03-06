package cs.byu.edu.beentherev2.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import cs.byu.edu.beentherev2.model.Event;

public class Journal {
    private String title;
    private File photo;
    private String description;
    private Date startDate;
    private Date endDate;
    private ArrayList<Event> events;

    public Journal() {
        title = "";
        photo = new File("");
        description = "";
        startDate = new Date();
        endDate = new Date();
        events = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    //TODO: implement method to delete an event
}
