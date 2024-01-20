package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.service.StudentDepartmentService;
import com.solvd.university.service.StudentDepartmentServiceSetter;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCA;

import java.util.List;

public class StudentDepartmentServiceMybatisImpl extends StudentDepartmentServiceCA implements StudentDepartmentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentDepartmentServiceSetter commonActionable;

    public StudentDepartmentServiceMybatisImpl(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            StudentDepartmentServiceSetter commonActionable
    ) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.commonActionable = commonActionable;
    }

    @Override
    public List<Student> getStudentsWithDepartments() {
        List<Student> students = studentRepository.findAll();
        List<Department> departments = departmentRepository.getAllDepartments();

        return commonActionable.setDepartmentsToStudents(students, departments);
    }
}
