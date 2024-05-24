package com.koushal.RestfulAPI.restfullapi.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;


@Entity(name = "user_details")
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private LocalDate birthday;
    @OneToMany(mappedBy = "user")
    //here we are telling spring to map this attribute to the 'user' attribute present int Post class.
    //and relation is one to many means one user can have many post.
    private List<Post> posts;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User(int id, String name, LocalDate birthday, List<Post> posts) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public User(int id, String name, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
