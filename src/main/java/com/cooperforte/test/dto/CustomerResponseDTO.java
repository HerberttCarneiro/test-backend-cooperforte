package com.cooperforte.test.dto;

import com.cooperforte.test.model.ApplicationUser;
import com.cooperforte.test.model.Contact;
import com.cooperforte.test.model.UserEmail;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CustomerResponseDTO {
    private Long id;
    private String name;
    private String cpf;
    private String cep;
    private String address;
    private String district;
    private String city;
    private String state;
    private List<UserEmail> emails;
    private List<Contact> contacts;
    private ApplicationUserResponseDTO createdBy;
    private ApplicationUserResponseDTO updatedBy;
    private LocalDateTime creationDate;
    private LocalDateTime updatedDate;
}
