package com.example.imdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film,Integer> {

    @Query(value = "SELECT f.* FROM film f JOIN film_category fc ON f.film_id = fc.film_id JOIN category c ON fc.category_id = c.category_id WHERE c.name = :categoryName", nativeQuery = true)
    public List<Film> getFilmsInCategory(@Param("categoryName") String categoryName);

}


