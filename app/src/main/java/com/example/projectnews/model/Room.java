package com.example.projectnews.model;

import java.sql.Time;
import java.util.Objects;

public class Room {
    private int Id;
    private int Movie;
    private Time Timeline;
    private String Datetime;
    private String Name;
    private String Venue;

    public Room() {
    }

    public Room(int id, int movie, Time timeline, String datetime, String name, String venue) {
        Id = id;
        Movie = movie;
        Timeline = timeline;
        Datetime = datetime;
        Name = name;
        Venue = venue;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getMovie() {
        return Movie;
    }

    public void setMovie(int movie) {
        Movie = movie;
    }

    public Time getTimeline() {
        return Timeline;
    }

    public void setTimeline(Time timeline) {
        Timeline = timeline;
    }

    public String getDatetime() {
        return Datetime;
    }

    public void setDatetime(String datetime) {
        Datetime = datetime;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

}
