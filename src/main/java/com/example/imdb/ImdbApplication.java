package com.example.imdb;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;

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
	private CategoryRepository categoryRepo;

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private StaffRepository staffRepo;

	@Autowired
	private StoreRepository storeRepo;

	public ImdbApplication(ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository categoryRepo, CustomerRepository customerRepo, AddressRepository addressRepo, StoreRepository storeRepo, StaffRepository staffRepo) {
		this.actorRepo = actorRepo;
		this.filmRepo = filmRepo;
		this.categoryRepo = categoryRepo;
		this.customerRepo = customerRepo;
		this.addressRepo = addressRepo;
		this.storeRepo = storeRepo;
		this.staffRepo = staffRepo;
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

	@GetMapping("categoryByID/{id}")
	public Set<Film> getFilmsInCategory(@PathVariable("id") Integer categoryID) {
		Category category = categoryRepo.findById(categoryID).orElseThrow(() -> new ResourceAccessException("Category Not Found"));
		return (category.getFilms());
	}

	@GetMapping("getFilmsInCategoryByApplication/{category_name}")
	public Set<Film> getFilmsInCategoryByApplication(@PathVariable("category_name") String categoryName) {
		int chosenCategoryID=0;
		List<Category> categories = new ArrayList<>();
		categories = categoryRepo.findAll();
		for (Category category : categories) {
			if (categoryName.equals(category.getCategoryName())) {
				chosenCategoryID = category.getCategoryID();
				break;
			}
		}
		Category category = categoryRepo.findById(chosenCategoryID).orElseThrow(() -> new ResourceAccessException("Category Not Found"));
		return (category.getFilms());
	}

	@GetMapping("getFilmsInCategoryByQuery/{category}")
	public List<Film> getFilmsInCategoryByQuery(@PathVariable("category") String category) {
		return (filmRepo.getFilmsInCategory(category));
	}

//	@JsonView(Views.Public.class)
	@GetMapping("getAllCustomer")
	public Iterable<Customer> getAllCustomer() {
		return customerRepo.findAll();
	}

	@GetMapping("getAllAddress")
	public Iterable<Address> getAllAddress() {
		return addressRepo.findAll();
	}

	@GetMapping("getAddressByName/{first_name}_{last_name}")
	public Address getAddressByName(@PathVariable("first_name") String firstName, @PathVariable("last_name") String lastName) {
		return addressRepo.findAddressByCustomerFirstNameAndLastName(firstName,lastName);
	}

	@PostMapping("addAddress")
	public Address addAddress(@RequestBody Address address) {
		Address newAddress = new Address();
		newAddress.setAddress(address.getAddress());
		newAddress.setDistrict(address.getDistrict());
		newAddress.setPostalCode(address.getPostalCode());
		newAddress.setCityID(address.getCityID());
		newAddress.setPhone(address.getPhone());
		Address addressSaved = addressRepo.save(newAddress);
		return addressSaved;
	}

	@PostMapping("addCustomer")
	public Customer addCustomer(@RequestBody Customer customer) {
		Customer newCustomer = new Customer();
//		newCustomer.setStoreID(customer.getStoreID());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setAddress(customer.getAddress());
		newCustomer.setActive(customer.getActive());
		Customer customerSaved = customerRepo.save(newCustomer);
		return customerSaved;
	}

	@PutMapping("alterCustomerByEmail/{email}")
	public Customer alterCustomerByName(@PathVariable("email") String email) {

	}

//	Potential sakila mac issues
//	@GetMapping("getAllStore")
//	public Iterable<Store> getAllStore() {
//		return storeRepo.findAll();
//	}
//
//	@GetMapping("getAllStaff")
//	public Iterable<Staff> getAllStaff() {
//		return staffRepo.findAll();
//	}
}
