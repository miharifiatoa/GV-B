package org.sales_management;

import org.sales_management.entity.PersonEntity;
import org.sales_management.service.PersonService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PersonEntity person = new PersonEntity();
        person.setLastname("Mihaja");
        person.setFirstname("Odilon");
        person.setAddress("Ambohipo");
        PersonService personService = new PersonService();
        PersonEntity person1 = personService.create(person);
        System.out.println(person1.getLastname());
        PersonEntity mihaja = personService.getById(1L);
        System.out.println(mihaja.getLastname());
    }
}