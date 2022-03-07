package com.propay.person.controllers;

import com.propay.person.entities.Contact;
import com.propay.person.entities.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/people-management/persons")
public class PersonController {

    @GetMapping
    public Response getAllPersons() {
        return Response.ok(Person.listAll()).status(HttpStatus.OK.value()).build();
    }

    @PostMapping
    @Transactional
    public Response addPerson(Person person) {
        Person.persist(person);
        return Response.ok(person).status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/{id}")
    public Response getPerson(@PathVariable("id") Long id) {
        Person person = Person.findById(id);
        return person != null ? Response.ok(person).status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Response deletePerson(@PathVariable("id") Long id) {
        return Person.deleteById(id) ? Response.ok().status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @PutMapping("/assign")
    @Transactional
    public Response assignContact(@RequestParam("personId") Long personId, @RequestParam("contactId") Long contactId) {
        Person person = Person.findById(personId);
        Contact contact = Contact.findById(contactId);
        if (contact != null && person != null) {
            person.addContact(contact);
            Person.persist(person);
            return Response.ok().status(HttpStatus.OK.value()).build();
        }
        return Response.status(HttpStatus.NOT_FOUND.value()).build();
    }
}
