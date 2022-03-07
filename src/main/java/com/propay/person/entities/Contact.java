package com.propay.person.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Contact extends PanacheEntity {

    @NotEmpty
    private String name;
    private String email;
    private String mobile;

    @ManyToMany(mappedBy = "contacts")
    @JsonIgnore
    private List<Person> personList;

    public Contact() {
    }

    public Contact(String name, String email, String mobile, List<Person> personList) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.personList = personList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
