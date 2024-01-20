package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.service.StudentDepartmentService;
import com.solvd.university.service.StudentDepartmentServiceSetter;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCA;

import java.util.List;

public class StudentDepartmentServiceJdbcImpl extends StudentDepartmentServiceCA implements StudentDepartmentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentDepartmentServiceSetter studentDepartmentServiceSetter;

    public StudentDepartmentServiceJdbcImpl(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            StudentDepartmentServiceSetter studentDepartmentServiceSetter
    ) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.studentDepartmentServiceSetter = studentDepartmentServiceSetter;
    }

    @Override
    public List<Student> getStudentsWithDepartments() {
        List<Student> students = studentRepository.findAll();
        List<Department> departments = departmentRepository.getAllDepartments();

        return studentDepartmentServiceSetter.setDepartmentsToStudents(students, departments);
    }
}
