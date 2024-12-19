package org.example.airlinesystem.Controller;

import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchControl {
    private String departureCity;   // Город отправления
    private String arrivalCity;     // Город прибытия
    private String date;            // Дата поиска

    // Конструктор
    public SearchControl(String departureCity, String arrivalCity, String date) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
    }

    // Геттеры и сеттеры
    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Метод поиска рейсов
    public List<FlightsControl> searchFlights(String departure, String arrival, String date) throws SQLException {
        List<FlightsControl> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE departure_city LIKE ? " +
                "AND arrival_city LIKE ? " +
                "AND departure_time = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Установка параметров запроса
            statement.setString(1, departure.isEmpty() ? "%" : departure);
            statement.setString(2, arrival.isEmpty() ? "%" : arrival);
            statement.setString(3, date.isEmpty() ? "%" : date);

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
        } catch (SQLException e) {
            // Логгирование ошибки или обработка исключения
            System.err.println("SQL Error: " + e.getMessage());
            throw e; // Пробрасываем исключение дальше
        }
        return flights;
    }
}