package JDBC_Practice;

import java.sql.*;

public class SimpleConnection {
    public static void main(String[] args) throws SQLException {


        String url = "jdbc:oracle:thin:@54.209.157.196:1521:xe",
                username = "hr",
                password = "hr";


        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Connection is connected");

        Statement statement =connection.createStatement();
        ResultSet  resultSet=statement.executeQuery("select * from countries");

        while(resultSet.next()) {
             String countryName=resultSet.getString("country_name");
           int regionID=  resultSet.getInt("region_id");
             if(countryName.equalsIgnoreCase("germany")){
                 System.out.println(countryName+"|"+regionID);
             }
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}