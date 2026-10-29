
package com.kadyrova.studentapi;

import com.kadyrova.studentapi.entity.Student;
import com.kadyrova.studentapi.repository.StudentRepository;
import com.kadyrova.studentapi.service.StudentService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student(
            "Kadyrova Hanshaim",
            "hanshaim@example.com",
            "Software Engineering"
        );
    }

    @Test
    void testCreateStudent() {
        when(repository.save(student)).thenReturn(student);

        Student result = service.create(student);

        assertEquals("Kadyrova Hanshaim", result.getName());
        verify(repository).save(student);
    }

    @Test
    void testGetAllStudents() {
        when(repository.findAll()).thenReturn(List.of(student));

        List<Student> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Kadyrova Hanshaim", result.get(0).getName());
    }

    @Test
    void testGetStudentById() {
        when(repository.findById(1L))
            .thenReturn(Optional.of(student));

        Student result = service.getById(1L);

        assertEquals("Kadyrova Hanshaim", result.getName());
    }

    @Test
    void testUpdateStudent() {
        Student updated = new Student(
            "Kadyrova Hanshaim",
            "updated@example.com",
            "DevOps"
        );

        when(repository.findById(1L))
            .thenReturn(Optional.of(student));

        when(repository.save(student))
            .thenReturn(student);

        Student result = service.update(1L, updated);

        assertEquals("updated@example.com", result.getEmail());
        assertEquals("DevOps", result.getCourse());
    }

    @Test
    void testDeleteStudent() {
        when(repository.findById(1L))
            .thenReturn(Optional.of(student));

        service.delete(1L);

        verify(repository).delete(student);
    }

    @Test
    void testStudentNotFound() {
        when(repository.findById(99L))
            .thenReturn(Optional.empty());

        assertThrows(
            ResponseStatusException.class,
            () -> service.getById(99L)
        );
    }
}

