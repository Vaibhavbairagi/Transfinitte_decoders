package com.example.transfinitte_decoders.pojos;

public class Docs {
    String profile;
    String name;
    String post;
    boolean available;

    public Docs() {
    }

    public Docs(String profile, String name, String post, boolean available) {
        this.profile = profile;
        this.name = name;
        this.post = post;
        this.available = available;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
