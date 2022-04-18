package com.example.mainapp.undergraduateStudent;

import com.example.mainapp.studentAbs.UndergraduateStudent;

import java.time.LocalDate;

public class Sophomore extends UndergraduateStudent {

	public Sophomore(){
		super();
	}

	public Sophomore(String name, String surname, Integer idStudent){
		super(name, surname, idStudent, LocalDate.now());
	}

	public Sophomore(String name, String surname, Integer idStudent, LocalDate startOfEducation){
		super(name, surname, idStudent, startOfEducation);
	}

	public Sophomore(String name, String surname, LocalDate startOfEducation){
		super(name, surname, 0, startOfEducation);
	}
}
