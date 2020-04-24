package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.prefs.Preferences;

public class Controller  {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label statusLabel;

    private Stage secondStage;
    public Stage getSecondStage() {
        return secondStage;
    }
    private ObservableList<Student> studentData= FXCollections.observableArrayList();
    @FXML
    public void handleLogin() {
        if(usernameField.getText().equals("admin")&& passwordField.getText().equals("1234")){
            secondStage=new Stage();
            secondStage.setTitle("Student Management");
            secondStage.initModality(Modality.APPLICATION_MODAL);
            initRootLayout();
            showStudentOverview();
        }else{
            statusLabel.setText("Login Failed");
        }
    }
    private BorderPane rootLayout;
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("rootScene1.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            secondStage.setScene(scene);
            RootLayout rootLayoutController = loader.getController();
            rootLayoutController.setController(this);
            secondStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = getStudentFilePath();
        if (file != null) {
            loadStudentDataFromFile(file);
        }
    }

    public StudentOverViewController studentOverViewController;
    public void showStudentOverview(){
        try{
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("Scene1.fxml"));
//            Parent root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        AnchorPane root =(AnchorPane)loader.load();
        rootLayout.setCenter(root);
        studentOverViewController = loader.getController();
        studentOverViewController.initStudent();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public File getStudentFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(Main.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    public void loadStudentDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            studentData.clear();
            studentData.addAll(wrapper.getStudent());

            // Save the file path to the registry.
           setStudentFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());
            alert.showAndWait();
        }
    }
    public void setStudentFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            secondStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            secondStage.setTitle("AddressApp");
        }
    }
    public void saveStudentDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setStudents(studentData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setStudentFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

}
