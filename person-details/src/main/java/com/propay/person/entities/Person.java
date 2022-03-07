package com.propay.person.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person extends PanacheEntity {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    private String address;
    private String designation;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "person_contact", joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "contact_id")})
    private Set<Contact> contacts = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST)
    private Set<ElectronicDevice> electronicDevices = new HashSet<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String address, String designation, Set<Contact> contacts, Set<ElectronicDevice> electronicDevices) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.designation = designation;
        this.contacts = contacts;
        this.electronicDevices = electronicDevices;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public Set<ElectronicDevice> getElectronicDevices() {
        return electronicDevices;
    }

    public void setElectronicDevices(Set<ElectronicDevice> electronicDevices) {
        this.electronicDevices = electronicDevices;
    }

    public void addContact(Contact contact) {
        this.contacts.add(contact);
    }
}
