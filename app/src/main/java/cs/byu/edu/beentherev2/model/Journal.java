package cs.byu.edu.beentherev2.model;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cs.byu.edu.beentherev2.R;
import cs.byu.edu.beentherev2.model.Event;

public class Journal {
    private String title;
    private int photo;
    private String description;
    private Date startDate;
    private Date endDate;
    private ArrayList<Event> events;

    private final String dateString = "dd MMMM yyyy";

    public Journal() {
        title = "";
        photo = R.drawable.santamonica;
        description = "";
        startDate = Calendar.getInstance().getTime();
        endDate = Calendar.getInstance().getTime();
        events = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
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

    public String getPrettyStartDate() {
        DateFormat dateFormat = new SimpleDateFormat(dateString);
        return dateFormat.format(startDate);
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getPrettyEndDate() {
        DateFormat dateFormat = new SimpleDateFormat(dateString);
        return dateFormat.format(endDate);
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
