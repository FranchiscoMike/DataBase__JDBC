package repasitory;

import entity.Category;
import entity.Response;
import lombok.SneakyThrows;
import utils.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepasitory {
    public static List<Category> getCategories() {
        Connection connection = DBConfig.connection();
        System.out.println("connection = " + connection);

        List<Category> categories = new ArrayList<>();
        try {
            // bu faqat biz bergan queryni bajaradi
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from category order by id");

            while (resultSet.next()) {
                Category category = new Category();

                // row :
//                System.out.println("========================================");
//                System.out.println(resultSet.getInt(1));
//                System.out.println(resultSet.getString(2));
//                System.out.println(resultSet.getBoolean(3));
//                System.out.println("========================================");
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                category.setActive(resultSet.getBoolean(3));
                categories.add(category);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @SneakyThrows
    public static boolean addCategory(int id, String name) {
        Connection connection = DBConfig.connection();
        // parametrli query yozish uchun
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into category values(?,?,?)"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setBoolean(3, true);
//        preparedStatement.setBoolean(3,false);

        return preparedStatement.execute();

    }

    @SneakyThrows
    // functionni ishlatish !
    public static Response callFunction(String name, String type, String n_name) {
        try (Connection connection = DBConfig.connection()) {
            CallableStatement callableStatement;
            Response response = new Response();
            callableStatement = connection.prepareCall("{call category_crud(?,?,?,?,?)}");
            callableStatement.setString(1, name);
            callableStatement.setString(2, type);
            callableStatement.setString(3, n_name);
            callableStatement.registerOutParameter(4, Types.BOOLEAN);
            callableStatement.registerOutParameter(5, Types.VARCHAR);


            callableStatement.execute();
            response.setSuccess(callableStatement.getBoolean(4));
            response.setMessage(callableStatement.getString(5));
            return response;
        }
    }

    @SneakyThrows
    public static Response currency_crud(String name, String type, String n_name) {
        Response response = new Response();

        Connection connection = DBConfig.connection();

        CallableStatement callableStatement;

        callableStatement = connection.prepareCall("{call currency_crud(?,?,?,?,?)}");


        callableStatement.setString(1, name);
        callableStatement.setString(2, type);
        callableStatement.setString(3, n_name);
        callableStatement.registerOutParameter(4, Types.BOOLEAN);
        callableStatement.registerOutParameter(5, Types.VARCHAR);

        callableStatement.execute();

        response.setSuccess(callableStatement.getBoolean(4));
        response.setMessage(callableStatement.getString(5));

        return response;
    }

    @SneakyThrows
    public  static Response delete_currency (String name){
        Response response = new Response();
        Connection connection = DBConfig.connection();

        CallableStatement callableStatement;
       callableStatement =  connection.prepareCall("{call delete_currency(?,?,?) }");

       callableStatement.setString(1,name);
       callableStatement.registerOutParameter(2,Types.BOOLEAN);
       callableStatement.registerOutParameter(3,Types.VARCHAR);

       callableStatement.execute();

       response.setMessage(callableStatement.getString(3));
       response.setSuccess(callableStatement.getBoolean(2));

        return  response;
    }
    // edit user :
    //1. Databaseda :function +
    @SneakyThrows
    public  static Response editUser (int id, String email,String password , String fullName ,String phone){
        // coding :
        Response response = new Response();

        Connection connection = DBConfig.connection();
        // hamma maulomotni op kela oladi
        CallableStatement callableStatement ;
        callableStatement = connection.prepareCall("{call edit_user(?,?,?,?,?,?,?,?)}");

        // malumotlarni berib yuborish :

        callableStatement.setInt(1,id);
        callableStatement.setString(2,email);
        callableStatement.setString(3,password);
        callableStatement.setString(4,fullName);
        callableStatement.setString(5,phone);
        callableStatement.setBoolean(6,true);

        callableStatement.registerOutParameter(7,Types.VARCHAR);
        callableStatement.registerOutParameter(8,Types.BOOLEAN);

        // amal bajaraib keladi
        callableStatement.execute();

        //resultga qiymat beradi :
        response.setSuccess(callableStatement.getBoolean(8));
        response.setMessage(callableStatement.getString(7));

        return  response;
    }


}
