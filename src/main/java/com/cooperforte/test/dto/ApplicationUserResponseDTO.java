package com.cooperforte.test.dto;

import com.cooperforte.test.model.Contact;
import com.cooperforte.test.model.UserEmail;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ApplicationUserResponseDTO {
    private Long id;
    private String name;
}
