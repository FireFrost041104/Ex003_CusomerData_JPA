package com.example.ex003_kundendaten_jpa_eichwald;

import com.example.ex003_kundendaten_jpa_eichwald.pojos.Address;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Country;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;

public class Main {

        public static void main(String[] args) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_KundendatenDB");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();

            try {
                Country country = new Country(1, "Austria", "AT");
                Address address = new Address(1, 1, "Grazerstrasse", "8000", "Leibnitz", country);
                Customer customer = new Customer(1, "Alex", "Eichwald", "Male", true, "asdf", LocalDate.of(2023, 11, 20), address);


                Country country1 = new Country(2, "Germany", "DE");
                Address address1 = new Address(2, 2, "Grazerstrasse 202", "5000", "Kaindorf", country);
                Customer customer1 = new Customer(2, "Alex", "Eichwald", "Male", true, "asdf@gmail.com", LocalDate.of(2023, 11, 20), address);

                em.persist(country);
                em.persist(address);
                em.persist(customer);

                em.persist(country1);
                em.persist(address1);
                em.persist(customer1);

                em.getTransaction().commit();

                Query query = em.createQuery("SELECT c FROM Country c", Country.class);
                query.getResultList().forEach(System.out::println);
            } catch (Exception e) {
                em.getTransaction().rollback();
                e.printStackTrace();
            } finally {
                em.close();
                emf.close();
            }
        }
}

