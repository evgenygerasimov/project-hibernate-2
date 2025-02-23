package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.*;

import org.example.repository.PaymentRepository;
import org.example.repository.RentalRepository;
import org.example.utils.TransactionManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final PaymentRepository paymentRepository;
    private final TransactionManager txManager;

    public void returnRentedFilm(Long customerId) {
        txManager.execute(session -> {
            Customer customer = session.get(Customer.class, customerId);
            List<Rental> rentals = customer.getRentals();
            for (Rental rental : rentals) {
                if (rental.getReturnDate() == null) {
                    Inventory inventory = rental.getInventory();
                    Film film = inventory.getFilm();
                    Payment payment = rental.getPayment();

                    long daysBetween = ChronoUnit.DAYS.between(rental.getRentalDate(), LocalDateTime.now());
                    if (daysBetween <= film.getRentalDuration()) {
                        rental.setReturnDate(LocalDateTime.now());
                    } else {
                        long overDays = daysBetween - film.getRentalDuration();
                        double rentalAmount = film.getRentalRate() + overDays * (film.getRentalRate() / film.getRentalDuration());
                        rental.setReturnDate(LocalDateTime.now());
                        payment.setAmount(rentalAmount);
                        payment.setPaymentDate(LocalDateTime.now());
                    }

                    rentalRepository.update(session, rental);
                    paymentRepository.update(session, payment);
                    System.out.println("Rental film " + film.getTitle() + " has been returned.");
                }
            }
            return null;
        });
    }

    public void rentFilm(Long customerId, Long filmId, Long storeId) {
        txManager.execute(session -> {
            Customer customer = session.get(Customer.class, customerId);
            Film film = session.get(Film.class, filmId);
            Store store = session.get(Store.class, storeId);
            List<Inventory> inventoriesInStore = store.getInventories();
            List<Inventory> inventoriesOfFilm = film.getInventories();
            Inventory availableInventory = null;
            for (Inventory inventory : inventoriesOfFilm) {
                if (inventoriesInStore.contains(inventory)) {
                    List<Rental> rentals = inventory.getRentals();
                    if (rentals.isEmpty() || rentals.get(rentals.size() - 1).getReturnDate() != null) {
                        availableInventory = inventory;
                        break;
                    }
                }
            }
            if (availableInventory == null) {
                throw new RuntimeException("No available inventory for renting.");
            }

            Payment payment = Payment.builder()
                    .customer(customer)
                    .staff(store.getStaff())
                    .amount(film.getRentalRate())
                    .paymentDate(LocalDateTime.now())
                    .build();

            Rental newRental = Rental.builder()
                    .customer(customer)
                    .staff(store.getStaff())
                    .inventory(availableInventory)
                    .rentalDate(LocalDateTime.now())
                    .payment(payment)
                    .build();

            payment.setRental(newRental);
            rentalRepository.save(session, newRental);
            paymentRepository.save(session, payment);
            System.out.println("Customer has rented film: " + film.getTitle());
            return null;
        });
    }

    public Rental findById(Long id) {
        return txManager.execute(session -> rentalRepository.findById(session, id));
    }

    public List<Rental> findAll() {
        return txManager.execute(rentalRepository::findAll);
    }

    public void delete(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.delete(session, rental);
            return null;
        });
    }

    public void save(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.save(session, rental);
            return null;
        });
    }

    public void update(Rental rental) {
        txManager.execute(session -> {
            rentalRepository.update(session, rental);
            return null;
        });
    }
}