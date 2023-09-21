package com.example.ex003_kundendaten_jpa_eichwald;

import com.example.ex003_kundendaten_jpa_eichwald.pojos.Address;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Country;
import com.example.ex003_kundendaten_jpa_eichwald.pojos.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

import java.io.File;
import java.io.IOException;
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
        emf = Persistence.createEntityManagerFactory("PU_KundendatenDB");
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
    }

    public void importJSON(String jsonFilePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Customer[] customers = objectMapper.readValue(new File(jsonFilePath), Customer[].class);

            for (Customer customer : customers) {
                open(); // Transaktion öffnen
                em.persist(customer); // Kunden persistieren
                close(); // Transaktion schließen
            }

            System.out.println("JSON-Daten wurden erfolgreich importiert.");
        } catch (IOException e) {
            System.err.println("Fehler beim Importieren der JSON-Daten: " + e.getMessage());
        }
    }

    public long queryCountriesImported() {
        open(); // Transaktion öffnen

        // Verwenden Sie ein NamedQuery, um die Anzahl der importierten Länder abzurufen
        TypedQuery<Long> query = em.createNamedQuery("Country.countAll", Long.class);
        long countriesImported = query.getSingleResult();

        close(); // Transaktion schließen

        return countriesImported;
    }

    public long queryAddressesImported() {
        open(); // Transaktion öffnen

        // Verwenden Sie ein NamedQuery, um die Anzahl der importierten Adressen abzurufen
        TypedQuery<Long> query = em.createNamedQuery("Address.countAll", Long.class);
        long addressesImported = query.getSingleResult();

        close(); // Transaktion schließen

        return addressesImported;
    }

    public long queryCustomersImported() {
        open(); // Transaktion öffnen

        // Verwenden Sie ein NamedQuery, um die Anzahl der importierten Kunden abzurufen
        TypedQuery<Long> query = em.createNamedQuery("Customer.countAll", Long.class);
        long customersImported = query.getSingleResult();

        close(); // Transaktion schließen

        return customersImported;
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
                    long countriesImported = queryCountriesImported();
                    System.out.println("Countries imported: " + countriesImported);
                    break;
                case 4:
                    long addressesImported = queryAddressesImported();
                    System.out.println("Addresses imported: " + addressesImported);
                    break;
                case 5:
                    long customersImported = queryCustomersImported();
                    System.out.println("Customers imported: " + customersImported);
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

