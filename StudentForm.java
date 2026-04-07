import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StudentForm extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Validated Registration Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label headerLabel = new Label("Student Registration");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        grid.add(headerLabel, 0, 0, 2, 1);

        // Name Field
        Label nameLabel = new Label("Full Name:");
        grid.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        grid.add(nameField, 1, 1);

        // Email Field
        Label emailLabel = new Label("Gmail ID:");
        grid.add(emailLabel, 0, 2);
        TextField emailField = new TextField();
        grid.add(emailField, 1, 2);

        // Department Choice
        Label deptLabel = new Label("Department:");
        grid.add(deptLabel, 0, 3);
        ComboBox<String> deptComboBox = new ComboBox<>();
        deptComboBox.getItems().addAll("Computer Science", "Data Science", "IT");
        deptComboBox.setValue("Computer Science"); // Default value
        grid.add(deptComboBox, 1, 3);

        Button submitBtn = new Button("Register");
        grid.add(submitBtn, 1, 4);

        // --- ADDED VALIDATION LOGIC HERE ---
        submitBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();

            // 1. Check if fields are empty
            if (name.isEmpty() || email.isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Form Error!", "Fields cannot be empty.");
                return;
            }

            // 2. Validate Name (Letters and spaces only, min 2 chars)
            if (!name.matches("[a-zA-Z\\s]{2,50}")) {
                showAlert(Alert.AlertType.ERROR, "Invalid Name", "Name must contain only letters (min 2).");
                return;
            }

            // 3. Validate Gmail specifically
            // Regex: must end with @gmail.com
            if (!email.matches("^[a-zA-Z0-9.]+@gmail\\.com$")) {
                showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please use a valid @gmail.com address.");
                return;
            }

            // Success Message
            String successMsg = "Name: " + name + "\nEmail: " + email + "\nDept: " + deptComboBox.getValue();
            showAlert(Alert.AlertType.INFORMATION, "Registration Successful", successMsg);
        });

        Scene scene = new Scene(grid, 450, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}