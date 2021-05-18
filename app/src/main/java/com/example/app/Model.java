package com.example.app;

public class Model {

    String id, title, loc, desc, obs, sit;

   public Model()
   {

   }

    public Model(String id, String title, String loc, String desc, String obs, String sit) {
        this.id = id;
        this.title = title;
        this.loc = loc;
        this.desc = desc;
        this.obs = obs;
        this.sit = sit;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getSit() {
        return sit;
    }

    public void setSit(String obs) {
        this.sit = sit;
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
