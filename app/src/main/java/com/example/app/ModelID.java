package com.example.app;

public class ModelID {

    String id, title, loc, desc;

    public ModelID()
    {

    }

    public ModelID(String id, String title, String loc, String desc) {
        this.id = id;
        this.title = title;
        this.loc = loc;
        this.desc = desc;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLoc() {
        return loc;
    }


    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
