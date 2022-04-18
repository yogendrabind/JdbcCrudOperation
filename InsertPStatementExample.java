package jdbcPackeage;
import java.sql.*;


public class InsertPStatementExample {
    private static final String INSERT_USERS_SQL = "INSERT INTO users" +
        "  (id, name, email, country, password) VALUES " +
        " (?, ?, ?, ?, ?);";

    public static void main(String[] argv) throws SQLException {
        InsertPStatementExample createTableExample = new InsertPStatementExample();
        createTableExample.insertRecord();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
       
        try (Connection connection = DriverManager
            .getConnection("jdbc:mysql://localhost:3306/users?useSSL=false", "root", "root");

   
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Yogendra");
            preparedStatement.setString(3, "yogendra@gmail.com");
            preparedStatement.setString(4, "Varanasi");
            preparedStatement.setString(5, "secret");

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
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