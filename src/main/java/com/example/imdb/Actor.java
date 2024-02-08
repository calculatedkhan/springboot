package com.example.imdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @Column(name="actor_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int actorID;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name="film_actor",
            joinColumns = {@JoinColumn(name="actor_id", referencedColumnName = "actor_id")},
            inverseJoinColumns = {@JoinColumn(name="film_id", referencedColumnName = "film_id")}
    )
    Set<Film> films = new HashSet<>();


    public Set<Film> getFilms() {
        return this.films;
    }

    public int getActorID() {
        return actorID;
    }

    public void setActorID(int actorID) {
        this.actorID = actorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
