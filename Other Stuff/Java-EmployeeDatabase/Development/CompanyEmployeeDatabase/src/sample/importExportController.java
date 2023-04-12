package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class importExportController {
    @FXML
    private AnchorPane importExport;
    @FXML
    private Label importStatus;
    @FXML
    private Label exportStatus;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void handleImport() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Import Employee CSV");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("All","*.*")
        );
        try {
            File file = chooser.showOpenDialog(importExport.getScene().getWindow());
            int ImpCount = Main.companyX.readCSVFile(file.toURI());
            importStatus.setText("Imported: " + ImpCount + " new Employees to database.");
             } catch (Exception e) {
            importStatus.setText("FAILED!");
        }
    }


    public void handleExport() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export Employee CSV");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV","*.csv"),
                new FileChooser.ExtensionFilter("All","*.*")
        );
        try {
            File file = chooser.showSaveDialog(importExport.getScene().getWindow());
            int ExpCount = Main.companyX.writeCSVFile(file.toURI());
            exportStatus.setText("Exported: " + ExpCount + " Employees to: " + file.toURI());
        } catch (Exception e) {
            importStatus.setText("FAILED!");
        }

    }

}
