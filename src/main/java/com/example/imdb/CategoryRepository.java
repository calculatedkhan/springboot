package com.example.imdb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import jakarta.persistence.*;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
    @Query(value = "SELECT * FROM film f", nativeQuery = true)
    public List<Film> getFilmsInCategory();

}
