package com.example.projectnews.model;

public class Room {
    private int Id;
    private String Movie;
    private String Timeline;
    private String Datetime;
    private String Name;
    private String Venue;

    public Room() {
    }

    public Room(int id, String movie, String timeline, String datetime, String name, String venue) {
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

    public String getMovie() {
        return Movie;
    }

    public void setMovie(String movie) {
        Movie = movie;
    }

    public String getTimeline() {
        return Timeline;
    }

    public void setTimeline(String timeline) {
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
