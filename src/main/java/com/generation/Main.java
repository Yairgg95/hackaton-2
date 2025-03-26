package com.generation;

import java.util.Scanner;
import com.generation.contacts.Addressbook;


public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int option = 1;

        System.out.println("\n--- Menú Agenda ---");
        System.out.println("1. Añadir contacto");
        System.out.println("2. Verificar existencia");
        System.out.println("3. Listar contactos");
        System.out.println("4. Buscar contacto");
        System.out.println("5. Eliminar contacto");
        System.out.println("6. Modificar teléfono");
        System.out.println("7. Agenda llena / Espacios libres");
        System.out.println("7. Mostrar el menú");
        System.out.println("0. Salir");

        while (option != 0) {

            System.out.print("Eligé una opción: ");
            option = s.nextInt();
            s.nextLine();

            switch (option) {
                case 1: {
                    // 1. Agregar contacto
                    Addressbook.addContact(s);
                    Addressbook.listContacts();
                    break;
                }
                case 2: {
                    // 2. Existe contacto
                    Addressbook.isContactExists(s);
                    break;
                }
                case 3: {
                    // 3. Listar contactos
                    Addressbook.listContacts();
                    break;
                }
                case 4: {
                    //4. Verificar contacto
                    Addressbook.verifyContact(s);
                    break;
                }
                case 5: {
                    // 5. Eliminar contacto
                    Addressbook.deleteContact(s);
                    Addressbook.listContacts();
                    break;
                }
                case 6: {
                    // 6. Modificar teléfono
                    Addressbook.modifyPhone(s);
                    Addressbook.listContacts();
                    break;
                }
                case 7: {
                    // 7. Verificar si la agenda está llena o los espacios libres
                    if (Addressbook.availableSpace()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("Espacios disponibles: " + Addressbook.checkAddressbookSize());
                    }
                    break;
                }
                case 8:{
                    System.out.println("\n--- Menú Agenda ---");
                    System.out.println("1. Añadir contacto");
                    System.out.println("2. Verificar existencia");
                    System.out.println("3. Listar contactos");
                    System.out.println("4. Buscar contacto");
                    System.out.println("5. Eliminar contacto");
                    System.out.println("6. Modificar teléfono");
                    System.out.println("7. Agenda llena / Espacios libres");
                    System.out.println("7. Mostrar el menú");
                    System.out.println("0. Salir");
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
