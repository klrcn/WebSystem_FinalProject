package com.digitalyearbook.Digital.Yearbook.service;

import com.digitalyearbook.Digital.Yearbook.entity.Student;
import com.digitalyearbook.Digital.Yearbook.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudentsByYear(int year) {
        return studentRepository.findByGraduationYear(year);
    }
}
