package com.example.app;

public class ListagemModel {

    String Id, Title, Loc, Desc, Obs, Sit;

    public ListagemModel()
    {

    }

    public ListagemModel(String id, String title, String loc, String desc, String sit, String obs) {
        Id = id;
        Title = title;
        Loc = loc;
        Desc = desc;
        Sit = sit;
        Obs = obs;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLoc() {
        return Loc;
    }

    public void setLoc(String loc) {
        Loc = loc;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }
    public String getObs() {
        return Obs;
    }

    public void setObs(String obs) {
        Obs = obs;
    }

    public String getSit() {
        return Sit;
    }

    public void setSit(String sit) {
        Sit = sit;
    }
}
