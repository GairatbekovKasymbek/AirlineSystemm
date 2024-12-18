package org.example.airlinesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.airlinesystem.dao.LoginDAO;

import java.io.IOException;
import java.util.Objects;

public class Login {

    @FXML
    private Button loginButton; // Кнопка для входа

    @FXML
    private Button backButton; // Кнопка для возврата

    @FXML
    private TextField enterEmail; // Поле для ввода email

    @FXML
    private PasswordField passwordField; // Поле для ввода пароля

    @FXML
    private Label errorLabel; // Метка для отображения ошибок

    private final LoginDAO loginDAO = new LoginDAO(); // Экземпляр LoginDAO

    // Метод, вызываемый при нажатии на кнопку "Login"
    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String email = enterEmail.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Все поля должны быть заполнены!");
            errorLabel.setStyle("-fx-text-fill: red;");
        } else {
            boolean isValid = loginDAO.validate(email, password);
            if (isValid) {
                errorLabel.setText("Вход выполнен успешно!");
                errorLabel.setStyle("-fx-text-fill: green;");
                try {
                    goToCenter(event);  // Переход в Center.fxml
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                errorLabel.setText("Неверный email или пароль!");
                errorLabel.setStyle("-fx-text-fill: red;");
            }
        }
    }

    // Переход на Center.fxml
    @FXML
    public void goToCenter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/airlinesystem/Center.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Переход на Welcome.fxml
    @FXML
    public void goToWelcome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/org/example/airlinesystem/Welcome.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}