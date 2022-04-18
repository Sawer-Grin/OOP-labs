package com.example.mainapp;

import com.example.mainapp.graduateStudent.DoctoralStudent;
import com.example.mainapp.graduateStudent.MastersStudent;
import com.example.mainapp.undergraduateStudent.Freshman;
import com.example.mainapp.studentAbs.Student;
import com.example.mainapp.undergraduateStudent.Junior;
import com.example.mainapp.undergraduateStudent.Senior;
import com.example.mainapp.undergraduateStudent.Sophomore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.io.*;

public class CreatingOverviewController {
	private MainApp mainApp;

	private Student newStudent;

	private Stage mainStage;

	private final DirectoryChooser directoryChooser;
	private final FileChooser fileChooser;

	/**
	 * Constructor for class CreatingOverviewController
	 */
	public CreatingOverviewController(){
		directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Directory");
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));

		fileChooser = new FileChooser();
		fileChooser.setTitle("Select Place for File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@FXML
	private void initialize() {
	}

	/**
	 * Method for setting object Main Class for Application
	 */
	public void setSettings(Stage primaryStage, @NotNull MainApp mainApp){
		this.mainStage = primaryStage;
		this.mainApp = mainApp;
	}

	/**
	 * Method for creating new Items( Object Freshman)
	 */
	@FXML
	public void createFreshman(){
		newStudent = new Freshman();
		timingEditStudent();
	}

	/**
	 * Method for creating new Items( Object Junior)
	 */
	@FXML
	public void createJunior(){
		newStudent = new Junior();
		timingEditStudent();
	}

	/**
	 * Method for creating new Items (Object Senior)
	 */
	@FXML
	public void createSenior(){
		newStudent = new Senior();
		timingEditStudent();
	}

	/**
	 * Method for creating new Items (Object Sophomore)
	 */
	@FXML
	public void createSophomore(){
		newStudent = new Sophomore();
		timingEditStudent();
	}

	/**
	 * Method for creating new Items (Object Master)
	 */
	@FXML
	public void createMaster(){
		newStudent = new MastersStudent();
		timingEditStudent();
	}

	/**
	 * Method for creating new Items (Object Doctor)
	 */
	@FXML
	public void createDoctor(){
		newStudent = new DoctoralStudent();
		timingEditStudent();
	}

	/**
	 * Method for timing editStudent in MainApp
	 */
	private void timingEditStudent(){
		mainApp.setEditStudent(newStudent);
	}

	/**
	 * Method for serialization object to XML
	 */
	@FXML
	public void saveTextFile(){
		String absolutPath = choosePlaceForFile();
		if (absolutPath == null){
			return;
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(absolutPath);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
			ObservableList<Student> tempList = mainApp.getStudentData();
			objectOutputStream.writeInt(tempList.size());

			for(Student temp : tempList){
				objectOutputStream.writeObject(temp);
			}
			objectOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Method for reading file and recovery object
	 */
	@FXML
	public void readTextFile(){
		String absolutPath = chooseFile();
		if (absolutPath == null){
			return;
		}

		try {
			FileInputStream inputStream = new FileInputStream(absolutPath);
			ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
			ObservableList<Student> tempList = FXCollections.observableArrayList();
			int amount_elements = objectInputStream.readInt();
			for (int i = 0; i < amount_elements; ++i){
				tempList.add((Student)objectInputStream.readObject());
			}
			mainApp.setStudentData(tempList);

			objectInputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

		/**
	 * Method for choosing directory for file
	 */
	@Nullable
	private String choosePlaceForFile(){
		File file = fileChooser.showSaveDialog(mainStage);
		if (file != null)
			return file.getAbsolutePath();
		else
			return null;
	}

	@Nullable
	private String chooseFile(){
		File file = fileChooser.showOpenDialog(mainStage);
		if (file != null)
			return file.getAbsolutePath();
		else
			return null;
	}
}
