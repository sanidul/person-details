package com.propay.person.controllers;

import com.propay.person.entities.Contact;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/contact-management/contacts")
public class ContactController {

    @GetMapping
    public Response getAllContacts() {
        return Response.ok(Contact.listAll()).status(HttpStatus.OK.value()).build();
    }

    @PostMapping
    @Transactional
    public Response addContact(Contact contact) {
        Contact.persist(contact);
        return Response.ok(contact).status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/{id}")
    public Response getContact(@PathVariable("id") Long id) {
        Contact contact = Contact.findById(id);
        return contact != null ? Response.ok(contact).status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Response deleteContact(@PathVariable("id") Long id) {
        return Contact.deleteById(id) ? Response.ok().status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }
}
