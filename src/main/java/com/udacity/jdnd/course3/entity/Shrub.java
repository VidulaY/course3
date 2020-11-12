package com.udacity.jdnd.course3.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity

public class Shrub extends Plant{
    private long height;
    private long width;

    public Shrub(long height, long width) {
        this.height = height;
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }
}
