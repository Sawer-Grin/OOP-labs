package com.example.mainapp.undergraduateStudent;

import com.example.mainapp.studentAbs.UndergraduateStudent;
import java.time.LocalDate;

public class Freshman extends UndergraduateStudent {

	public Freshman(){
		super();
	}

	public Freshman(String name, String surname) {
		super(name, surname, 0, LocalDate.now());
	}

	public Freshman(String name, String surname, Integer idStudent, LocalDate startOfEducation) {
		super(name, surname, idStudent, startOfEducation);
	}

	public Freshman(String name, String surname, LocalDate startOfEducation) {
		super(name, surname, 0, startOfEducation);
	}
}
