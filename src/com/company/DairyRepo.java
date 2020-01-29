package com.company;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DairyRepo implements AutoCloseable {
    //public class PersonRepo {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public DairyRepo() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Dashtikh", "dashti1565");
        connection.setAutoCommit(false);
    }

    public void insert(DairyEnti dairyEnti) throws Exception {
        preparedStatement = connection.prepareStatement("INSERT INTO Mihan (id, name,price) VALUES (?,?,?)");
        preparedStatement.setInt(1, dairyEnti.getId());
        preparedStatement.setString(2, dairyEnti.getName());
        preparedStatement.setInt(3, dairyEnti.getPrice());
        preparedStatement.executeUpdate();
    }

    public void update(DairyEnti personEnti) throws Exception {
        preparedStatement = connection.prepareStatement("UPDATE Mihan SET name =?, price=?, WHERE id=? ");
        preparedStatement.setString(1, personEnti.getName());
        preparedStatement.setInt(2, personEnti.getPrice());
        preparedStatement.setLong(4, personEnti.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(int id) throws Exception {
        preparedStatement = connection.prepareStatement("DELETE FROM Mihan WHERE id=?");
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<DairyEnti> select() throws Exception {
        preparedStatement = connection.prepareStatement("SELECT * FROM Mihan");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<DairyEnti> carEntiList = new ArrayList<>();
        while (resultSet.next()) {
            DairyEnti carEnti = new DairyEnti(); // call by reference
            carEnti.setmodel(resultSet.getInt("id"));
            carEnti.setName(resultSet.getString("name"));
            carEnti.setprice(resultSet.getInt("price"));
            carEntiList.add(carEnti);
        }
        return carEntiList;
    }

    public void commit() throws Exception {
        connection.commit();
    }

    public void rollback() throws Exception {
        connection.rollback();
    }

    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
