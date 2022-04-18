package com.example.mainapp;
import com.example.mainapp.Graduate.TypeGraduate;
import com.example.mainapp.studentAbs.Student;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class StudentsOverviewController {
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> firstNameColumn;
    @FXML
    private TableColumn<Student, String> lastNameColumn;

    @FXML
    private TextField firstNameLabel;
    @FXML
    private TextField lastNameLabel;
    @FXML
    private TextField idStudentLabel;
    @FXML
    private TextField graduateLabel;
    @FXML
    private TextField startOfEducationLabel;
    @FXML
    private TextField periodOfEducationLabel;

    // Reference to the main application.
    private MainApp mainApp;

	/**
	 * Considered Student - temporary memory for editing items
	 */
	private Student consideredStudent;

    /**
     * The constructor.
     * The constructor is called before initialize() method.
     */
    public StudentsOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());

		TableView.TableViewSelectionModel<Student> selectionModel = studentTable.getSelectionModel();
		selectionModel.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				consideredStudent = newValue;
				updateTableInfo(newValue);
			}
		});

    }

    /**
     * Is called by the main application to give a reference back to itself.
	 */
    public void setMainApp(@NotNull MainApp mainApp) {
        this.mainApp = mainApp;
		studentTable.setEditable(true);
        studentTable.setItems(mainApp.getStudentData());
    }

	/**
	  * Action for button (creating new Item (New..))
	 */
	@FXML
	public void creatingNewItem(){
		Student editStudent = mainApp.getEditStudent();
		if (editStudent != null){
			updateInfoStudent(editStudent);
			mainApp.addNewItemStudentData(editStudent);
		}
	}

	/**
	 * Method to implement on the button(Edit...)
	 * Find last considered item in list then remove it and add new item(bad ENGLISH)
	 */
	@FXML
	public void editItem(){
		if (consideredStudent != null)
			updateInfoStudent(consideredStudent);
	}

	/**
	 * Method to implement on the button(delete ...)
	 */
	@FXML
	public void deleteItem(){
		if (this.consideredStudent != null)
			editItemInList(this.consideredStudent, null);
	}

	/**
	 * Method for deleting considered item from list of Student(StudentData in MainApp)
	 * Second parameter for replace old item in list( Can be null)
	 */
	private void editItemInList(@NotNull Student localStudent, Student editProfileStudent){
		mainApp.removeStudentInList(localStudent);
		if (editProfileStudent != null)
			mainApp.addStudentInList(editProfileStudent);
	}

	/**
	 * Method for compare information between editStudent and TextField.
	 * And set information( if not equal) to editStudent
	 * Method using try construction
	 */
	private void updateInfoStudent(@NotNull Student oldStudent){
		if (!Objects.equals(firstNameLabel.getText(), oldStudent.getName()))
			oldStudent.setName(firstNameLabel.getText());

		if (!Objects.equals(lastNameLabel.getText(), oldStudent.getSurname()))
			oldStudent.setSurname(lastNameLabel.getText());

		if (!idStudentLabel.getText().equals(oldStudent.getIDStudent().toString()))
			oldStudent.setIDStudent(Integer.parseInt(idStudentLabel.getText()));

		String temp = graduateLabel.getText();
		if (!graduateLabel.getText().equals(oldStudent.getGraduate().toString())){
			try {
				oldStudent.setGraduate(TypeGraduate.valueOf(graduateLabel.getText()));
			}
			catch (IllegalArgumentException e){
				oldStudent.setGraduate(TypeGraduate.NONE);
			}
		}

		if (!startOfEducationLabel.getText().equals(oldStudent.getStartOfEducation().toString())){
			try {
				oldStudent.setStartOfEducation(LocalDate.parse(startOfEducationLabel.getText()));
			}
			catch (DateTimeParseException e){
				oldStudent.setStartOfEducation(LocalDate.now());
			}
		}
	}

	/**
	 * Method for filling TextFields in information from tempStudent
	 */
	public void updateTableInfo(@NotNull Student tempStudent){
		firstNameLabel.setText(tempStudent.getName());
		lastNameLabel.setText(tempStudent.getSurname());
		idStudentLabel.setText(tempStudent.getIDStudent().toString());
		graduateLabel.setText(tempStudent.getGraduate().toString());
		startOfEducationLabel.setText(tempStudent.getStartOfEducation().toString());
		periodOfEducationLabel.setText(tempStudent.getYearOfStudy().toString());
	}

	public void overloadTable(ObservableList<Student> newStudentData){
		studentTable.refresh();
		studentTable.setItems(newStudentData);
	}
}