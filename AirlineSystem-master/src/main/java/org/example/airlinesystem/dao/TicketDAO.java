package org.example.airlinesystem.dao;

import org.example.airlinesystem.Controller.TicketControl;
import org.example.DatabaseConnection;
import org.example.airlinesystem.Controller.TicketControl;

import java.sql.*;

public class TicketDAO {

    public TicketControl searchTicketById(int ticketId) throws SQLException {
        String query = "SELECT * FROM tickets WHERE ticket_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, ticketId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new TicketControl(
                        resultSet.getInt("ticket_id"),
                        resultSet.getInt("flight_id"),
                        resultSet.getInt("user_id"),
                        resultSet.getString("seat_number"),
                        resultSet.getString("issue_date"),
                        resultSet.getString("departure_date"),
                        resultSet.getString("status")
                );
            }
        }
        return null; // No ticket found
    }
}
