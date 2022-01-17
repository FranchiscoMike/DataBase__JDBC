package uz.pdp;

import entity.Response;
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
            System.out.println("5. Input product");

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

                    MyRaspiratory.editUser(id, email, password, fullName, phone);

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

                    MyRaspiratory.addUser(email, password, fullName, phone);
                }
                case 4 -> MyRaspiratory.showAllUsers();
                case 5 -> {
                    // currency, supplier,  were house  keladi

                    int optioncha = -1, w, c, s;

//                      if (count==0) {
                    MyRaspiratory.showAllWereHouses();
                    System.out.println("In which WareHouse do you want to input your product :");
                    w = SCANNER_NUM.nextInt();

                    MyRaspiratory.showAllCurrencies();
                    System.out.println("In which currency do you want to input your product :");
                    c = SCANNER_NUM.nextInt();

                    MyRaspiratory.showAllSuppliers();
                    System.out.println("In which supplier do you want to input your product :");
                    s = SCANNER_NUM.nextInt();

                    Response response = MyRaspiratory.createInputForNewInputProduct(w, c, s);


//                        }

                    while (optioncha != 0 && response.isSuccess()) {

                        // doim so'raladigan :
                        System.out.print("There maximum (0...50) 50 types of product " +
                                "Enter one of them correctly please : ");
                        int product_id = SCANNER_NUM.nextInt();

                        System.out.println("Enter the amount of it :");
                        int amount = SCANNER_NUM.nextInt();

                        System.out.println("Enter the price :");
                        double price = SCANNER_NUM.nextDouble();

                        MyRaspiratory.inputProduct( product_id, amount, price);

                        System.out.println("In order to stop enter  0  ");
                        System.out.println("In order to  input product again enter except 0  ");
                        try {
                            optioncha = SCANNER_NUM.nextInt();
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter numeric value");
                        }
                    }

                }

            }

        }


    }
}
