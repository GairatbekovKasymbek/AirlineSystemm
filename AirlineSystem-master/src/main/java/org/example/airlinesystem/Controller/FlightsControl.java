package org.example.airlinesystem.Controller;

public class FlightsControl {
    private String flightId;         // ID рейса
    private String departureCity;    // Город отправления
    private String arrivalCity;      // Город прибытия
    private String departureTime;    // Время отправления
    private String arrivalTime;      // Время прибытия
    private double price;

    // Конструктор
    public FlightsControl(String flightId, String departureCity, String arrivalCity,
                          String departureTime, String arrivalTime, double price) {
        this.flightId = flightId;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }
    public FlightsControl() {}


    // Геттеры и сеттеры
    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
