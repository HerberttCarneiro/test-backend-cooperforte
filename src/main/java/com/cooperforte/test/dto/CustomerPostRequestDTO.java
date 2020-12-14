package com.cooperforte.test.dto;

import com.cooperforte.test.model.Contact;
import com.cooperforte.test.model.UserEmail;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class CustomerPostRequestDTO {
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
}
