package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public Stage primaryStage;

    //    private BorderPane root;
    @Override
    public void start(Stage primaryStage){
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Student Management");
        try{
//                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(getClass().getResource("sample.fxml"));
            AnchorPane root = (AnchorPane)loader.load();
//                Controller controller = loader.getController();
//                controller.setMain(this);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

//    private ObservableList<Student> studentData = FXCollections.observableArrayList();

//    public Stage getPrimaryStage() {
//        return primaryStage;
//    }
//    public boolean showStudentEditDialog(Student student) {
//        try {
//            // Load the fxml file and create a new stage for the popup dialog.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(Main.class.getResource("Scene2.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            // Create the dialog Stage.
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Student");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            // Set the person into the controller.
//            EditDialog controller = loader.getController();
//            controller.setDialogStage(dialogStage);
//            controller.setStudent(student);
//
//            // Show the dialog and wait until the user closes it
//            dialogStage.showAndWait();
//
//            return controller.isOkClicked();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//    public ObservableList <Student> getStudentData(){
//        return studentData;
//    }

//    public Main() {
//
//        studentData.add(new Student("Ruth", 18,"12345"));
//        studentData.add(new Student("Heinz", 19,"12354"));
//        studentData.add(new Student("Cornelia",20,"51243"));
//        studentData.add(new Student("Werner", 18,"12435"));
//        studentData.add(new Student("Lydia", 19,"12534"));
//        studentData.add(new Student("Anna", 19,"12453"));
//        studentData.add(new Student("Stefan", 19,"52314"));
//        studentData.add(new Student("Martin", 20,"52143"));
//    }

    public static void main(String[] args) {
        launch(args);
    }
}

