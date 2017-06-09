package com.objectpartners.stormydata.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by mikeh on 6/6/17.
 */
@Entity
public class StormType {

    @Id
    private Long id;

    @Column
    private  String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
