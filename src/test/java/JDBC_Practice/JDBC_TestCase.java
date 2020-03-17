package JDBC_Practice;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBC_TestCase {

    Connection connection;
    Statement statement;
    ResultSet resultSet;


    @BeforeMethod
    public void setUp() throws SQLException {
        String url = "jdbc:oracle:thin:@54.209.157.196:1521:xe",
                username = "hr",
                password = "hr";
       connection= DriverManager.getConnection(url,username, password);
       statement = connection.createStatement();
    }

    @Test(description = "verify that Laylo salary is greater than Seyfo salary")
    public void test_1() throws SQLException {
        int seyfoSalary=0;
        int layloSalary=0;

          String query="select * from testers";
       resultSet=statement.executeQuery(query);
       while(resultSet.next()){
         String TestersName = resultSet.getString("name");
         int testersSalary=resultSet.getInt(3);
         //System.out.println("Testers Names: "+TestersName+" Salary is: "+testersSalary);

         if(TestersName.equals("Laylo")){
             layloSalary=testersSalary;
         }
         if(TestersName.equals("Seyfo")){
             seyfoSalary=testersSalary;}

       }
        System.out.println("laylo salary is : "+ layloSalary);
        System.out.println("seyfo salary is :"+ seyfoSalary);

        Assert.assertTrue(layloSalary>seyfoSalary,"test fail");
        System.out.println("test past" );



    }
    @AfterMethod
    public void tearDown() throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
