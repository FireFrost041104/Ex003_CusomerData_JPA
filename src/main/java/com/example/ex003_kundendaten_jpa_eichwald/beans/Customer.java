package com.example.ex003_kundendaten_jpa_eichwald.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private Boolean active;
    private String email;
    private LocalDate since;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
