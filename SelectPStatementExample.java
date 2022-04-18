package jdbcPackeage;

import java.sql.*;


public class SelectPStatementExample {
    private static final String QUERY = "select id,name,email,country,password from users where id =?";

    public static void main(String[] args) {

        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "root");

           
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);) {
            preparedStatement.setInt(1, 1);
            System.out.println(preparedStatement);
           
            ResultSet rs = preparedStatement.executeQuery();

       
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                String password = rs.getString("password");
                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}