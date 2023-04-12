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
import java.util.Objects;

public class addEmployeeController {
    @FXML
    private TextField addName,addLM,addDep,addRole,addPPA,addEN,addNI;
    @FXML
    private Label addSuccess;

    @FXML
    public void switchToMainMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainMenu.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void addUser() {
        String textOne = addEN.getText();
        String textTwo = addNI.getText();
        String textThree = addPPA.getText();
        String textFour = addRole.getText();
        String textFive = addDep.getText();
        String textSix = addLM.getText();
        String textSeven = addName.getText();
        // Check boxes all have content
        boolean disableButton = textOne.isEmpty() || textOne.trim().isEmpty() || textTwo.isEmpty() || textTwo.trim().isEmpty() ||
                textThree.isEmpty() || textThree.trim().isEmpty() || textFour.isEmpty() || textFour.trim().isEmpty() ||
                textFive.isEmpty() || textFive.trim().isEmpty() || textSix.isEmpty() || textSix.trim().isEmpty() ||
                textSeven.isEmpty() || textSeven.trim().isEmpty();
        // Check correct type in int boxes
        try {
            Integer.parseInt(textOne);
            Integer.parseInt(textThree);
        } catch (Exception e) {
            disableButton = true;
        }
        if(!disableButton){
            boolean success = Main.companyX.addMember(Integer.parseInt(addEN.getText()), addNI.getText(), Integer.parseInt(addPPA.getText()),
                addRole.getText(), addDep.getText(), addLM.getText(), addName.getText());
            addSuccess.setText("Employee: " + addName.getText() + " added successfully");
            addEN.clear();
            addNI.clear();
            addPPA.clear();
            addRole.clear();
            addDep.clear();
            addLM.clear();
            addName.clear();
            String successText = success ? "Employee: " + addName.getText() + " has been added to database." : "Employee " + addName.getText() + " failed to add, check that employee does not already exist!";
            addSuccess.setText(successText);
        } else {
            addSuccess.setText("Failed to add employee, one or more value are incorrect or missing. Check and try again.");
        }

    }

    /*
     * Not currently used, had planned on disabling button if conditions not met.
     * Maybe come back to this, but for the moment error feedback when clicking submit.
     */
    /*public void handleKeyReleased() {
        String textOne = addEN.getText();
        String textTwo = addNI.getText();
        String textThree = addPPA.getText();
        String textFour = addRole.getText();
        String textFive = addDep.getText();
        String textSix = addLM.getText();
        String textSeven = addName.getText();
        // Check boxes all have content
        boolean disableButton = textOne.isEmpty() || textOne.trim().isEmpty() || textTwo.isEmpty() || textTwo.trim().isEmpty() ||
                textThree.isEmpty() || textThree.trim().isEmpty() || textFour.isEmpty() || textFour.trim().isEmpty() ||
                textFive.isEmpty() || textFive.trim().isEmpty() || textSix.isEmpty() || textSix.trim().isEmpty() ||
                textSeven.isEmpty() || textSeven.trim().isEmpty();
        // Check correct type in int boxes
        try {
            Integer.parseInt(textOne);
            Integer.parseInt(textThree);
        } catch (Exception e) {
            disableButton = true;
        }

        confirmButton.setDisable(disableButton);
    }*/
}
