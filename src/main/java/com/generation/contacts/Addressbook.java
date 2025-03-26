package com.generation.contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Addressbook {
    private static HashSet<Contact> contactList = new HashSet<>();

    public static void addContact(Scanner scanner) {
        System.out.println("\n--- Agregar Nuevo Contacto ---");

        // Solicitar datos del contacto
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Ingrese número de teléfono: ");
        String phoneNumber = scanner.nextLine().trim();

        //No sobrepases la agenda limite
        if (contactList.size() >= 10) {
            System.out.println("Error: La agenda está llena (límite 10 contactos)");
            System.out.println("--------------------------");
        }

        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios");
            System.out.println("--------------------------");
        }

        // Validar formato de teléfono (ejemplo básico)
        if (!phoneNumber.matches("\\d{8,15}")) {
            System.out.println("Error: El número de teléfono debe contener solo dígitos (8-15 caracteres)");
            System.out.println("--------------------------");
        }

        // Crear nuevo contacto
        Contact newContact = new Contact(firstName, lastName, phoneNumber);

        // Verificar si el contacto ya existe
        if (contactList.contains(newContact)) {
            System.out.println("Error: Ya existe un contacto con ese nombre, apellido y número");
            System.out.println("--------------------------");
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
    }

    // Joaquín
    public static void isContactExists(Scanner scanner) {
        //Se ingresa el nombre y apellido del contacto que se requier buscar
        System.out.print("Ingrese nombre: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Ingrese apellido: ");
        String lastName = scanner.nextLine().trim();

        // Validación de que las variable no estén vacías
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Error: Nombre y apellido son obligatorios");
            return;
        }

        // Iteración de la lista en busqueda del contacto con valores iguales
        for (Contact c : contactList) {
            if (c.getFirstName().equals(firstName) && c.getLastName().equals(lastName)) {
                System.out.println("El contacto " + firstName + " " + lastName + " existe en la agenda");
                return;
            }
        }
        System.out.println("El contacto " + firstName + " " + lastName + " no existe en la agenda");
    }

    public static void listContacts() {
        System.out.println("\n--- Lista de Contactos ---");
        if (contactList.isEmpty()) {
            System.out.println("No hay contactos registrados");
        } else {

            TreeSet<Contact> sortedContacts = new TreeSet<>(Comparator.comparing(Contact::getFirstName).thenComparing(Contact::getLastName));
            sortedContacts.addAll(contactList);
            sortedContacts.forEach(System.out::println);
        }
        System.out.println("--------------------------");
    }

    public static void verifyContact(Scanner s) {
        System.out.println("\n--- Verificar Contacto ---");
        // Se ingresa el nombre y apellido para buscar en el hashset con un ciclo
        System.out.print("Ingrese nombre: ");
        String firstName = s.nextLine();
        System.out.print("Ingrese apellido: ");
        String lastName = s.nextLine();
        Contact tempC = null;
        for (Contact c : contactList) {
            // Se verifica la existencia del contacto ignorando mayúsculas y minúsculas, se guarda el contacto temporalmente
            if (c.getFirstName().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)) {
                tempC = c;
                break;
            }
        }
        // Se imprime un mensaje dependiendo de resultado
        if (tempC == null) {
            System.out.println("El contacto " + firstName + " " + lastName + " no existe");
        }else{
            System.out.println("El número de " + tempC.getFirstName() + " " + tempC.getLastName() + " es " + tempC.getPhoneNumber());
        }
        System.out.println("--------------------------");
    }
    //

    //Edna

    //Pido el objeto de hash set y el scanner para que no se use varias veces
    public static void deleteContact(Scanner scanner) {
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
    }

    public static void modifyPhone(Scanner scanner) {
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
            return;
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
            return;
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
    }

    public static boolean availableSpace() {
        return contactList.size() >= 10;
    }

    public static int checkAddressbookSize() {
        return 10 - contactList.size();
    }

}
