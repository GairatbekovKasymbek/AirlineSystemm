package org.example.airlinesystem.Controller;

public class TicketControl {
    private int ticketID;       // Ticket ID
    private int flightID;       // Flight ID
    private int userID;         // Customer/User ID
    private String seatNumber1;  // Seat Number
    private String issueDate;   // Issue Date
    private String departureTime; // Departure Time
    private String status;      // Ticket Status

    // Constructor
    public TicketControl(int ticketID, int flightID, int userID, String seatNumber,
                  String issueDate, String departureTime, String status) {
        this.ticketID = ticketID;
        this.flightID = flightID;
        this.userID = userID;
        this.seatNumber1 = seatNumber;
        this.issueDate = issueDate;
        this.departureTime = departureTime;
        this.status = status;
    }

    // Getters and Setters
    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSeatNumber() {
        return seatNumber1;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber1 = seatNumber;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
