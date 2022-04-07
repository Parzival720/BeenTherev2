package cs.byu.edu.beentherev2.model;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String title;
    private LatLng location;
    private ArrayList<File> photos;
    private String description;
    private Date startDate;
    private Date endDate;
    private Float cost;
    private ArrayList<String> tags;

    public Event() {
        title = "";
        location = new LatLng(50, 50);
        photos = new ArrayList<>();
        description = "";
        startDate = new Date();
        endDate = new Date();
        cost = 0f;
        tags = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }

    public ArrayList<File> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<File> photos) {
        this.photos = photos;
    }

    public void addPhoto(File photo) {
        this.photos.add(photo);
    }

    //TODO: implement a delete photo function? In case an invalid picture is chosen

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

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }
}
