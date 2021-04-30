package com.ttweb55.recipeapp.models;

import javax.persistence.*;

@Entity
@Table(name = "avatars")
public class Avatar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String avatarid;

    @Column(nullable = false)
    private String url;

    @OneToOne(mappedBy = "avatar")
    private User user;

    public Avatar() {
    }

    public Avatar(String avatarid, String url) {
        this.avatarid = avatarid;
        this.url = url;
    }

    public String getAvatarid() {
        return avatarid;
    }

    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
