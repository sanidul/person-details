# Person-details Project

This application provides facility to Create/Delete/Find Persons, Contacts and Electronic
Devices, and supports assigning devices and contacts to the Person.

## Prepare Database
Install postgres and create a database with the name `db_person`
Username and Password can be altered in the `application.properties`.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

## End Points

### Person

GET `/people-management/persons` find all persons.<br>
GET `/people-management/persons/<id>`find a person.<br>
DELETE `/people-management/persons/<id>` delete a person<br>
PUT `/people-management/persons/assign?personId=<person_id>&contactId=<contact_id>` assign contact to a person<br>
POST `/people-management/persons` add a person.<br>

```shell script
{
  "firstName" : "san",
  "lastName" : "lak",
  "address" : "colombo",
  "designation" : "TL",
}
```

### Contact

GET `/contact-management/contacts` find all contacts.<br>
GET `/contact-management/contacts/<id>`find a contact.<br>
DELETE `/contact-management/contacts/<id>`delete a contact<br>
POST `/people-management/persons`add a contact<br>

```shell script
{
  "name" : "geveo",
  "email" : "geveo@gmail.com",
  "mobile" : "778412522"
}
```
### Electronic Devices

GET `/device-management/electronic-devices` find all devices<br>
GET `/device-management/electronic-devices/<id>` find a device<br>
DELETE `/device-management/electronic-devices/<id>` delete a device<br>
PUT `/device-management/electronic-devices/assign?deviceId=<device_id>&personId=<person_id>` Assign a device to a person<br>
POST `/device-management/electronic-devices`add a device<br>

```shell script
{            
    "name" : "TV",
    "make" : "Samsung",
    "model" : "MOD_7589V664"
}
```