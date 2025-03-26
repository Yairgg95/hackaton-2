package com.generation.contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

public class Addressbook {
    private  HashSet<Contact> contactList = new HashSet<>();

    public boolean addContact(Contact contact) {

        // Validar campos vacíos
        if (contact.getFirstName().isEmpty() || contact.getLastName().isEmpty() || contact.getPhoneNumber().isEmpty()) {
            System.out.println("Error: Todos los campos son obligatorios");
            System.out.println("--------------------------");
            return false;
        }

        //No sobrepases la agenda limite
        if (contactList.size() >= 10) {
            System.out.println("Error: La agenda está llena (límite 10 contactos)");
            System.out.println("--------------------------");
            return false;
        }

        // Validar formato de teléfono (ejemplo básico)
        if (!contact.getPhoneNumber().matches("\\d{8,15}")) {
            System.out.println("Error: El número de teléfono debe contener solo dígitos (8-15 caracteres)");
            System.out.println("--------------------------");
            return false;
        }

        // Verificar si el contacto ya existe
        if (contactList.contains(contact)) {
            System.out.println("Error: Ya existe un contacto con ese nombre, apellido y número");
            System.out.println("--------------------------");
            return false;
        }

        // Agregar el contacto
        boolean added = contactList.add(contact);
        if (added) {
            System.out.println("Contacto agregado exitosamente:");
            System.out.println(contact);
        } else {
            System.out.println("Error: No se pudo agregar el contacto");
        }

        System.out.println("--------------------------");
        return added;
    }

    public void listContacts() {
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

    public String searchContact(String firstName, String lastName) {
        System.out.println("\n--- Verificar Contacto ---");

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
            System.out.println("--------------------------");
            return null;
        }else{
            System.out.println("El número de " + tempC.getFirstName() + " " + tempC.getLastName() + " es " + tempC.getPhoneNumber());
            System.out.println("--------------------------");
            return tempC.getPhoneNumber();
        }

    }

    //Edna

    //Pido el objeto de hash set y el scanner para que no se use varias veces
    public boolean deleteContact(Contact contact) {
        System.out.println("\n--- Eliminar Contacto ---");

        // Validar campos vacíos
        if (contact.getFirstName().isEmpty() || contact.getLastName().isEmpty()) {
            System.out.println("Error: Nombre y apellido son obligatorios");
            System.out.println("--------------------------");
        }

        boolean removed = contactList.removeIf(c ->
                c.getFirstName().equalsIgnoreCase(contact.getFirstName()) &&
                        c.getLastName().equalsIgnoreCase(contact.getLastName())
        );

        if (removed) {
            System.out.println("Contacto " + contact.getFirstName() + " " + contact.getLastName() + " eliminado correctamente.");
            System.out.println("--------------------------");
            return true;
        } else {
            System.out.println("No se encontró el contacto " + contact.getFirstName() + " " + contact.getLastName());
            System.out.println("--------------------------");
            return false;
        }

    }

    public boolean updatePhoneNumber(String firstName, String lastName, String newPhoneNumber) {
        System.out.println("\n--- Modificar Teléfono ---");


        // Validar campos vacíos
        if (firstName.isEmpty() || lastName.isEmpty()) {
            System.out.println("Error: Nombre y apellido son obligatorios");
            System.out.println("--------------------------");
            return false;
        }

        // Buscar el contacto existente
        for (Contact c : contactList) {
            if (c.getFirstName().equalsIgnoreCase(firstName) &&
                    c.getLastName().equalsIgnoreCase(lastName)) {
                c.setPhoneNumber(newPhoneNumber);
                System.out.println("Teléfono actualizado correctamente para " + firstName + " " + lastName + " nuevo número: " + newPhoneNumber);
                System.out.println("--------------------------");
                return true;
            }
        }
        System.out.println("Error: No se encontró el contacto " + firstName + " " + lastName);
        System.out.println("--------------------------");
        return false;
    }

    public int availableSlots() {
        int availableSlots = 10 - contactList.size();
        System.out.println("Hay " + availableSlots + " disponibles.");
        return availableSlots;
    }

    public boolean isFull() {
        if(contactList.size() >= 10) {
            System.out.println("La agenda esta llena");
            return true;
        }
        return false;
    }

    public boolean contactExists(Contact contact) {
        for(Contact c : contactList){
            if(c.getFirstName().equalsIgnoreCase(contact.getFirstName()) &&
                    c.getLastName().equalsIgnoreCase(contact.getLastName())) {
                System.out.println("El contacto " + contact.toString() + " ya existe en la agenda");
                return true;
            }
        }
        System.out.println("El contacto " + contact.toString() + "  no existe en la agenda");
        return false;
    }
}
