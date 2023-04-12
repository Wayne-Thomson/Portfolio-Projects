package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class saveLoadController {
    @FXML
    private AnchorPane saveLoad;
    public File filenameLoad,filenameSave;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void loadFromFile() {
        Main.companyX.clear();
        Path path = Paths.get(filenameLoad.toURI());

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String input;
            boolean completed;
            int failCount = 0;
            while ((input = br.readLine()) != null) {
                String[] itemPieces = input.split("\t");

                String empNum = itemPieces[0];
                String empNI = itemPieces[1];
                String empPay = itemPieces[2];
                String empRole = itemPieces[3];
                String empDep = itemPieces[4];
                String empLM = itemPieces[5];
                String empName = itemPieces[6];

                completed = Main.companyX.addMember(Integer.parseInt(empNum), empNI, Integer.parseInt(empPay), empRole, empDep, empLM, empName);
                if (!completed) failCount++;
            }
        } catch (Exception e) {
            //Failed to Load
        }
    }

    public void saveToFile() {
        Path path = Paths.get(filenameSave.toURI());

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Employee employee : Main.companyX.getEmployeesInCompany()) {
                System.out.println("Entered");
                bw.write(String.format("%d\t%s\t%d\t%s\t%s\t%s\t%s",
                        employee.getEmployeeNumber(),
                        employee.getNationalInsuranceNumber(),
                        employee.getPayPA(),
                        employee.getRoleAndDepartment()[0],
                        employee.getRoleAndDepartment()[1],
                        employee.getLineManager(),
                        employee.getEmployeeName()));
                bw.newLine();
            }
        } catch (Exception e) {
            // Failed to save
        }

    }

    @FXML
    public void handleLoadLocation() {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Load Employee Database");
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("EMDB","*.EMDB"),
                new FileChooser.ExtensionFilter("All","*.*")
        );
        try {
            File file = chooser.showOpenDialog(saveLoad.getScene().getWindow());
            System.out.println(file.getClass());
            filenameLoad = file;
        } catch (Exception e) {
            //Failed to read location
        }
    }

    @FXML
    public void handleSaveLocation() {
        FileChooser writer = new FileChooser();
        writer.setTitle("Save Employee Database");
        writer.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("EMDB","*.EMDB"),
                new FileChooser.ExtensionFilter("All","*.*")
        );
        try {
            File file = writer.showSaveDialog(saveLoad.getScene().getWindow());
            System.out.println(file.getClass());
            filenameSave = file;
        } catch (Exception e) {
            //Failed to read location
        }
    }

}
