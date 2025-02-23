package org.example;

import org.example.entity.*;
import org.example.service.*;
import org.example.utils.DIContainer;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) {

        RentalService rentalService = DIContainer.get(RentalService.class);
        FilmService filmService = DIContainer.get(FilmService.class);
        CustomerService customerService = DIContainer.get(CustomerService.class);
        StoreService storeService = DIContainer.get(StoreService.class);
        ActorService actorService = DIContainer.get(ActorService.class);
        CategoryService categoryService = DIContainer.get(CategoryService.class);
        LanguageService languageService = DIContainer.get(LanguageService.class);
        CountryService countryService = DIContainer.get(CountryService.class);

// test renting a film
        rentalService.rentFilm(1L, 1L, 1L);
        System.out.println("-----------------------------------------------------------------------------");

// test returning a rented film
        rentalService.returnRentedFilm(1L);
        System.out.println("-----------------------------------------------------------------------------");

// test creating a new film with dependencies
        FilmText filmText = FilmText.builder()
                .title("Test Film")
                .description("Test Description")
                .build();

        List<Actor> actors = List.of(actorService.findById(1L), actorService.findById(2L));
        List<Category> categories = List.of(categoryService.findById(1L), categoryService.findById(2L));
        Language language = languageService.findById(1L);
        Store store = storeService.findById(1L);


        Film film = Film.builder()
                .releaseYear(Year.now())
                .length(128)
                .rentalDuration(3)
                .rentalRate(2.5)
                .replacementCost(10.99)
                .rating(Rating.PG)
                .specialFeatures(Set.of("Trailers", "Deleted Scenes"))
                .build();

        filmService.createNewFilm(film, filmText, actors, language, categories, store);
        System.out.println("-----------------------------------------------------------------------------");

// test creating a new customer with dependencies
        Country country = countryService.findById(1L);
        City city = country.getCities().get(0);
        Address address = new Address();
        address.setAddress("123 Main St");
        address.setDistrict("Manhattan");
        address.setPostalCode("10001");
        address.setPhone("123456789");
        address.setCity(city);
        address.setLastUpdate(LocalDateTime.now());
        Store store1 = storeService.findById(1L);

        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setCreatedDate(LocalDateTime.now());
        customer.setLastUpdate(LocalDateTime.now());
        customer.setAddress(address);
        customer.setIsActive(true);
        customer.setStore(store1);
        customerService.saveWithDependencies(customer);
    }
}