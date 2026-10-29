
package com.kadyrova.studentapi.service;

import com.kadyrova.studentapi.entity.Student;
import com.kadyrova.studentapi.repository.StudentRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student create(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student getById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Student not found: " + id
            ));
    }

    public Student update(Long id, Student updated) {
        Student student = getById(id);

        student.setName(updated.getName());
        student.setEmail(updated.getEmail());
        student.setCourse(updated.getCourse());

        return repository.save(student);
    }

    public void delete(Long id) {
        Student student = getById(id);
        repository.delete(student);
    }
}

