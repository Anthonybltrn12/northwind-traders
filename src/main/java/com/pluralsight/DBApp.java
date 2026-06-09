package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBApp {
    public static void main(String[] args) {

        //create a datasource so we can connect to the database
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        //fine for now but dont add credentials to code
        //better strategies exist
        dataSource.setUsername("root");
        dataSource.setPassword("yearup26");

        String sql = """
                select
                    *
                from
                    products
                """;
        //  this is like clicking connect to server in workbench
        try(Connection connection = dataSource.getConnection();
            //this is like typing the query in the query windows
            PreparedStatement stmt = connection.prepareStatement(sql);
        //like clicking the lightning bolt
            ResultSet results = stmt.executeQuery();
            ){

            while(results.next()){

                String title = results.getString("productname");

                System.out.println(title);
                System.out.println();
            }


        }catch(SQLException e){
            System.out.println("Querying the data failed.");
            throw new RuntimeException(e);
        }


    }
}
