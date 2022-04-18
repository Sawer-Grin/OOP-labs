package com.example.mainapp.studentAbs;

import com.example.mainapp.Graduate.TypeGraduate;
import javafx.beans.property.*;
import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class Student implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	protected transient StringProperty name;
	protected transient StringProperty surname;
	protected Integer IDStudent;
	protected TypeGraduate graduate;
	protected LocalDate startOfEducation;

	public Student(){
		name = new SimpleStringProperty("");
		surname = new SimpleStringProperty("");
		graduate = TypeGraduate.NONE;
		IDStudent = 0;
		startOfEducation = LocalDate.now();
	}

	public Student(TypeGraduate typeGraduate){
		name = new SimpleStringProperty("");
		surname = new SimpleStringProperty("");
		graduate = typeGraduate;
		IDStudent = 0;
		startOfEducation = LocalDate.now();
	}

	public Student(String name, String surname, TypeGraduate graduate,
					  Integer idStudent, LocalDate startDate) {
		this.name = new SimpleStringProperty(name);
		this.surname = new SimpleStringProperty(surname);
		this.graduate = graduate;
		this.IDStudent = idStudent;
		this.startOfEducation = startDate;
	}

	public Student(@NotNull Student student){
		this.name = student.name;
		this.surname = student.surname;
		this.graduate = student.graduate;
		this.IDStudent = student.IDStudent;
		this.startOfEducation = student.startOfEducation;
	}

	public StringProperty nameProperty() {
		return name;
	}
	public StringProperty surnameProperty() {
		return surname;
	}
	public LocalDate getStartOfEducation() {
		return startOfEducation;
	}

	public Integer getIDStudent(){
		return IDStudent;
	}

	public void setIDStudent(Integer idStudent){
		this.IDStudent = idStudent;
	}

	public String getName(){
		return name.get();
	}

	public void setName(String name){
		this.name.set(name);
	}

	public String getSurname() {
		return surname.get();
	}

	public void setSurname(String surname) {
		this.surname.set(surname);
	}

	public void setGraduate(TypeGraduate graduate){
		this.graduate = graduate;
	}

	public TypeGraduate getGraduate(){
		return graduate;
	}

	public void setStartOfEducation(LocalDate tempStartOfEducation){
		startOfEducation = tempStartOfEducation;
	}

	public Integer getYearOfStudy() {
		Period localPeriod = Period.between(LocalDate.now(), getStartOfEducation());
		return localPeriod.getYears();
	}

	@Override
	public String toString(){
		return "Student{" + "Name=" + name.getValue() +
				", Surname=" + surname.getValue() +
				", Graduate" + graduate.toString() +
				". PlaceOfWork=" + IDStudent +
				" ,YearOfStudy=" + startOfEducation.toString();
	}

	@Override
	public boolean equals(Object student){
		if (student == this) {
			return true;
		}
		if (student == null || student.getClass() != this.getClass()) {
			return false;
		}

		Student guest = (Student) student;
		return (guest.name == this.name) &&
				(guest.surname == this.surname) &&
				(guest.IDStudent.equals(this.IDStudent)) &&
				(guest.startOfEducation == this.startOfEducation) &&
				(guest.graduate == this.graduate);
	}

	@Serial
	private void writeObject(@NotNull ObjectOutputStream s) throws IOException, ClassNotFoundException {
		s.defaultWriteObject();
		s.writeObject(name.getValue());
		s.writeObject(surname.getValue());
		s.writeInt(IDStudent);
		s.writeObject(startOfEducation);
		s.writeObject(graduate);
	}

	@Serial
	private void readObject(@NotNull ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		name = new SimpleStringProperty((String)s.readObject());
		surname = new SimpleStringProperty((String)s.readObject());
		IDStudent = s.readInt();
		startOfEducation = (LocalDate) s.readObject();
		graduate = (TypeGraduate) s.readObject();
	}
}
