package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.models.Seller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SellerRepository {

    private Connection connection;

    public SellerRepository(){

        connection = ConnectionFactory.getConnection();

    }

    public List<Seller> getSellers(){

        List<Seller> sellers = new ArrayList<>();

        try {

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM seller");

            while (result.next()){

                Seller seller = new Seller();

                seller.setId(result.getInt("Id"));
                seller.setName(result.getString("Name"));
                seller.setEmail(result.getString("Email"));
                seller.setBirthDate(result.getDate("BirthDate").toLocalDate());
                seller.setBaseSalary(result.getDouble("BaseSalary"));

                sellers.add(seller);


                /*
                System.out.println("Id: " + result.getInt("Id"));
                System.out.println("Name: " + result.getString("Name"));
                System.out.println("Email: " + result.getString("Email"));
                System.out.println("BirthDate: " + result.getDate("BirthDate"));
                System.out.println("BaseSalary: " + result.getInt("BaseSalary"));
                */

            }

            result.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ConnectionFactory.closeConnection();

        return sellers;
    }

}
