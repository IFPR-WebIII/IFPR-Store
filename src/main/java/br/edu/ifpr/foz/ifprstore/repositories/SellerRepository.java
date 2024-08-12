package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.foz.ifprstore.models.Seller;

import java.sql.*;
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

            }

            result.close();


        } catch (SQLException e) {

            throw new RuntimeException(e);

        } finally {
            ConnectionFactory.closeConnection();
        }


        return sellers;
    }

    public Seller insert(Seller seller){

        String sql = "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " +
                "VALUES(?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, Date.valueOf(seller.getBirthDate()));
            statement.setDouble(4 ,seller.getBaseSalary());
            statement.setInt(5, 1);

            Integer rowsInserted = statement.executeUpdate();

            if(rowsInserted > 0){

                ResultSet id = statement.getGeneratedKeys();

                id.next();

                Integer sellerId = id.getInt(1);

                System.out.println("Rows inserted: " + rowsInserted);
                System.out.println("Id: " + sellerId);

                seller.setId(sellerId);

            }


        } catch (Exception e){
            throw new DatabaseException(e.getMessage());
        }

        return seller;
    }

    public void updateSalary(Integer departmentId, Double bonus){

        String sql = "UPDATE seller SET BaseSalary = BaseSalary + ? WHERE DepartmentId = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);//crl+alt+v
            statement.setDouble(1, bonus);
            statement.setInt(2, departmentId);

            Integer rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0){
                System.out.println("Rows updated: " + rowsUpdated);
            }

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }

    }

    public void delete(Integer id){

        String sql = "DELETE FROM seller WHERE Id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0){
                System.out.println("Rows deleted: " + rowsDeleted);
            }

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
    }

}
