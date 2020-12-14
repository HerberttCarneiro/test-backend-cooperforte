package com.cooperforte.test.resource;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
@Log4j2
public class UserResource {
    @GetMapping(value = "admin")
    @PreAuthorize("hasRole('ADMIN')")
    String list() {
        log.info("ADMIN");
        return "ADMIN";
    }

    @GetMapping(value = "user")
    @PreAuthorize("hasRole('USER')")
    String list2() {
        log.info("USER");
        return "USER";
    }

    @GetMapping(value = "by-id/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> findByIdAuthenticationPrincipal(@PathVariable long id,
                                                           @AuthenticationPrincipal UserDetails userDetails) {

        log.info(userDetails);
        return ResponseEntity.ok("teste");
    }
}
