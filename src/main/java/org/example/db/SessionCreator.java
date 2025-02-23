package org.example.db;

import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class SessionCreator {

    private final SessionFactory sessionFactory;

    public SessionCreator() {
        this.sessionFactory = buildSessionFactory();
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    private SessionFactory buildSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));

            return new Configuration()
                    .setProperties(properties)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Country.class)
                    .addAnnotatedClass(Actor.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Film.class)
                    .addAnnotatedClass(FilmText.class)
                    .addAnnotatedClass(Language.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Inventory.class)
                    .addAnnotatedClass(Payment.class)
                    .addAnnotatedClass(Rental.class)
                    .addAnnotatedClass(Staff.class)
                    .addAnnotatedClass(Store.class)
                    .buildSessionFactory();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            System.out.println("Closing SessionFactory...");
            sessionFactory.close();
        }
    }
}