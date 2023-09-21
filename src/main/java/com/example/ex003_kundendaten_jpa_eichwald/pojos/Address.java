package com.example.ex003_kundendaten_jpa_eichwald.pojos;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Address {

    @Id
    @GeneratedValue
    long address_id;

    @NonNull
    int street_number;

    String street_name;

    String postal_code;
    String city;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    Country country;
}
