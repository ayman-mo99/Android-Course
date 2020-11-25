package com.example.myapplication;

public class Todos {
    String title;
    String dis;
    int photo;
    boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Todos(String title , String dis ,boolean done) {
        this.title=title;
        this.dis=dis;
        this.photo =photo;
        this.done=done;
    }
    public Todos() {
        this.title=title;
        this.dis=dis;
        this.photo =photo;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }


}
