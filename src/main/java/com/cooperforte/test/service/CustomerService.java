package com.cooperforte.test.service;

import com.cooperforte.test.dto.CustomerPostRequestDTO;
import com.cooperforte.test.dto.CustomerResponseDTO;
import com.cooperforte.test.dto.CustomerUpdateDTO;
import com.cooperforte.test.exception.BadRequestException;
import com.cooperforte.test.model.ApplicationUser;
import com.cooperforte.test.model.Customer;
import com.cooperforte.test.repository.ApplicationUserRepository;
import com.cooperforte.test.repository.CustomerRepository;
import com.cooperforte.test.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public List<CustomerResponseDTO> list() {
        List<Customer> customers = customerRepository.findAll();
        return Mapper.mapList(customers, CustomerResponseDTO.class);
    }

    public CustomerResponseDTO findByIdOrThrowBadRequestException(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        return convertEntityToDto(customer);
    }

    @Transactional
    public void deleteById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        customerRepository.deleteById(id);
    }

    @Transactional
    public URI save(CustomerPostRequestDTO customerPostRequestDTO, UserDetails userDetails) {
        Customer customer = this.convertDtoToEntity(customerPostRequestDTO);
        ApplicationUser userLogged = applicationUserRepository.findByUsername(userDetails.getUsername());
        this.setCreationByAndDateCreated(customer, userLogged);
        customerRepository.save(customer);
        return URI.create(customer.getId().toString());
    }

    @Transactional
    public void update(Long id, CustomerUpdateDTO customerUpdateDTO, UserDetails userDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Customer not found"));
        ApplicationUser userLogged = applicationUserRepository.findByUsername(userDetails.getUsername());
        Customer customerUpdated = this.organizeCustomerChange(customerUpdateDTO, customer, userLogged);
        customerRepository.save(customerUpdated);
    }

    private Customer organizeCustomerChange(CustomerUpdateDTO customerUpdateDTO, Customer customer,
                                            ApplicationUser userLogged) {
        Customer customerUpdated = Mapper.mapper().map(customerUpdateDTO, Customer.class);
        customerUpdated.setId(customer.getId());
        customerUpdated.setCreationDate(customer.getCreationDate());
        customerUpdated.setCreatedBy(customer.getCreatedBy());
        this.setUpdatedByAndDateUpdated(customerUpdated, userLogged);
        return customerUpdated;
    }

    private Customer convertDtoToEntity(CustomerPostRequestDTO customerPostRequestDTO) {
        return Mapper.mapper().map(customerPostRequestDTO, Customer.class);
    }

    private CustomerResponseDTO convertEntityToDto(Customer customer) {
        return Mapper.mapper().map(customer, CustomerResponseDTO.class);
    }

    private void setCreationByAndDateCreated(Customer customer, ApplicationUser userLogged) {
        customer.setCreationDate(LocalDateTime.now());
        customer.setCreatedBy(userLogged);
    }

    private void setUpdatedByAndDateUpdated(Customer customer, ApplicationUser userLogged) {
        customer.setUpdatedDate(LocalDateTime.now());
        customer.setUpdatedBy(userLogged);
    }
}
