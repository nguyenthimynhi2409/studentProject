package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditDialog {
    private Stage dialogStage;
    private Student student;
    @FXML
    private TextField nameField;
    @FXML
    private TextField nickNameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField classField;
    @FXML
    private TextField ageField;

    private boolean okClicked = false;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "No valid name!\n";
        }
        if (classField.getText() == null || classField.getText().length() == 0) {
            errorMessage += "No valid class!\n";
        }

        if (nickNameField.getText() == null || nickNameField.getText().length() == 0) {
            errorMessage += "No valid nickname!\n";
        }
        if (idField.getText() == null || idField.getText().length() == 0) {
            errorMessage += "No valid id!\n";
        }
        if (ageField.getText() == null || ageField.getText().length() == 0) {
            errorMessage += "No valid age!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(ageField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid age (must be an integer)!\n";
            }
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
    public void setStudent(Student student) {
        this.student = student;
        nameField.setText(student.getName());
        nickNameField.setText(student.getNickName());
        classField.setText(student.getNameClass());
        idField.setText(student.getId());
        ageField.setText(Integer.toString(student.getAge()));
        birthdayField.setText(DateUtil.format(student.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            student.setName(nameField.getText());
            student.setID(idField.getText());
            student.setNickName(nickNameField.getText());
            student.setAge(Integer.parseInt(ageField.getText()));
            student.setNameClass(classField.getText());
            student.setBirthday(DateUtil.parse(birthdayField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
