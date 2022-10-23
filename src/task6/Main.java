package task6;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/testbase";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Vova280798"; // "root";
    // Присваиваем запрос константе GET_ALL
    private static final String GET_ALL = "SELECT * FROM testtable";
    private static final String INSERT = "INSERT INTO testtable(firstName, lastName )  VALUES(?,?)";

    public static void main(String[] args) {
        registerDriver();
        setTable("Volodymyr", "Ostash");
        setTable("Oleg", "Ivanov");
        setTable("Sofia", "Duda");
        setTable("Pavlo", "Dobushchak");

        getAll();

    }

    private static void setTable (String firstName, String lastName) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            statement = connection.prepareStatement(INSERT);

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement = connection.prepareStatement(GET_ALL);

            ResultSet resultSet_1 = statement.executeQuery();

            while (resultSet_1.next()) {
                String firstName  = resultSet_1.getString("firstName");
                String lastName  = resultSet_1.getString("lastName");

                System.out.println( firstName + " " + lastName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void registerDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

