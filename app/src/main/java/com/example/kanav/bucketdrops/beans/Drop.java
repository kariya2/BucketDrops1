package com.example.kanav.bucketdrops.beans;

import io.realm.RealmObject;

/**
 * Created by Kanav on 6/22/16.
 */
public class Drop extends RealmObject {

    public Drop() {
    }

    public Drop(long added, boolean completed, String what, long when) {
        this.added = added;
        this.completed = completed;
        this.what = what;
        this.when = when;
    }

    private String what;
    private long added;
    private long when;
    private boolean completed;

    public long getAdded() {
        return added;
    }

    public void setAdded(long added) {
        this.added = added;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }
}
