package com.objectpartners.stormydata.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by mikeh on 6/6/17.
 */
@Entity
@Table(name="storm_info")
public class Storm {


    @Id
    private Long id;

    @Column(name="begin_timestamp")
    private ZonedDateTime beginTime;

    @Column(name="end_timestamp")
    private ZonedDateTime endTime;



    @Column
    private Long eventId;

    @Column
    private Long episodeId;


    @ManyToOne
    private State state;

    @ManyToOne
    private StormType stormType;

    @Column
    private String comments;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   public ZonedDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(ZonedDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(Long episodeId) {
        this.episodeId = episodeId;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public StormType getStormType() {
        return stormType;
    }

    public void setStormType(StormType stormType) {
        this.stormType = stormType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
