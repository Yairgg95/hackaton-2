package com.generation.contacts;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    public Contact(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName)
                && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + phoneNumber;
    }

    public static boolean addContact(HashSet<Contact> contactList, Scanner scanner) {
        System.out.println("\n--- Agregar Nuevo Contacto ---");

        // Solicitar datos del contacto
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Ingrese número de teléfono: ");
        String phoneNumber = scanner.nextLine().trim();

        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios");
            System.out.println("--------------------------");
            return false;
        }

        // Validar formato de teléfono (ejemplo básico)
        if (!phoneNumber.matches("\\d{8,15}")) {
            System.out.println("Error: El número de teléfono debe contener solo dígitos (8-15 caracteres)");
            System.out.println("--------------------------");
            return false;
        }

        // Crear nuevo contacto
        Contact newContact = new Contact(firstName, lastName, phoneNumber);

        // Verificar si el contacto ya existe
        if (contactList.contains(newContact)) {
            System.out.println("Error: Ya existe un contacto con ese nombre, apellido y número");
            System.out.println("--------------------------");
            return false;
        }

        // Agregar el contacto
        boolean added = contactList.add(newContact);
        if (added) {
            System.out.println("Contacto agregado exitosamente:");
            System.out.println(newContact);
        } else {
            System.out.println("Error: No se pudo agregar el contacto");
        }

        System.out.println("--------------------------");
        return added;
    }

    public static void listContacts(HashSet<Contact> contactList) {
        System.out.println("\n--- Lista de Contactos ---");
        if (contactList.isEmpty()) {
            System.out.println("No hay contactos registrados");
        } else {
            contactList.forEach(System.out::println);
        }
        System.out.println("--------------------------");
    }

    public static Contact verifyContact(HashSet<Contact> contactList, Scanner s) {
        System.out.println("\n--- Verificar Contacto ---");
        System.out.print("Ingrese nombre: ");
        String firstName = s.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = s.nextLine();
        Contact tempC = null;
        for (Contact c : contactList) {
            if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)) {

                System.out.println("El número de " + firstName + " " + lastName + " es " + c.phoneNumber);
                tempC = c;
                break;
            }
        }
        if (tempC == null) {
            System.out.println("El contacto " + firstName + " " + lastName + " no existe");
        }

        System.out.println("--------------------------");
        return tempC;
    }

    // metodo 5 eliminar contacto
    public static void deleteContact(HashSet<Contact> contactList, Scanner s) {
        System.out.print("Ingrese nombre: ");
        String nombre = s.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = s.nextLine();
        System.out.print("Ingrese teléfono: ");
        String telefono = s.nextLine();

        // Creamos el contacto temporal para buscar y eliminar
        Contact tempC = new Contact(nombre, apellido, telefono);
        if (contactList.remove(tempC)) {
            System.out.println(
                    "Contacto " + tempC.getFirstName() + " " + tempC.getLastName() + " eliminado correctamente.");
        } else {
            System.out.println(
                    "El contacto " + tempC.getFirstName() + " " + tempC.getLastName() + " no existe en la agenda.");
        }
        System.out.println("--------------------------");
    }

    // metodo 6 modificar telefono
    public static void changeTelephoneNumber(HashSet<Contact> contactList, Scanner s) {
        Contact tempC = verifyContact(contactList, s);

        if (tempC != null) {
            System.out.print("Ingrese nuevo teléfono: ");
            String nuevoTelefono = s.nextLine();
            Contact nuevoContacto = new Contact(tempC.firstName, tempC.lastName, nuevoTelefono);
            contactList.remove(tempC);
            contactList.add(nuevoContacto);
            System.out.println("Teléfono actualizado correctamente para " + nuevoContacto.firstName + " "
                    + nuevoContacto.lastName);
        }
        System.out.println("--------------------------");
    }
}