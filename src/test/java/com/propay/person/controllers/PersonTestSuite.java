package com.propay.person.controllers;

import com.propay.person.entities.Contact;
import com.propay.person.entities.ElectronicDevice;
import com.propay.person.entities.Person;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(21)
public class PersonTestSuite {

    @Test
    @Order(1)
    public void testAddPerson() {
        given()
                .contentType("application/json")
                .body(new Person("Lak", "Mal", "Colombo", "TL", null, null))
                .when()
                .post("/people-management/persons")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @Order(2)
    public void testAddContact() {
        given()
                .contentType("application/json")
                .body(new Contact("Gibbs", "Gibbs@gmail.com", "+94226558", null))
                .when()
                .post("/contact-management/contacts")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test()
    @Order(3)
    public void testAddDevice() {
        given()
                .contentType("application/json")
                .body(new ElectronicDevice("TV", "Samsung", "VT0085N99", null))
                .when()
                .post("/device-management/electronic-devices")
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    @Order(4)
    public void assignContactTest() {
        given()
                .contentType("application/json")
                .when()
                .put("/people-management/persons/assign?personId=1&contactId=2")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(5)
    public void assignPersonTest() {
        given()
                .contentType("application/json")
                .when()
                .put("/device-management/electronic-devices/assign?deviceId=3&personId=1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Order(6)
    public void testGetPerson() {
        given()
                .contentType("application/json")
                .when()
                .get("/people-management/persons")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$.size()", is(1),
                        "[0].firstName", is("Lak"),
                        "[0].contacts[0].name", is("Gibbs"),
                        "[0].electronicDevices[0].name", is("TV"));
    }

    @Test
    @Order(7)
    public void testGetDevice() {
        given()
                .contentType("application/json")
                .when()
                .get("/device-management/electronic-devices")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$.size()", is(1),
                        "[0].name", is("TV"));
    }

    @Test
    @Order(8)
    public void testDeleteDevice() {
        given()
                .contentType("application/json")
                .when()
                .delete("/device-management/electronic-devices/3")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Making sure the Item deleted properly.
     * calling a Get should return NOT FOUND 404
     */
    @Test
    @Order(9)
    public void verifyDeleteWithGet() {
        given()
                .contentType("application/json")
                .when()
                .get("/device-management/electronic-devices/3")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(10)
    public void assignDeviceNotFoundTest() {
        given()
                .contentType("application/json")
                .when()
                .put("/device-management/electronic-devices/assign?deviceId=3&personId=1")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(11)
    public void testDeletePerson() {
        given()
                .contentType("application/json")
                .when()
                .delete("/people-management/persons/1")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    /**
     * Making sure the Item deleted properly.
     * calling a Get should return NOT FOUND 404
     */
    @Test
    @Order(12)
    public void verifyPersonDeleteWithGet() {
        given()
                .contentType("application/json")
                .when()
                .get("/people-management/persons/1")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(13)
    public void assignContactNotFoundTest() {
        given()
                .contentType("application/json")
                .when()
                .put("/people-management/persons/assign?personId=1&contactId=2")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    @Order(14)
    public void testGetContact() {
        given()
                .contentType("application/json")
                .when()
                .get("/contact-management/contacts")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("$.size()", is(1),
                        "[0].name", is("Gibbs"));
    }

    @Test
    @Order(15)
    public void testDeleteContact() {
        given()
                .contentType("application/json")
                .when()
                .delete("/contact-management/contacts/2")
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    /**
     * Making sure the Item deleted properly.
     * calling a Get should return NOT FOUND 404
     */
    @Test
    @Order(16)
    public void verifyContactDeleteWithGet() {
        given()
                .contentType("application/json")
                .when()
                .get("/contact-management/contacts/2")
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

}
