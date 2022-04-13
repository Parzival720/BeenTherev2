package cs.byu.edu.beentherev2.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cs.byu.edu.beentherev2.R;

public class Event {
    private String title;
    private LatLng location;
    private List photos;
    private String description;
    private Date startDate;
    private Date endDate;
    private Float cost;
    private ArrayList<String> tags;

    private final String dateString = "dd MMMM yyyy";

    public Event() {
        title = "";
        location = new LatLng(50, 50);
        photos = new ArrayList<Integer>(Arrays.asList(new Integer(R.drawable.scoops)));
        description = "";
        startDate = new Date();
        endDate = null;
        cost = 0f;
        tags = new ArrayList<>(Arrays.asList("Ice cream"));
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

    public String getPrettyLocation() { return convert(); }

    private String convert() {
        double latitude = location.latitude;
        double longitude = location.longitude;
        StringBuilder builder = new StringBuilder();

        if (latitude < 0) {
            builder.append("S ");
        } else {
            builder.append("N ");
        }

        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builder.append(latitudeSplit[0]);
        builder.append("°");
        builder.append(latitudeSplit[1]);
        builder.append("'");
        builder.append(latitudeSplit[2]);
        builder.append("\"");

        builder.append(" ");

        if (longitude < 0) {
            builder.append("W ");
        } else {
            builder.append("E ");
        }

        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builder.append(longitudeSplit[0]);
        builder.append("°");
        builder.append(longitudeSplit[1]);
        builder.append("'");
        builder.append(longitudeSplit[2]);
        builder.append("\"");

        return builder.toString();
    }

    public List<Integer> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Integer> photos) {
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
        if (endDate == null) return null;
        DateFormat dateFormat = new SimpleDateFormat(dateString);
        return dateFormat.format(endDate);
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
