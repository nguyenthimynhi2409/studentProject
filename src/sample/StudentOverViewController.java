package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;


public class StudentOverViewController {
//    private Controller controller;
    private Stage s;
    private ObservableList<Student> studentData= FXCollections.observableArrayList();
    public ObservableList<Student> getStudentData() {
        return studentData;
    }
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> idColumn;
    @FXML
    private TableColumn<Student, Integer> noColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;
    @FXML
    private TableColumn<Student, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> nickNameColumn;
    @FXML
    private RadioButton byName;
    @FXML
    private RadioButton byNickName;
    @FXML
    private RadioButton byId;
    @FXML
    private TextField findField;
    @FXML
    private void initialize() {

        nameColumn.setCellValueFactory(
                cellData -> cellData.getValue().NameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        birthdayColumn.setCellValueFactory(
                cellData -> cellData.getValue().birthdayProperty());
        classColumn.setCellValueFactory(cellData -> cellData.getValue().nameClassProperty());
        nickNameColumn.setCellValueFactory(cellData -> cellData.getValue().nickNameProperty());
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
//        studentTable.setItems(studentData);

        FilteredList<Student> filteredData = new FilteredList<>(studentData,e -> true);
        findField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(student -> {
                if(newValue== null||newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter=newValue.toLowerCase();
                if(byName.isSelected()){
                    if(student.getName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }}
                if(byNickName.isSelected()){
                    if(student.getNickName().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }}
                if(byId.isSelected()){
                    if(student.getId().contains(newValue)){
                        return true;
                    }}

                return false;
            });
        });
        SortedList<Student> sortedData =new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
        studentTable.setItems(sortedData);
    }

//    public void setMainApp(Main mainApp) {
//        this.mainApp = mainApp;
//        studentTable.setItems(mainApp.getStudentData());
//
//    }
//    private FilteredList<Student> filteredData = new FilteredList<>(studentData,s -> true);
////    private SortedList<Student> sortedData =new SortedList<>(filteredData);
//    @FXML
//    private void search(KeyEvent event){
//                findField.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(student -> {
//                if(newValue== null||newValue.isEmpty()){
//                    return true;
//                }
//                String lowerCaseFilter=newValue.toLowerCase();
//                if(byName.isSelected()){
//                    if(student.getName().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }}
//                if(byNickName.isSelected()){
//                    if(student.getNickName().toLowerCase().contains(lowerCaseFilter)){
//                        return true;
//                    }}
//                if(byId.isSelected()){
//                    if(student.getId().contains(newValue)){
//                        return true;
//                    }}
//                return false;
//            });
//        });
//
//        SortedList<Student> sortedData =new SortedList<>(filteredData);
//        sortedData.comparatorProperty().bind(studentTable.comparatorProperty());
//        studentTable.setItems(sortedData);
//    }
    @FXML
    public void handleNew(){
        Student tempStudent= new Student();
        boolean okClicked = showStudentEditDialog(tempStudent);
        if (okClicked) {
            getStudentData().add(tempStudent);
        }

    }
    @FXML
    public void handleDelete(){
//        int selectedIndex=studentTable.getSelectionModel().getSelectedIndex();
//        if(selectedIndex>= 0){
//            studentTable.getItems().remove(selectedIndex);
        Student selectedStudent= studentTable.getSelectionModel().getSelectedItem();
        if(selectedStudent!= null){
            studentData.remove(selectedStudent);
        }else{
            Alert alert= new Alert(Alert.AlertType.WARNING);;
            alert.initOwner(s);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Student Selected");
            alert.setContentText("Please select a student in the table.");
            alert.showAndWait();
        }
    }
    public boolean showStudentEditDialog(Student student) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("Scene2.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(s);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            EditDialog editDialogController = loader.getController();
            editDialogController.setDialogStage(dialogStage);
            editDialogController.setStudent(student);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return editDialogController.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void handleEdit() {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent != null) {
            boolean okClicked = showStudentEditDialog(selectedStudent);

        } else {
            // Nothing selected.java
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(s);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }

    }
//    public void setController(Controller controller){
//        this.controller=controller;
//        studentTable.setItems(controller.getStudentData());
//
//
//    }


    public void initStudent() {

        studentData.add(new Student("Ruth", 18,"12345"));
        studentData.add(new Student("Heinz", 19,"12354"));
        studentData.add(new Student("Cornelia",20,"51243"));
        studentData.add(new Student("Werner", 18,"12435"));
        studentData.add(new Student("Lydia", 19,"12534"));
        studentData.add(new Student("Anna", 19,"12453"));
        studentData.add(new Student("Stefan", 19,"52314"));
        studentData.add(new Student("Martin", 20,"52143"));
    }



}
