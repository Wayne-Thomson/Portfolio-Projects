package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class updateEmployeeController {

    @FXML
    private TextField updateUserID,searchVar;
    @FXML
    private Label nameUpdate,departmentUpdate,roleUpdate,salaryUpdate,lineManagerUpdate,employeeNumberUpdate,hasUpdated;
    @FXML
    private ChoiceBox choiceBox;


    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void updatePullUser() {
        hasUpdated.setText("");
        ArrayList<String> employeeDetails = Main.companyX.findEmployee(Integer.parseInt(updateUserID.getText()));
        if(employeeDetails != null && employeeDetails.size() > 0) {
            nameUpdate.setText("Name: " + employeeDetails.get(0));
            departmentUpdate.setText("Department: " + employeeDetails.get(1));
            roleUpdate.setText("Role: " + employeeDetails.get(2));
            salaryUpdate.setText("Salary PA: " + employeeDetails.get(3));
            lineManagerUpdate.setText("Line Manager: " + employeeDetails.get(4));
            employeeNumberUpdate.setText("Employee Number: " + employeeDetails.get(5));
        }
        System.out.println("Code hit end, should be updated.");
    }

    @FXML
    public void updateEmployee() {
        String choice = choiceBox.getValue().toString();
        boolean isDone = false;
        switch (choice) {
            case "Line Manager":
                //Do Stuff

                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), searchVar.getText(), (String)null);
                break;

            case "Role":
                //Do Stuff
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), new String[]{searchVar.getText(), null});
                break;

            case "Department":
                //Do Stuff
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), new String[]{null, searchVar.getText()});
                break;

            case "Pay PA":
                //Do Stuff
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), Integer.parseInt(searchVar.getText()));
                break;

            case "Employee Number":
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), null, Integer.parseInt(searchVar.getText()));
                //Do Stuff
                break;

            case "NI":
                //Do Stuff
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), searchVar.getText(), (Integer)null);
                break;
            case "Name":
                //Do Stuff
                isDone = Main.companyX.updateMember(Integer.parseInt(updateUserID.getText()), null, searchVar.getText());
                break;
            default:
                //Do failure Stuff
        }
        if(isDone) {
            hasUpdated.setText("The " + choiceBox.getValue().toString() + " of " + updateUserID.getText() + " has been changed to " + searchVar.getText());
        } else {
            hasUpdated.setText("The Employee " + updateUserID.getText() + " could not be changed!");
        }
        nameUpdate.setText("Name: ");
        departmentUpdate.setText("Department: ");
        roleUpdate.setText("Role: ");
        salaryUpdate.setText("Salary PA: ");
        lineManagerUpdate.setText("Line Manager: ");
        employeeNumberUpdate.setText("Employee Number: ");
        updateUserID.clear();
        searchVar.clear();

    }
}
