package org.example.repository;

import org.example.entity.Payment;

public class PaymentRepository extends BaseRepository<Payment> {
    public PaymentRepository() {
        super(Payment.class);
    }
}
