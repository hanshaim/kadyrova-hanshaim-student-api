
package com.kadyrova.studentapi.repository;

import com.kadyrova.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}


