package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class removeEmployeeController {
    @FXML
    private TextField removeUserID;
    @FXML
    private Label nameRemove,departmentRemove,roleRemove,salaryRemove,lineManagerRemove,employeeNumberRemove,hasRemoved;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        nameRemove.setText("Name: ");
        departmentRemove.setText("Department: ");
        roleRemove.setText("Role: ");
        salaryRemove.setText("Salary PA: ");
        lineManagerRemove.setText("Line Manager: ");
        employeeNumberRemove.setText("Employee Number: ");
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void removePullUser() {
        hasRemoved.setText("");
        ArrayList<String> employeeDetails = Main.companyX.findEmployee(Integer.parseInt(removeUserID.getText()));
        if(employeeDetails != null && employeeDetails.size() > 0) {
            nameRemove.setText("Name: " + employeeDetails.get(0));
            departmentRemove.setText("Department: " + employeeDetails.get(1));
            roleRemove.setText("Role: " + employeeDetails.get(2));
            salaryRemove.setText("Salary PA: " + employeeDetails.get(3));
            lineManagerRemove.setText("Line Manager: " + employeeDetails.get(4));
            employeeNumberRemove.setText("Employee Number: " + employeeDetails.get(5));
        }
        System.out.println("Code hit end, should be updated.");
    }

    @FXML
    public void removeUser() {
        boolean success = Main.companyX.removeMember(Integer.parseInt(removeUserID.getText()));
        if(success){
            hasRemoved.setText("Employee: " + removeUserID.getText() + " Removed Successfully");
        } else {
            hasRemoved.setText("Employee: " + removeUserID.getText() + " Removal failed, check the used ID");
        }
        nameRemove.setText("Name: ");
        departmentRemove.setText("Department: ");
        roleRemove.setText("Role: ");
        salaryRemove.setText("Salary PA: ");
        lineManagerRemove.setText("Line Manager: ");
        employeeNumberRemove.setText("Employee Number: ");
        removeUserID.clear();
    }

    @FXML
    public void clearDatabase() {
        Main.companyX.clear();

    }
}
