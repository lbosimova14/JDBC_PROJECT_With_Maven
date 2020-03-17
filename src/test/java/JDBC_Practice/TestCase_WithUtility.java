package JDBC_Practice;

import JDBCUtility.Jdbc_Utility;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

public class TestCase_WithUtility {

    @Test
    public void test_1() throws SQLException {
        ResultSet resultSet= Jdbc_Utility.getResult("select first_name||' '||last_name as \"Full Name\" from employees");


        while(resultSet.next()){
            System.out.println("full name is :"+resultSet.getString("Full name"));

        }
    }

    @Test(description = "verify that Steven King has the highest salary")
    public void testcase_2() throws SQLException {
        ResultSet resultSet = Jdbc_Utility.getResult("select * from employees");

        List<Integer> salaries = new ArrayList<Integer>();
        for (int i=0; resultSet.next();)
            salaries.add(resultSet.getInt("salary"));
            Collections.sort(salaries);
            int maxSalary = salaries.get(salaries.size() - 1);
            System.out.println("max salary is: " + maxSalary);

            String richestGuy="";
             resultSet = Jdbc_Utility.getResult("select * from employees");
            while (resultSet.next()){
                int money=resultSet.getInt("salary");
                String fullname= resultSet.getString(2)+" "+resultSet.getString(3);
                if(money==maxSalary){
                    richestGuy=fullname;
                }
            }
            System.out.println("Richest Guy is : "+richestGuy);
            Assert.assertEquals(richestGuy,"Steven King");

            resultSet=Jdbc_Utility.getResult("Select * from Locations");
            ResultSetMetaData rsm =resultSet.getMetaData();
            System.out.println("Total number of columns number: "+ rsm.getColumnCount());
            System.out.println("Third column name: "+rsm.getColumnName(3));

            String [] ColumnNames=new String [rsm.getColumnCount()];

            for(int j=0;j<ColumnNames.length;j++){
                ColumnNames[j]=rsm.getColumnName(j+1);

            }
            System.out.println(Arrays.toString(ColumnNames));



    }

}
