package org.example.service;

import lombok.AllArgsConstructor;
import org.example.entity.Payment;
import org.example.repository.PaymentRepository;
import org.example.utils.TransactionManager;

import java.util.List;

@AllArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final TransactionManager txManager;

    public Payment findById(Long id) {
        return txManager.execute(session -> paymentRepository.findById(session, id));
    }

    public List<Payment> findAll() {
        return txManager.execute(paymentRepository::findAll);
    }

    public void delete(Payment payment) {
        txManager.execute(session -> {
            paymentRepository.delete(session, payment);
            return null;
        });
    }

    public void save(Payment payment) {
        txManager.execute(session -> {
            paymentRepository.save(session, payment);
            return null;
        });
    }

    public void update(Payment payment) {
        txManager.execute(session -> {
            paymentRepository.update(session, payment);
            return null;
        });
    }
}
