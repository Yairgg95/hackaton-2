package com.generation.contacts;

public class AdressbookTest {


    private AddressBook addressBook;

    @BeforeEach
    void setUp() {
        addressBook = new AddressBook(3); // Máximo de 3 contactos
    }

    @Test
    void testAddContactSuccessfully() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        assertTrue(addressBook.addContact(contact));
    }

    @Test
    void testCannotAddDuplicateContact() {
        Contact contact1 = new Contact("Julio", "Ramirez", "1234567890");
        Contact contact2 = new Contact("Julio", "Ramirez", "0987654321");

        addressBook.addContact(contact1);
        assertFalse(addressBook.addContact(contact2));
    }

    @Test
    void testCannotAddContactWithEmptyName() {
        Contact contact = new Contact("", "Ramirez", "1234567890");
        assertFalse(addressBook.addContact(contact));
    }

    @Test
    void testContactExists() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressBook.addContact(contact);

        assertEquals(contact.getPhoneNumber(),addressBook.contactExists(contact));
    }

    @Test
    void testDeleteExistingContact() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressBook.addContact(contact);
        assertTrue(addressBook.deleteContact(contact));
    }

    @Test
    void testDeleteNonExistingContact() {
        Contact contact = new Contact("Fernanda", "Perez", "1234567890");
        assertFalse(addressBook.deleteContact(contact));
    }

    @Test
    void testSearchContactFound() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressBook.addContact(contact);

        assertEquals("1234567890", addressBook.searchContact("Julio", "Ramirez"));
    }

    @Test
    void testSearchContactNotFound() {
        assertNull(addressBook.searchContact("Fernanda", "Perez"));
    }

    @Test
    void testUpdatePhoneNumber() {
        Contact contact = new Contact("Julio", "Ramirez", "1234567890");
        addressBook.addContact(contact);

        assertTrue(addressBook.updatePhoneNumber("Julio", "Ramirez", "0987654321"));
        assertEquals("0987654321", addressBook.searchContact("Julio", "Ramirez"));
    }

    @Test
    void testAgendaIsFull() {
        addressBook.addContact(new Contact("Julio", "Ramirez", "1234567890"));
        addressBook.addContact(new Contact("Fernanda", "Perez", "0987654321"));
        addressBook.addContact(new Contact("Alicia", "Garcia", "1112223333"));

        assertTrue(addressBook.isFull());
    }

    @Test
    void testAvailableSlots() {
        addressBook.addContact(new Contact("Julio", "Ramirez", "1234567890"));
        assertEquals(2, addressBook.availableSlots());
    }


}
