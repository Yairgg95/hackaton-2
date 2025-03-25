package com.generation.contacts;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.HashSet;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;


public class ContactTest {

    @Test
    void testContactCreation() {
        Contact contact = new Contact("Gerardo", "Ramirez", "5545768565");
        assertEquals("Gerardo", contact.getFirstName());
        assertEquals("Ramirez", contact.getLastName());
        assertEquals("5545768565", contact.getPhoneNumber());
    }
    @Test
    void testAddContactSuccess() {
        // Test de add contact para la opcion 3 de la agenda
        HashSet<Contact> contacts = new HashSet<>();
        String input = "Juan\nPerez\n12345678\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // esta linea si no se entiende es para como si un usuario metiera los datos
        Scanner scanner = new Scanner(System.in);
        boolean result = Contact.addContact(contacts, scanner); // si todo bien un bolean
        assertTrue(result); //revisa el boolean
        assertEquals(1, contacts.size()); // comprueba que se agrego un contacto viendo el tamaño
        assertTrue(contacts.stream().anyMatch(c -> //aca comprueba cada uno de los datosque se ingresaron anteriormente
                c.getFirstName().equals("Juan") &&
                        c.getLastName().equals("Perez") &&
                        c.getPhoneNumber().equals("12345678")
        ));
    }
    @Test
    void testContactsWithSameNameAndLastNameAreEqual() {
        Contact contact1 = new Contact("Gerardo", "Ramirez", "5545768565");
        Contact contact2 = new Contact("Gerardo", "Ramirez", "5564738564");
        assertEquals(contact1.getFirstName(), contact2.getFirstName());
        assertEquals(contact1.getLastName(), contact2.getLastName());
    }

    @Test
    void testContactsWhitDifferentNameAreNotEqual() {
        Contact contact1 = new Contact("Gerardo", "Ramirez", "5545768565");
        Contact contact2 = new Contact("Jorge", "Ramirez", "5564738564");
        assertNotEquals(contact1.getFirstName(), contact2.getFirstName());
    }
}
