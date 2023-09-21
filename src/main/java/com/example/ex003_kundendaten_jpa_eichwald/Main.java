package com.example.ex003_kundendaten_jpa_eichwald;

import com.example.ex003_kundendaten_jpa_eichwald.pojos.Address;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Country;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
/*
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
 */

    private EntityManagerFactory emf;
    private EntityManager em;

    public Main() {
        emf = Persistence.createEntityManagerFactory("customerdb");
        em = emf.createEntityManager();
    }

    public void open() {
        em.getTransaction().begin();
    }

    public void close() {
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public void importXML(String xmlFilePath) {
        // Implement XML import logic here
        // Parse XML, create Pojo objects, and persist them
    }

    public void importJSON(String jsonFilePath) {
        // Implement JSON import logic here
        // Parse JSON, create Pojo objects, and persist them
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Import XML");
            System.out.println("2. Import JSON");
            System.out.println("3. Query: Countries imported");
            System.out.println("4. Query: Addresses imported");
            System.out.println("5. Query: Customers imported");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter XML file path: ");
                    String xmlFilePath = scanner.nextLine();
                    open();
                    importXML(xmlFilePath);
                    close();
                    break;
                case 2:
                    System.out.print("Enter JSON file path: ");
                    String jsonFilePath = scanner.nextLine();
                    open();
                    importJSON(jsonFilePath);
                    close();
                    break;
                case 3:
                    // Implement query for countries imported
                    break;
                case 4:
                    // Implement query for addresses imported
                    break;
                case 5:
                    // Implement query for customers imported
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Main dataImport = new Main();
        dataImport.menu();
    }
}

