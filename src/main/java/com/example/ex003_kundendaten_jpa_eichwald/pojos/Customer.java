package com.example.ex003_kundendaten_jpa_eichwald.pojos;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue
    long customer_id;

    String firstname;
    String lastname;
    String gender;
    Boolean active;
    String email;
    LocalDate since;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    Address address;
}
