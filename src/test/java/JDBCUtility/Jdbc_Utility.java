package JDBCUtility;

import java.sql.*;


public class Jdbc_Utility {

   private static final String url = ConfigReader.getProperty("JDBC_URL"),
                            username = ConfigReader.getProperty("JDBC_USERNAME"),
                            password = ConfigReader.getProperty("JDBC_PASSWORD");



    private static Connection connection;
    private static Statement statement;
    public static DatabaseMetaData metadata;

    //Static block is used for initializing the static variables.
    static {
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            metadata=connection.getMetaData();
        }catch(Exception e) { }

    }

    public static ResultSet getResult(String sql) {
        //creates the result
        ResultSet result = null;

        try {
            result = statement.executeQuery(sql);

        }catch(Exception e) {}

        return result;
    }


    public static void tearDown() {
        //closes the connection at the last step
        try {
            connection.close();
        } catch (Exception e) {

        }
    }


    public static void Updating(String sql) {
        try {
            statement.executeUpdate(sql);
        } catch (SQLException e) {

        }
    }

}
