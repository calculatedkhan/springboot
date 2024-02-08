package com.example.imdb;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;

    @Column(name="name")
    private String categoryName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name="film_category",
            joinColumns = {@JoinColumn(name="category_id", referencedColumnName = "category_id")},
            inverseJoinColumns = {@JoinColumn(name="film_id", referencedColumnName = "film_id")}
    )
    Set<Film> films = new HashSet<>();

    public Set<Film> getFilms() {
        return this.films;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
