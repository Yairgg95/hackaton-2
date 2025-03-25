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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + ": " + phoneNumber;
    }

    public static void listarContactos(HashSet<Contact> contactList) {
        System.out.println("\n--- Lista de Contactos ---");
        if (contactList.isEmpty()) {
            System.out.println("No hay contactos registrados");
        } else {
            contactList.forEach(System.out::println);
        }
        System.out.println("--------------------------");
    }

    public static void existeContacto(HashSet<Contact> contactList, Scanner s) {
        System.out.println("\n--- Verificar Contacto ---");
        System.out.print("Ingrese nombre: ");
        String firstName = s.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = s.nextLine();

        boolean exists = false;
        for (Contact c : contactList) {
            if (c.getFirstName().equalsIgnoreCase(firstName) &&
                    c.getLastName().equalsIgnoreCase(lastName)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("El contacto existe en la agenda.");
        } else {
            System.out.println("El contacto no existe en la agenda.");
        }
        System.out.println("--------------------------");
    }

    //Edna

    //Pido el objeto de hash set y el scanner para que no se use varias veces
    public static boolean deleteContact(HashSet<Contact> contactList, Scanner scanner) {
        System.out.println("\n--- Eliminar Contacto ---");

        // Solicitar datos del contacto a eliminar
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine().trim();

        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Error: Nombre y apellido son obligatorios");
            System.out.println("--------------------------");
            return false;
        }

        // Crear contacto temporal para búsqueda (sin número)
        Contact contactToRemove = new Contact(firstName, lastName, "");

        // Buscar y eliminar el contacto
        boolean removed = contactList.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(firstName) &&
                        c.getLastName().equalsIgnoreCase(lastName)
        );

        if (removed) {
            System.out.println("Contacto " + firstName + " " + lastName + " eliminado correctamente.");
        } else {
            System.out.println("No se encontró el contacto " + firstName + " " + lastName);
        }

        System.out.println("--------------------------");
        return removed;
    }





    public static boolean modPhone(HashSet<Contact> contactList, Scanner scanner) {
        System.out.println("\n--- Modificar Teléfono ---");

        // Solicitar datos del contacto
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine().trim();

        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Error: Nombre y apellido son obligatorios");
            System.out.println("--------------------------");
            return false;
        }

        // Buscar el contacto existente
        Contact existingContact = null;
        for (Contact c : contactList) {
            if (c.getFirstName().equalsIgnoreCase(firstName) &&
                    c.getLastName().equalsIgnoreCase(lastName)) {
                existingContact = c;
                break;
            }
        }

        if (existingContact == null) {
            System.out.println("Error: No se encontró el contacto " + firstName + " " + lastName);
            System.out.println("--------------------------");
            return false;
        }

        // Solicitar nuevo número
        System.out.print("Ingrese nuevo número de teléfono: ");
        String newPhoneNumber = scanner.nextLine().trim();

        // Crear nuevo contacto con el número actualizado
        Contact updatedContact = new Contact(firstName, lastName, newPhoneNumber);

        // Reemplazar el contacto
        contactList.remove(existingContact);
        contactList.add(updatedContact);

        System.out.println("Teléfono actualizado correctamente para " + firstName + " " + lastName);
        System.out.println("Nuevo número: " + newPhoneNumber);
        System.out.println("--------------------------");
        return true;
    }




    // Jose

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





}
