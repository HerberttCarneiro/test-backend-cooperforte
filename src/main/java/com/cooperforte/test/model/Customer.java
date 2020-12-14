package com.cooperforte.test.model;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String name;

    @NotEmpty
    @Column(nullable = false)
    private String cpf;

    @NotEmpty
    @Column(nullable = false)
    private String cep;

    @NotEmpty
    @Column(nullable = false)
    private String address;

    @NotEmpty
    @Column(nullable = false)
    private String district;

    @NotEmpty
    @Column(nullable = false)
    private String city;

    @NotEmpty
    @Column(nullable = false)
    private String state;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<UserEmail> emails;

    @NotNull
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Contact> contacts;

    @Column(nullable = false)
    LocalDateTime creationDate;

    LocalDateTime updatedDate;

    @OneToOne
    ApplicationUser createdBy;

    @OneToOne
    ApplicationUser updatedBy;
}
