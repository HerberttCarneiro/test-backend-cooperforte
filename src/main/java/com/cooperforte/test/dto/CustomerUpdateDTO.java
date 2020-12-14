package com.cooperforte.test.dto;

import com.cooperforte.test.model.ApplicationUser;
import com.cooperforte.test.model.Contact;
import com.cooperforte.test.model.UserEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerUpdateDTO {
    @Id
    @NotNull
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String cpf;

    @NotEmpty
    private String cep;

    @NotEmpty
    private String address;

    @NotEmpty
    private String district;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @Valid
    @NotNull
    List<UserEmail> emails;

    @Valid
    @NotNull
    List<Contact> contacts;

    @JsonIgnore
    private  LocalDateTime creationDate;

    @JsonIgnore
    private  ApplicationUser createdBy;
}
