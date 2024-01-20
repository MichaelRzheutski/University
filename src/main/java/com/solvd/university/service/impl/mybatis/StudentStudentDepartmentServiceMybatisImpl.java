package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.service.StudentDepartmentCAService;
import com.solvd.university.service.StudentDepartmentService;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCommonActions;

import java.util.List;

public class StudentStudentDepartmentServiceMybatisImpl extends StudentDepartmentServiceCommonActions implements StudentDepartmentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final StudentDepartmentCAService commonActionable;

    public StudentStudentDepartmentServiceMybatisImpl(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            StudentDepartmentCAService commonActionable
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
