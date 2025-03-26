package com.generation;

import java.util.Scanner;

import com.generation.contacts.Addressbook;
import com.generation.contacts.Contact;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Addressbook addressbook = new Addressbook();
        int option = -1;

        while (option != 0) {
            System.out.println("\n--- Menú Agenda ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Verificar existencia");
            System.out.println("3. Listar contactos");
            System.out.println("4. Buscar contacto");
            System.out.println("5. Eliminar contacto");
            System.out.println("6. Modificar teléfono");
            System.out.println("7. Agenda llena / Espacios libres");
            System.out.println("0. Salir");

            System.out.print("Eligé una opción: ");
            option = s.nextInt();
            s.nextLine();

            switch (option) {
                case 1: {
                    // 1. Agregar contacto
                    System.out.println("Nombre: ");
                    String firstName = s.nextLine().trim();
                    System.out.println("Apellido: ");
                    String lastName = s.nextLine().trim();
                    System.out.println("Teléfono: ");
                    String phoneNumber = s.nextLine().trim();
                    Contact newContact = new Contact(firstName, lastName, phoneNumber);
                    addressbook.addContact(newContact);
                    addressbook.listContacts();
                    break;
                }
                case 2: {
                    // 2. Existe contacto
                    System.out.print("Nombre: ");
                    String firstName = s.nextLine().trim();
                    System.out.print("Apellido: ");
                    String lastName = s.nextLine().trim();

                    Contact tempContact = new Contact(firstName, lastName, "");
                    addressbook.contactExists(tempContact);
                    break;
                }
                case 3: {
                    // 3. Listar contactos
                    addressbook.listContacts();
                    break;
                }
                case 4: {
                    //4. Verificar contacto
                    System.out.print("Nombre: ");
                    String firstName = s.nextLine().trim();
                    System.out.print("Apellido: ");
                    String lastName = s.nextLine().trim();
                    addressbook.searchContact(firstName,lastName);
                    break;
                }
                case 5: {
                    // 5. Eliminar contacto
                    System.out.print("Nombre: ");
                    String firstName = s.nextLine().trim();
                    System.out.print("Apellido: ");
                    String lastName = s.nextLine().trim();
                    Contact contactToDelete = new Contact(firstName,lastName, "");
                    addressbook.deleteContact(contactToDelete);
                    addressbook.listContacts();
                    break;
                }
                case 6: {
                    // 6. Modificar teléfono
                    System.out.print("Nombre: ");
                    String firstName = s.nextLine().trim();
                    System.out.print("Apellido: ");
                    String lastName = s.nextLine().trim();
                    System.out.print("Nuevo Teléfono: ");
                    String newPhoneNumber = s.nextLine().trim();
                    addressbook.updatePhoneNumber(firstName,lastName,newPhoneNumber);
                    addressbook.listContacts();
                    break;
                }
                case 7: {
                    // 7. Verificar si la agenda está llena o los espacios libres
                    if (addressbook.isFull()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("Espacios libres: " + addressbook.availableSlots());
                    }
                    break;
                }
                case 0: {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                default:
                    System.out.println("Opción no válida: " + option);
            }
        }
        s.close();
    }
}
