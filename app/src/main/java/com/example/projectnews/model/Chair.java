package com.example.projectnews.model;

public class Chair {
    private int Id;
    private String Name;
    private String Room;
    private String Status;

    public Chair() {
    }

    public Chair(int id, String name, String room, String status) {
        Id = id;
        Name = name;
        Room = room;
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
