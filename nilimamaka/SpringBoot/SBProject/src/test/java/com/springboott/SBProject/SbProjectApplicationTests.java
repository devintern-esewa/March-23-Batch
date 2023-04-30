package com.springboott.SBProject;

import com.springboott.SBProject.student.Student;
import com.springboott.SBProject.student.StudentRepo;
import com.springboott.SBProject.student.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SbProjectApplicationTests {
@Autowired
	private StudentService service;

@MockBean
	private StudentRepo studentRepo;


	@Test
	public void getStudentsTest() {

		when (studentRepo.findAll()).thenReturn(Stream.of(
				new Student("abc","abc@gmail","1999-02-10"),new Student("azxczxbc","abzczxcc@gmail","1999-02-11")).collect(Collectors.toList()));
		assertEquals(2,service.getStudents().size() );
	}

}
