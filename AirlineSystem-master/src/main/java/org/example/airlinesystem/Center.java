package org.example.airlinesystem;

import org.example.airlinesystem.dao.FlightsDAO;
import org.example.airlinesystem.dao.TicketDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.airlinesystem.Controller.FlightsControl;
import org.example.airlinesystem.Controller.TicketControl;
import org.example.airlinesystem.model.Ticket;


import java.sql.SQLException;
import java.util.List;

public class Center {

    // Search Tab
    @FXML
    private TextField departureField; // Поле для ввода города отправления
    @FXML
    private TextField contetShow;
    @FXML
    private TextField arrivalField; // Поле для ввода города прибытия
    @FXML
    private TextField datePicker; // Поле для выбора даты
    @FXML
    private TableView<FlightsControl> searchTableView; // Таблица для отображения рейсов
    @FXML
    private TableColumn<FlightsControl, String> departureColumn; // Столбец для города отправления
    @FXML
    private TableColumn<FlightsControl, String> arrivalColumn; // Столбец для города прибытия
    @FXML
    private TableColumn<FlightsControl, String> dateColumn; // Столбец для даты рейса
    @FXML
    private TableColumn<FlightsControl, Double> priceColumn; // Столбец для стоимости рейса
    @FXML
    private RadioButton economyRadioButton; // Кнопка для выбора эконом-класса
    @FXML
    private RadioButton businessRadioButton; // Кнопка для выбора бизнес-класса
    @FXML
    private RadioButton firstClassRadioButton; // Кнопка для выбора первого класса

    // Flights Tab
    @FXML
    private TableView<FlightsControl> flightsTableView; // Таблица для отображения всех рейсов
    @FXML
    private TableColumn<FlightsControl, String> flightIdColumn; // Столбец для ID рейса
    @FXML
    private TableColumn<FlightsControl, String> flightNumberColumn; // Столбец для номера рейса
    @FXML
    private TableColumn<FlightsControl, String> depCityColumn; // Столбец для города отправления
    @FXML
    private TableColumn<FlightsControl, String> arrCityColumn; // Столбец для города прибытия
    @FXML
    private TableColumn<FlightsControl, String> depTimeColumn; // Столбец для времени отправления
    @FXML
    private TableColumn<FlightsControl, String> arrTimeColumn; // Столбец для времени прибытия

    // Ticket Tab
    @FXML
    private TextField ticketIdField; // Поле для ввода Ticket ID
    @FXML
    private Label ticketIdLabel;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label flightIdLabel;
    @FXML
    private Label seatNumberLabel;
    @FXML
    private Label departureTimeLabel1;
    @FXML
    private Label statusLabel;
    @FXML
    private Label issueLabel;
    // Метод для поиска рейсов в "Search" Tab
    @FXML
    private void onSearchFlights() {
        String departure = departureField.getText();
        String arrival = arrivalField.getText();
        String date = datePicker.getText();
        String flightClass = null;

        // Проверяем, какой класс выбран
        if (!economyRadioButton.isSelected() && !businessRadioButton.isSelected() && !firstClassRadioButton.isSelected()) {
            // Handle the case where no class is selected
            System.out.println("Error: You must select a flight class.");
        } else {
            // Check which radio button is selected and assign the corresponding class
            if (economyRadioButton.isSelected()) {
                flightClass = "Economy";
            } else if (businessRadioButton.isSelected()) {
                flightClass = "Business";
            } else if (firstClassRadioButton.isSelected()) {
                flightClass = "First Class";
            }
        }

        FlightsDAO flightsDAO = new FlightsDAO();
        try {
            // Передаем все 4 параметра в метод searchFlights
            List<FlightsControl> flights = flightsDAO.searchFlights(departure, arrival, date);
            ObservableList<FlightsControl> observableFlights = FXCollections.observableArrayList(flights);
            searchTableView.setItems(observableFlights);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Метод для загрузки всех рейсов в "Flights" Tab
    @FXML
    private void loadAllFlights() {
        FlightsDAO flightsDAO = new FlightsDAO();
        try {
            List<FlightsControl> flights = flightsDAO.searchFlights("", "", "");  // Пустые параметры для получения всех рейсов
            ObservableList<FlightsControl> observableFlights = FXCollections.observableArrayList(flights);
            flightsTableView.setItems(observableFlights);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для поиска билетов в "Ticket" Tab

    // This method is triggered by a button click in the FXML file
    @FXML
    public void onSearchTicket() {
        String ticketId = ticketIdField.getText();
        if (ticketId.isEmpty()) {
            System.out.println("Please enter a Ticket ID.");
            return;
        }

        try {
            // Create an instance of TicketDAO to fetch the ticket details
            TicketDAO ticketDAO = new TicketDAO();
            TicketControl ticket = ticketDAO.searchTicketById(Integer.parseInt(ticketId));

            if (ticket != null) {
                // Update the labels with ticket details if found
                ticketIdLabel.setText(String.valueOf(ticket.getTicketID()));
                userIdLabel.setText(String.valueOf(ticket.getUserID()));
                flightIdLabel.setText(String.valueOf(ticket.getFlightID()));
                seatNumberLabel.setText(ticket.getSeatNumber());
                issueLabel.setText(ticket.getIssueDate());
                departureTimeLabel1.setText(ticket.getDepartureTime());
                statusLabel.setText(ticket.getStatus());
            } else {
                System.out.println("Ticket not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
