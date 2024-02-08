package com.example.imdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class ImdbApplication {

	@Autowired
	private ActorRepository actorRepo;

	@Autowired
	private FilmRepository filmRepo;

	@Autowired
	private CategoryRepository categoryRepository;

	public ImdbApplication(ActorRepository actorRepo, FilmRepository filmRepo) {
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(ImdbApplication.class, args);
	}

	@GetMapping("/allActors")
	public Iterable<Actor> getAllActors() {
		return actorRepo.findAll();
	}

	@GetMapping("actor/{id}")
	public Actor getActorByID(@PathVariable("id") int actorID) {
		return actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor Not Found"));
	}

	@PostMapping("addActor")
	public Actor addActor(@RequestBody Actor actor) {
		Actor newActor = new Actor();
		newActor.setFirstName(actor.getFirstName());
		newActor.setLastName(actor.getLastName());
		Actor actorSaved = actorRepo.save(newActor);
		return actorSaved;
	}

	@PutMapping("alterActor/{id}")
	public Actor alterActor(@PathVariable("id") int actorID, @RequestBody Actor actor) {
		Actor updateActor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor Not Found"));
		updateActor.setFirstName(actor.getFirstName());
		updateActor.setLastName(actor.getLastName());
		Actor actorSaved = actorRepo.save(updateActor);
		return actorSaved;
	}

	@DeleteMapping("deleteActor/{id}") //need to add new actor, to show delete works
	public String deleteActor(@PathVariable("id") Integer actorID) {
		Actor deletedActor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor Not Found"));
		actorRepo.deleteById(actorID);
		return ("Actor Deleted!");

	}

	@GetMapping("allFilms")
	public Iterable<Film> getAllFilms() {
		return filmRepo.findAll();
	}

	@GetMapping("filmsWithActor/{id}")
	public Set<Film> getActorFilms(@PathVariable("id") Integer actorID) {
		Actor actor = actorRepo.findById(actorID).orElseThrow(() -> new ResourceAccessException("Actor Not Found"));
		Set<Film> actorFilms = actor.getFilms();
		return actorFilms;
	}

	@GetMapping("allActorsInFilm/{id}")
	public Set<Actor> getActorsinFilm(@PathVariable("id") Integer filmID) {
		Film film = filmRepo.findById(filmID).orElseThrow(() -> new ResourceAccessException("Film Not Found"));
		Set<Actor> actorsInFilm = film.getActors();
		return actorsInFilm;
	}

	@GetMapping("category/{id}")
	public Set<Film> getFilmsInCategory(@PathVariable("id") Integer categoryID) {
		Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new ResourceAccessException("Category Not Found"));
		return (category.getFilms());
	}

	@GetMapping("category/{category_name")
	public Set<Film> getFilmsInCategoryByName(@PathVariable("category_name") String categoryName) {
		int chosenCategoryID=0;
		List<Category> categories = new ArrayList<>();
		categories = categoryRepository.findAll();
		for (Category category : categories) {
			if (categoryName.equals(category.getCategoryName())) {
				chosenCategoryID = category.getCategoryID();
				break;
			}
		}
		Category category = categoryRepository.findById(chosenCategoryID).orElseThrow(() -> new ResourceAccessException("Category Not Found"));
		return (category.getFilms());
	}

}
