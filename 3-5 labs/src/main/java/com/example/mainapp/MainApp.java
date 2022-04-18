package com.example.mainapp;

import java.io.IOException;
import java.time.LocalDate;
import com.example.mainapp.studentAbs.Student;
import com.example.mainapp.undergraduateStudent.Junior;
import com.example.mainapp.undergraduateStudent.Senior;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

	private Student editStudent;

	private StudentsOverviewController controllerStudent;
	private CreatingOverviewController controllerForCreatingNewStudent;

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Student> studentData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
		// Add some sample data
		studentData.add(new Junior("Hans", "Muster", 0, LocalDate.now()));
		studentData.add(new Junior("Ruth", "Mueller", 0, LocalDate.now()));
		studentData.add(new Junior("Heinz", "Kurz", 0, LocalDate.now()));
		studentData.add(new Junior("Cornelia", "Meier", 0, LocalDate.now()));
		studentData.add(new Junior("Werner", "Meyer", 0, LocalDate.now()));
		studentData.add(new Junior("Lydia", "Kunz", 0, LocalDate.now()));
		studentData.add(new Junior("Anna", "Best", 0, LocalDate.now()));
		studentData.add(new Junior("Stefan", "Meier", 0, LocalDate.now()));
		studentData.add(new Junior("Martin", "Mueller", 0, LocalDate.now()));
		studentData.add(new Senior("efjehefj", "dedede", 0));
	}

	/**
	 * Set the data as an observable list of Students.
	 */
	public void setStudentData(ObservableList<Student> listStudent){
		this.studentData = listStudent;
		controllerStudent.overloadTable(listStudent);
	}

	/**
	 * Method for adding new element in list of Student
	 */
	public void addNewItemStudentData(Student newStudent){
		studentData.add(newStudent);
	}

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout(primaryStage);
        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout(Stage mainStage) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

			controllerForCreatingNewStudent = loader.getController();
			controllerForCreatingNewStudent.setSettings(mainStage, this);

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(personOverview);

            // Give the controller access to the main app.
            controllerStudent = loader.getController();
			//////////////////////////////////////////////////////----------------
            controllerStudent.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/**
	 * Method for setting editStudent
	 */
	public void setEditStudent(Student editStudent) {
		this.editStudent = editStudent;
		controllerStudent.updateTableInfo(editStudent);
	}

	/**
	 * Method for removing elements(items) on list of Students
	 * @param oldStudent
	 */
	public void removeStudentInList(Student oldStudent){
		studentData.remove(oldStudent);
	}

	/**
	 * Method for adding new elements(items) on list of Students
	 * @param newStudent
	 */
	public void addStudentInList(Student newStudent){
		studentData.add(newStudent);
	}

	/**
	 * Method for returning list of Students
	 * @return ObservableList<Student>
	 */
	public ObservableList<Student> getStudentData(){
		return studentData;
	}

	/**
	 * Getter for editStudent
	 */
	public Student getEditStudent() {
		Student result = editStudent;
		editStudent = null;
		return result;
	}

	/**
     * Returns the main stage.
     * @return Stage
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}