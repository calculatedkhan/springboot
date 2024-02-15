package com.example.imdb;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class FilmStepDefs {

    private Film film;

    @Given("a film exists")
    public void getAFilm() {
        film = new Film();
    }

    @When("manager sets film id to 201")
    public void setFilmID(int id) {
        film.setfilmID(id);
    }

    @Then("film id will be 201")
    public void checkFilmID(int id) {
        assertEquals(id, film.getfilmID());
    }


}
