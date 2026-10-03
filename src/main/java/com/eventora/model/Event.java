package com.eventora.model;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Event {
    private int eventId, capacity, registeredCount;
    private String title, description, category, venue;
    private Date eventDate;
    private Time eventTime;
    private Timestamp createdAt;

    public Event() {}

    public int getEventId() { return eventId; }
    public void setEventId(int v) { eventId=v; }
    public String getTitle() { return title; }
    public void setTitle(String v) { title=v; }
    public String getDescription() { return description; }
    public void setDescription(String v) { description=v; }
    public String getCategory() { return category; }
    public void setCategory(String v) { category=v; }
    public Date getEventDate() { return eventDate; }
    public void setEventDate(Date v) { eventDate=v; }
    public Time getEventTime() { return eventTime; }
    public void setEventTime(Time v) { eventTime=v; }
    public String getVenue() { return venue; }
    public void setVenue(String v) { venue=v; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int v) { capacity=v; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp v) { createdAt=v; }
    public int getRegisteredCount() { return registeredCount; }
    public void setRegisteredCount(int v) { registeredCount=v; }

    public boolean hasSlotsAvailable() { return registeredCount < capacity; }
    public int getSlotsLeft() { return capacity - registeredCount; }
    public int getFillPercent() {
        if (capacity==0) return 0;
        return (int) Math.min(100, (registeredCount*100.0)/capacity);
    }
}
