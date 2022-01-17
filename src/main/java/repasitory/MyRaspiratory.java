package repasitory;

import entity.*;
import lombok.SneakyThrows;
import utils.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyRaspiratory {

    @SneakyThrows
    public static Response editUser(int id, String email, String password, String fullName, String phone) {
        // coding :
        Response response = new Response();

        Connection connection = DBConfig.connection();
        // hamma maulomotni op kela oladi
        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{call edit_user(?,?,?,?,?,?,?,?)}");

        // malumotlarni berib yuborish :

        callableStatement.setInt(1, id);
        callableStatement.setString(2, email);
        callableStatement.setString(3, password);
        callableStatement.setString(4, fullName);
        callableStatement.setString(5, phone);
        callableStatement.setBoolean(6, true);

        callableStatement.registerOutParameter(7, Types.VARCHAR);
        callableStatement.registerOutParameter(8, Types.BOOLEAN);

        // amal bajaraib keladi
        callableStatement.execute();

        //resultga qiymat beradi :
        response.setSuccess(callableStatement.getBoolean(8));
        response.setMessage(callableStatement.getString(7));

        System.out.println(response);

        return response;
    }


    @SneakyThrows
    public static Response deleteUser(int id) {
        Response response = new Response();
        // connection :
        Connection connection = DBConfig.connection();

        CallableStatement callableStatement = connection.prepareCall("{call delete_user(?,?,?,?)}");

        callableStatement.setInt(1, id);
        callableStatement.setBoolean(2, false);

        callableStatement.registerOutParameter(3, Types.VARCHAR);
        callableStatement.registerOutParameter(4, Types.VARCHAR);

        callableStatement.execute();

        response.setMessage(callableStatement.getString(3));
        response.setSuccess(callableStatement.getBoolean(4));

        return response;
    }


    @SneakyThrows
    public static void showAllUsers() {
        Connection connection = DBConfig.connection();
        List<UserClass> userList = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from users order by id");
        while (resultSet.next()) {
            UserClass user = new UserClass();

            user.setId(resultSet.getInt(1));
            user.setEmail(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setFull_name(resultSet.getString(4));
            user.setPhone(resultSet.getString(5));
            user.setActive(resultSet.getBoolean(6));

            userList.add(user);
        }

        userList.forEach(System.out::println);

        userList.forEach(System.out::println);
    }

    public static Response addUser(String email, String password, String fullName, String phone) {
        Response response = new Response();
        // coding :

        return response;
    }

    @SneakyThrows
    public static Response createInputForNewInputProduct(int wareHouse_id,
                                                         int currency_id,
                                                         int supplier_id) {

        Response response = new Response();

        Connection connection = DBConfig.connection();
        CallableStatement callableStatement = connection.prepareCall("{call create_input(?,?,?,?,?) }");

        callableStatement.setInt(1,currency_id);
        callableStatement.setInt(2,wareHouse_id);
        callableStatement.setInt(3,supplier_id);

        // out parametr bo'lganiga shunaqa :
        callableStatement.registerOutParameter(4,Types.VARCHAR);
        callableStatement.registerOutParameter(5,Types.BOOLEAN);


        callableStatement.execute();

        response.setMessage(callableStatement.getString(4));
        response.setSuccess(callableStatement.getBoolean(5));

        System.out.println(response);

        return response;
    }

    @SneakyThrows
    public static Response inputProduct(
            int product_id,
            int amount,
            double price) {
        Response response = new Response();
// coding :
        Connection connection = DBConfig.connection();

        // function create in databse
        CallableStatement callableStatement = connection.prepareCall("{call input_product(?,?,?,?,?)}");

        callableStatement.setInt(1,product_id);
        callableStatement.setInt(2,amount);
        callableStatement.setDouble(3,price);


        // out parametr bo'lganiga shunaqa :
        callableStatement.registerOutParameter(4,Types.VARCHAR);
        callableStatement.registerOutParameter(5,Types.BOOLEAN);


        callableStatement.execute();

        response.setMessage(callableStatement.getString(4));
        response.setSuccess(callableStatement.getBoolean(5));

        System.out.println(response);


        return response;
    }

    public static void showAllCurrencies() {
        Connection connection = DBConfig.connection();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from currency where active = true");

            List<Currency> currencyList = new ArrayList<>();

            while (resultSet.next()) {
                Currency currency = new Currency();

                currency.setId(resultSet.getInt(1));
                currency.setName(resultSet.getString(2));
                currency.setActive(true);

                currencyList.add(currency);

            }
            currencyList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void showAllWereHouses() {

        Connection connection = DBConfig.connection();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from warehouse where active = true");

            List<WereHouse> wereHouseList = new ArrayList<>();

            while (resultSet.next()) {
                WereHouse wereHouse = new WereHouse();


                wereHouse.setId(resultSet.getInt(1));
                wereHouse.setName(resultSet.getString(2));
                wereHouse.setActive(true);

                wereHouseList.add(wereHouse);

            }
            wereHouseList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void showAllSuppliers() {
        Connection connection = DBConfig.connection();
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from supplier where active = true");

            List<Supplier> supplierList = new ArrayList<>();

            while (resultSet.next()) {
                Supplier supplier = new Supplier();

                supplier.setId(resultSet.getInt(1));
                supplier.setName(resultSet.getString(2));
                supplier.setActive(true);

                supplierList.add(supplier);

            }
            supplierList.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
