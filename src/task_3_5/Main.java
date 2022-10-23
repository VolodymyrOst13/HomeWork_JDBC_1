package task_3_5;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/MyJoinsDB";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Vova280798";

    private static final String first = "SELECT phone, address FROM MyJoinsDB.Employee JOIN MyJoinsDB.workingStatus ON id = EmployeeID";
    private static final String second = "SELECT dateBirth, phone FROM MyJoinsDB.Employee JOIN MyJoinsDB.workingStatus ON famStatus = 'не одружений' and id = EmployeeID";
    private static final String third = "SELECT phone , dateBirth  FROM MyJoinsDB.Employee JOIN MyJoinsDB.position ON position = 'менеджер' and id = PositionID JOIN MyJoinsDB.workingStatus ON id = EmployeeID";


    public static void main(String[] args) {

        registerDriver();

        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        PreparedStatement statement3 = null;


        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

            statement1 = connection.prepareStatement(first);
            statement2 = connection.prepareStatement(second);
            statement3 = connection.prepareStatement(third);

            ResultSet resultSet1 = statement1.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();
            ResultSet resultSet3 = statement3.executeQuery();

            System.out.println();
            System.out.println("Employees info about phone & live location");
            System.out.println();

            while (resultSet1.next()) {
                String address = resultSet1.getString("address");
                String phone = resultSet1.getString("phone");

                System.out.println(phone + "\n" + address);
                System.out.println();
            }

            System.out.println();
            System.out.println("Info about don't married employee phone number ");

            while (resultSet2.next()) {
                String phone = resultSet2.getString("phone");
                String dateOfBirth = resultSet2.getString("dateBirth");

                System.out.println(phone + "\n" + dateOfBirth);
                System.out.println();
            }

            System.out.println();
            System.out.println("Info about all managers: phone number & date of birth");

            while (resultSet3.next()) {
                String phone = resultSet3.getString("phone");
                String dateOfBirth = resultSet3.getString("dateBirth");

                System.out.println(phone + "\n" + dateOfBirth);
                System.out.println();
            }
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                statement1.close();
                statement2.close();
                statement3.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }
    private static void registerDriver(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded success");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}