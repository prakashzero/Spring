package com.DbProject.SpringJDBC.service;


import com.DbProject.SpringJDBC.model.Student;
import com.DbProject.SpringJDBC.repo.Studentrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {


    @Autowired
    Studentrepo studentrepo;

    public Studentrepo getStudentrepo() {
        return studentrepo;
    }

    public void setStudentrepo(Studentrepo studentrepo) {
        this.studentrepo = studentrepo;
    }



    public void addStudent(Student student) {
        studentrepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentrepo.findAll();
    }
}
