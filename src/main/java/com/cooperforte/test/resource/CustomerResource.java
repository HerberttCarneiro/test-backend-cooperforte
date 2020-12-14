package com.cooperforte.test.resource;


import com.cooperforte.test.dto.CustomerPostRequestDTO;
import com.cooperforte.test.dto.CustomerResponseDTO;
import com.cooperforte.test.dto.CustomerUpdateDTO;
import com.cooperforte.test.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "customers")
@Log4j2
public class CustomerResource {
    private final CustomerService customerService;

    @GetMapping()
    public ResponseEntity<List<CustomerResponseDTO>> findAll() {
        return ResponseEntity.ok(customerService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.findByIdOrThrowBadRequestException(id));
    }

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody @Valid CustomerPostRequestDTO customerPostRequestDTO,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        URI uri = customerService.save(customerPostRequestDTO, userDetails);
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,
                                         @RequestBody @Valid CustomerUpdateDTO customerUpdateDTO,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        customerService.update(id, customerUpdateDTO, userDetails);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
