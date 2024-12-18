package org.example.airlinesystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.example.airlinesystem.dao.RegistrationDAO;

import java.io.IOException;
import java.util.Objects;

public class Registration {

    @FXML private TextField nameField;
    @FXML private TextField surnameField;
    @FXML private RadioButton maleRadio;
    @FXML private RadioButton femaleRadio;
    @FXML private TextField ageField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    // Очистка всех полей
    @FXML
    public void clearFields(ActionEvent event) {
        nameField.clear();
        surnameField.clear();
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        ageField.clear();
        emailField.clear();
        passwordField.clear();
    }

    // Сохранение данных в базу данных
    @FXML
    public void registerUser(ActionEvent event) {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        String age = ageField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        // Проверка на пустые поля
        if (name.isEmpty() || surname.isEmpty() || age.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Please fill all fields!");
            return;
        }

        // Регистрация пользователя в базе данных
        boolean isRegistered = RegistrationDAO.registerUser(name, surname, gender, age, email, password);

        if (isRegistered) {
            showAlert("Registration successful!");
            try {
                goToWelcome(event);
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Failed to go to welcome screen.");
            }
        } else {
            showAlert("Registration failed! Please try again.");
        }
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

    // Всплывающее окно
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.show();
    }
}
