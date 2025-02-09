package com.DbProject.SpringJDBC;

import com.DbProject.SpringJDBC.model.Student;
import com.DbProject.SpringJDBC.service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringJdbcApplication {

	public static void main(String[] args) {

	ApplicationContext context= SpringApplication.run(SpringJdbcApplication.class, args);
	Student student=context.getBean(Student.class);



	StudentService service=context.getBean(StudentService.class);
		student.setName("Bhanu");
		student.setRollNo(24);
		student.setMarks(82);
	service.addStudent(student);


	List<Student> list=service.getAllStudents();
		System.out.println(list);

	}

}
