package com.example.lola;

public class Movie {

    private String name;
    private String director;
    private int imgid;

    public Movie(String name, String director,int id) {
        this.name = name;
        this.director = director;
        this.imgid=id;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
