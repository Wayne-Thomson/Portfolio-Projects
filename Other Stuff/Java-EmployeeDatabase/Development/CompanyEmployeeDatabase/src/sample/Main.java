package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static Company companyX;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));

        primaryStage.setTitle("Company Employees");
        primaryStage.setScene(new Scene(root, 1400, 968));
        primaryStage.show();
    }


    public static void main(String[] args) {
        companyX = new Company();
        launch(args);
    }
}
