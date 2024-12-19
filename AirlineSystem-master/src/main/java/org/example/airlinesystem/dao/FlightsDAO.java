package org.example.airlinesystem.dao;

import org.example.airlinesystem.Controller.FlightsControl;
import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightsDAO {

    // Метод для поиска рейсов с возможностью получения всех рейсов
    public List<FlightsControl> searchFlights(String departureCity, String arrivalCity, String date) throws SQLException {
        List<FlightsControl> flights = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM flights");

        // Добавляем условия поиска только если параметры заданы
        boolean hasConditions = false;

        if (departureCity != null && !departureCity.isEmpty()) {
            query.append(" WHERE departure_city = ?");
            hasConditions = true;
        }

        if (arrivalCity != null && !arrivalCity.isEmpty()) {
            query.append(hasConditions ? " AND" : " WHERE");
            query.append(" arrival_city = ?");
            hasConditions = true;
        }

        if (date != null && !date.isEmpty()) {
            query.append(hasConditions ? " AND" : " WHERE");
            query.append(" departure_time = ?");
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {

            // Устанавливаем параметры запроса, если они есть
            int paramIndex = 1;
            if (departureCity != null && !departureCity.isEmpty()) {
                preparedStatement.setString(paramIndex++, departureCity);
            }
            if (arrivalCity != null && !arrivalCity.isEmpty()) {
                preparedStatement.setString(paramIndex++, arrivalCity);
            }
            if (date != null && !date.isEmpty()) {
                preparedStatement.setString(paramIndex++, date);
            }

            ResultSet resultSet = preparedStatement.executeQuery();

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
