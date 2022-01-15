package uz.pdp;

import lombok.SneakyThrows;
import repasitory.MyRaspiratory;

import java.util.Scanner;

public class Main {

    public static final Scanner SCANNER_NUM = new Scanner(System.in);
    public static final Scanner SCANNER_STR = new Scanner(System.in);

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println("+---------------------------+");
        System.out.println("|  Welcome to MY  DATABASE  |");
        System.out.println("+---------------------------+");

        int option = -1;
        while (option != 0) {
            System.out.println("Enter one of the services: ");
            // services :
            System.out.println("1. Edit User");
            System.out.println("2. Delete User");
            System.out.println("3. Add User");
            System.out.println("4. Show Users");

            option = SCANNER_NUM.nextInt();

            switch (option) {
                case 1 -> {
                    // asking information :
                    System.out.println("Enter your ID :");
                    int id = SCANNER_NUM.nextInt();
                    System.out.println("Enter your email :");
                    String email = SCANNER_STR.nextLine();
                    System.out.println("Enter your password :");
                    String password = SCANNER_STR.nextLine();
                    System.out.println("Enter your fullname :");
                    String fullName = SCANNER_STR.nextLine();
                    System.out.println("Enter your phone :");
                    String phone = SCANNER_STR.nextLine();

                    MyRaspiratory.editUser(id,email,password,fullName,phone);

                }
                case 2 -> {
                    System.out.println("Enter your ID :");
                    int id = SCANNER_NUM.nextInt();
                    MyRaspiratory.deleteUser(id);
                }
                case 3 -> {
                    // asking information :

                    System.out.println("Enter your email :");
                    String email = SCANNER_STR.nextLine();
                    System.out.println("Enter your password :");
                    String password = SCANNER_STR.nextLine();
                    System.out.println("Enter your fullname :");
                    String fullName = SCANNER_STR.nextLine();
                    System.out.println("Enter your phone :");
                    String phone = SCANNER_STR.nextLine();

                    MyRaspiratory.addUser(email,password,fullName,phone);
                }
                case 4 -> MyRaspiratory.showAllUsers();
            }

        }


    }
}
