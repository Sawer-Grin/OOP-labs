package com.example.mainapp.undergraduateStudent;

import com.example.mainapp.studentAbs.UndergraduateStudent;

import java.time.LocalDate;

public class Senior extends UndergraduateStudent {

	public Senior(){
		super();
	}
	public Senior(String name, String surname, Integer idStudent) {
		super(name, surname, idStudent, LocalDate.now());
	}

	public Senior(String name, String surname, Integer idStudent, LocalDate startOfEducation) {
		super(name, surname, idStudent, startOfEducation);
	}


	public Senior(String name, String surname, LocalDate startOfEducation) {
		super(name, surname, 0, startOfEducation);
	}
}
