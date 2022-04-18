package com.example.mainapp.studentAbs;
import com.example.mainapp.Graduate.TypeGraduate;

import java.time.LocalDate;

public class GraduateStudent extends Student{

	public GraduateStudent(){
		super(TypeGraduate.GRADUATE);
	}
	public GraduateStudent(String name, String surname, Integer idStudent,
						   LocalDate startOfEducation){
		super(name, surname, TypeGraduate.GRADUATE, idStudent, startOfEducation);
	}
}
