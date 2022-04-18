package com.example.mainapp.graduateStudent;

import com.example.mainapp.studentAbs.GraduateStudent;

import java.time.LocalDate;

public class MastersStudent extends GraduateStudent {

	public MastersStudent(){
		super();
	}

	public MastersStudent(String name, String surname, Integer idStudent) {
		super(name, surname, idStudent, LocalDate.now());
	}

	public MastersStudent(String name, String surname, Integer idStudent, LocalDate startOfEducation) {
		super(name, surname, idStudent, startOfEducation);
	}

	public MastersStudent(String name, String surname, LocalDate startOfEducation) {
		super(name, surname, 0, startOfEducation);
	}
}
