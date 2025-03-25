package com.generation;

import java.util.HashSet;
import java.util.Scanner;

import com.generation.contacts.Contact;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int option = 1;
        HashSet<Contact> contactList = new HashSet<>();

        contactList.add(new Contact("Juan", "Ro", "123-456-7890"));
        contactList.add(new Contact("Alberto", "R", "234-567-8901"));
        contactList.add(new Contact("Josefa", "R", "345-678-9012"));
        contactList.add(new Contact("Juan", "R", "123-456-7890"));

        System.out.println("\n--- Menú Agenda ---");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Verificar existencia");
        System.out.println("3. Listar contactos");
        System.out.println("4. Buscar contacto");
        System.out.println("5. Eliminar contacto");
        System.out.println("6. Modificar teléfono");
        System.out.println("7. Agenda llena");
        System.out.println("8. Espacios libres");
        System.out.println("0. Salir");
        System.out.print("Opción: ");

        while (option != 0) {

            option = s.nextInt();
            s.nextLine();

            switch (option) {
                case 1: {
                    Contact.addContact(contactList, s);
                    Contact.listarContactos(contactList);
                    break;
                }
                case 2: {

                    break;
                }
                case 3: {
                    Contact.listarContactos(contactList);
                    break;
                }
                case 4: {
                    Contact.existeContacto(contactList, s);
                    break;
                }
                case 5: {


                    // 5. Eliminar contacto
                    Contact.deleteContact(contactList, s);
                    Contact.listarContactos(contactList);
                    break;
                }
                case 6: {
                    // 6. Modificar teléfono
                    Contact.modPhone(contactList, s);
                    Contact.listarContactos(contactList);
                    break;
                }
                case 7: {

                    break;
                }
                case 0: {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                default:
                    System.out.println("Opción no valida: " + option);
            }
        }
        s.close();
    }
}