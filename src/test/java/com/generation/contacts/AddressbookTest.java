package com.generation.contacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AddressbookTest {


    private Addressbook addressbook;

    @BeforeEach
    void setUp() {
        addressbook = new Addressbook();
    }

    @Test
    void testAddContactSuccessfully() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        assertTrue(addressbook.addContact(contact));
    }

    @Test
    void testCannotAddDuplicateContact() {
        Contact contact1 = new Contact("Julio", "Ramirez", "1234567890");
        Contact contact2 = new Contact("Julio", "Ramirez", "0987654321");

        addressbook.addContact(contact1);
        assertFalse(addressbook.addContact(contact2));
    }

    @Test
    void testCannotAddContactWithEmptyName() {
        Contact contact = new Contact("", "Ramirez", "1234567890");
        assertFalse(addressbook.addContact(contact));
    }

    @Test
    void testContactExists() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressbook.addContact(contact);

        assertTrue(addressbook.contactExists(contact));
    }

    @Test
    void testDeleteExistingContact() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressbook.addContact(contact);
        assertTrue(addressbook.deleteContact(contact));
    }

    @Test
    void testDeleteNonExistingContact() {
        Contact contact = new Contact("Fernanda", "Perez", "1234567890");
        assertFalse(addressbook.deleteContact(contact));
    }

    @Test
    void testSearchContactFound() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressbook.addContact(contact);

        assertEquals("1234567890", addressbook.searchContact("Julio", "Ramirez"));
    }

    @Test
    void testSearchContactNotFound() {
        assertNull(addressbook.searchContact("Fernanda", "Perez"));
    }

    @Test
    void testUpdatePhoneNumber() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressbook.addContact(contact);

        assertTrue(addressbook.updatePhoneNumber("Julio", "Ramirez", "0987654321"));
        assertEquals("0987654321", addressbook.searchContact("Julio", "Ramirez"));
    }

    @Test
    void testAgendaIsFull() {
        addressbook.addContact(new Contact("Julio", "Ramirez", "1234567890"));
        addressbook.addContact(new Contact("Fernanda", "Perez", "0987654321"));
        addressbook.addContact(new Contact("Alicia", "Garcia", "1112223333"));
        addressbook.addContact(new Contact("Julio", "Gonzales", "1234567890"));
        addressbook.addContact(new Contact("Fernanda", "Elizalde", "0987654321"));
        addressbook.addContact(new Contact("Alicia", "Mares", "1112223333"));
        addressbook.addContact(new Contact("Julio", "Raya", "1234567890"));
        addressbook.addContact(new Contact("Fernanda", "Alvarez", "0987654321"));
        addressbook.addContact(new Contact("Alicia", "Jimenez", "1112223333"));
        addressbook.addContact(new Contact("Alicia", "Blancas", "1112223333"));

        assertTrue(addressbook.isFull());
    }

    @Test
    void testAvailableSlots() {
        addressbook.addContact(new Contact("Julio", "Ramirez", "1234567890"));
        assertEquals(9, addressbook.availableSlots());
    }



}
