package org.example.airlinesystem.Controller;

import org.example.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchControl {
    private String departureCity;   // Город отправления
    private String arrivalCity;     // Город прибытия
    private String date;            // Дата поиска
    private int customerId;         // ID клиента, выполняющего поиск

    // Конструктор
    public SearchControl(String departureCity, String arrivalCity, String date, int customerId) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.date = date;
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Метод поиска рейсов
    public List<FlightsControl> searchFlights() throws SQLException {
        List<FlightsControl> flights = new ArrayList<>();
        String query = "SELECT * FROM flights WHERE departure_city LIKE ? AND arrival_city LIKE ? AND departure_date LIKE ?";

        // Открытие подключения к базе данных
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, departureCity.isEmpty() ? "%" : departureCity);
            statement.setString(2, arrivalCity.isEmpty() ? "%" : arrivalCity);
            statement.setString(3, date.isEmpty() ? "%" : date);

            // Выполнение запроса
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FlightsControl flight = new FlightsControl(
                            resultSet.getString("id"),
                            resultSet.getString("departure_city"),
                            resultSet.getString("arrival_city"),
                            resultSet.getString("departure_time"),
                            resultSet.getString("arrival_time"),
                            resultSet.getDouble("price")
                    );
                    flights.add(flight);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Ошибка при выполнении запроса");
        }

        return flights;
    }
}
