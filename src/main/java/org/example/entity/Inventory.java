package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Long inventoryId;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "inventory")
    private List<Rental> rentals;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != Hibernate.getClass(o)) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(inventoryId, inventory.getInventoryId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(inventoryId);
    }
}
