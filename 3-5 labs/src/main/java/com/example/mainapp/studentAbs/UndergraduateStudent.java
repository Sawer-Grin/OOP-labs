package com.example.mainapp.studentAbs;
import com.example.mainapp.Graduate.TypeGraduate;
import kotlin.contracts.CallsInPlace;

import java.lang.reflect.Type;
import java.time.LocalDate;

public class UndergraduateStudent extends Student{

	public UndergraduateStudent(){
		super(TypeGraduate.UNDERGRADUATE);
	}
	public UndergraduateStudent(String name, String surname, Integer idStudent,
								LocalDate startOfEducation){
		super(name, surname, TypeGraduate.UNDERGRADUATE, idStudent, startOfEducation);
	}
}
