package com.propay.person.controllers;

import com.propay.person.entities.ElectronicDevice;
import com.propay.person.entities.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/device-management/electronic-devices")
public class ElectronicDeviceController {

    @GetMapping
    public Response getAllDevices() {
        return Response.ok(ElectronicDevice.listAll()).status(HttpStatus.OK.value()).build();
    }

    @PostMapping
    @Transactional
    public Response addDevice(ElectronicDevice device) {
        ElectronicDevice.persist(device);
        return Response.ok(device).status(HttpStatus.CREATED.value()).build();
    }

    @GetMapping("/{id}")
    public Response getPerson(@PathVariable("id") Long id) {
        ElectronicDevice electronicDevice = ElectronicDevice.findById(id);
        return electronicDevice != null ? Response.ok(electronicDevice).status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public Response deletePerson(@PathVariable("id") Long id) {
        return ElectronicDevice.deleteById(id) ? Response.ok().status(HttpStatus.OK.value()).build() : Response.status(HttpStatus.NOT_FOUND.value()).build();
    }

    @PutMapping("/assign")
    @Transactional
    public Response assignPerson(@RequestParam("deviceId") Long deviceId, @RequestParam("personId") Long personId) {
        ElectronicDevice electronicDevice = ElectronicDevice.findById(deviceId);
        Person person = Person.findById(personId);
        if (electronicDevice != null && person != null) {
            electronicDevice.setPerson(person);
            ElectronicDevice.persist(electronicDevice);
            return Response.ok().status(HttpStatus.OK.value()).build();
        }
        return Response.status(HttpStatus.NOT_FOUND.value()).build();
    }
}
