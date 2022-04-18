package com.example.mainapp.undergraduateStudent;

import com.example.mainapp.studentAbs.UndergraduateStudent;

import java.time.LocalDate;

public class Junior extends UndergraduateStudent {

	public Junior(){
		super();
	}

	public Junior(String name, String surname, Integer idStudent) {
		super(name, surname, idStudent, LocalDate.now());
	}
	public Junior(String name, String surname, Integer idStudent, LocalDate localDate){
		super(name, surname, idStudent, localDate);
	}

	public Junior(String name, String surname, LocalDate startOfEducation){
		super(name, surname, 0, startOfEducation);
	}
}
