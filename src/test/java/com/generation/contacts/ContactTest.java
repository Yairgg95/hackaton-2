package com.generation.contacts;

import org.junit.jupiter.api.Test;

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
