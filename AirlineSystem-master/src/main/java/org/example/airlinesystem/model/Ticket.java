package org.example.airlinesystem.model;

import org.example.airlinesystem.dao.FlightsDAO;
import org.example.airlinesystem.dao.TicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.airlinesystem.Controller.FlightsControl;
import org.example.airlinesystem.Controller.TicketControl;
import org.example.airlinesystem.model.Ticket;

public class Ticket {
    private String ticketID;
    private String userID;
    private String flightID;
    private String seatNumber;
    private String issueDate;
    private String departureTime;
    private String status;

    // Constructor to initialize the Ticket object
    public Ticket(String ticketID, String userID, String flightID, String seatNumber, String issueDate, String departureTime, String status) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.flightID = flightID;
        this.seatNumber = seatNumber;
        this.issueDate = issueDate;
        this.departureTime = departureTime;
        this.status = status;
    }

    // Getter methods for all properties
    public String getTicketID() { return ticketID; }
    public String getUserID() { return userID; }
    public String getFlightID() { return flightID; }
    public String getSeatNumber() { return seatNumber; }
    public String getIssueDate() { return issueDate; }
    public String getDepartureTime() { return departureTime; }
    public String getStatus() { return status; }
}

