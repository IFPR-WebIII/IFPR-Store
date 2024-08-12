package br.edu.ifpr.foz.ifprstore.repositories;

import br.edu.ifpr.foz.ifprstore.connection.ConnectionFactory;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseException;
import br.edu.ifpr.foz.ifprstore.exceptions.DatabaseIntegrityException;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DepartmentRepository {

    Connection connection;

    public DepartmentRepository(){
        connection = ConnectionFactory.getConnection();
    }

    public void delete(Integer id){

        String sql = "DELETE FROM department WHERE Id = ?";

        try {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            Integer rowsDeleted = statement.executeUpdate();

            if(rowsDeleted > 0){
                System.out.println("Rows deleted: " + rowsDeleted);
            }

        } catch (Exception e){
            throw new DatabaseIntegrityException(e.getMessage());
        }

    }
}
