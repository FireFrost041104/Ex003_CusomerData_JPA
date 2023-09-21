package com.example.ex003_kundendaten_jpa_eichwald.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")
@NamedQueries({
        @NamedQuery(name = "Address.countAll", query = "SELECT COUNT(a) FROM Address a")})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String postalCode;
    private String city;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customers = new ArrayList<>();
}
