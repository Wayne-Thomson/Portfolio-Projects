package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class viewEmployeeController {

    @FXML
    public Label nameView,departmentView,roleView,salaryView,lineManagerView,employeeNumberView,natInView;
    @FXML
    public TextField enterEmployeeNameView, enterEmployeeNumberView, enterEmployeeLMView, enterEmployeeRoleView;
    @FXML
    public TextField enterEmployeeDepView, enterMinSalView, enterMaxSalView, enterNIView;
    @FXML
    private ListView employeeView;

    public void initialize() {
    }

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("mainMenu.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void clearView() {
        // Do clear
        employeeView.getItems().clear();
    }

    @FXML
    public void populateView() {
        TreeSet<Employee> employees = Main.companyX.getEmployeesInCompany();
        employeeView.getItems().setAll(employees);
        employeeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void filterEmployees() {
        String name = enterEmployeeNameView.getText().equals("") ? null : enterEmployeeNameView.getText();
        String number = enterEmployeeNumberView.getText().equals("") ? null : enterEmployeeNumberView.getText();
        String lineMan = enterEmployeeLMView.getText().equals("") ? null : enterEmployeeLMView.getText();
        String role = enterEmployeeRoleView.getText().equals("") ? null : enterEmployeeRoleView.getText();
        String dep = enterEmployeeDepView.getText().equals("") ? null : enterEmployeeDepView.getText();
        String min = enterMinSalView.getText().equals("") ? null : enterMinSalView.getText();
        String max = enterMaxSalView.getText().equals("") ? null : enterMaxSalView.getText();
        String ni = enterNIView.getText().equals("") ? null : enterNIView.getText();
        ArrayList<String> filterConditions = new ArrayList<>();
        Collections.addAll(filterConditions, name, number, lineMan, role, dep, min, max, ni);
        TreeSet<Employee> filteredList = Main.companyX.filterMembers(filterConditions);

        employeeView.getItems().setAll(filteredList);
        employeeView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        clearFilters();
    }

    private void clearFilters() {
        enterEmployeeNameView.clear();
        enterEmployeeNumberView.clear();
        enterEmployeeLMView.clear();
        enterEmployeeRoleView.clear();
        enterEmployeeDepView.clear();
        enterMinSalView.clear();
        enterMaxSalView.clear();
        enterNIView.clear();
    }

    @FXML
    public void handleClickListView() {
        Employee employeeSelected = (Employee) employeeView.getSelectionModel().getSelectedItem();
        nameView.setText("Name: " + employeeSelected.getEmployeeName());
        departmentView.setText("Department: " + employeeSelected.getRoleAndDepartment()[1]);
        roleView.setText("Role: " + employeeSelected.getRoleAndDepartment()[0]);
        salaryView.setText("Salary: " + employeeSelected.getPayPA());
        lineManagerView.setText("Line Manager: " + employeeSelected.getLineManager());
        employeeNumberView.setText("Employee Number: " + employeeSelected.getEmployeeNumber());
        natInView.setText("NI Number: " + employeeSelected.getNationalInsuranceNumber());
    }
}
