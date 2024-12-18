package org.example.airlinesystem.dao;

import org.example.airlinesystem.Controller.FlightsControl;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class SearchDAO {

    public List<FlightsControl> searchFlights(String departure, String arrival, String date, String flightClass) throws SQLException {
        List<FlightsControl> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE departure_city LIKE ? AND arrival_city LIKE ? AND departure_date LIKE ?";

        // Если flightClass не пустой, добавляем условие в запрос
        if (flightClass != null && !flightClass.isEmpty()) {
            query += " AND flight_class = ?";
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departure.isEmpty() ? "%" : departure);
            statement.setString(2, arrival.isEmpty() ? "%" : arrival);
            statement.setString(3, date.isEmpty() ? "%" : date);

            // Если передан класс рейса, добавляем его в запрос
            if (flightClass != null && !flightClass.isEmpty()) {
                statement.setString(4, flightClass);
            }

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                flights.add(new FlightsControl(
                        resultSet.getString("flight_id"),
                        resultSet.getString("departure_city"),
                        resultSet.getString("arrival_city"),
                        resultSet.getString("departure_time"),
                        resultSet.getString("arrival_time"),
                        resultSet.getDouble("price")
                ));
            }
        }
        return flights;
    }


}
