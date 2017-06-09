package com.objectpartners.stormydata.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mikeh on 6/6/17.
 */
@Entity
@Table(name="storm_info")
public class Storm {


    @Id
    private Long id;

    @Column(name="begin_timestamp")
    private LocalDateTime beginTime;

    @Column(name="end_timestamp")
    private LocalDateTime endTime;



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

   public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
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
