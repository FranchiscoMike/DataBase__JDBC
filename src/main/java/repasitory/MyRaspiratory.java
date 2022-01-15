package repasitory;

import entity.Response;
import entity.UserClass;
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
    public static List<UserClass> showAllUsers() {
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

        return userList;
    }

    public static Response addUser( String email, String password, String fullName, String phone) {
        Response response = new Response();
        // coding :

        return response;
    }
}
