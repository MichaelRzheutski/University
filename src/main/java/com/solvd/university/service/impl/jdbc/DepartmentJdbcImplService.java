package com.solvd.university.service.impl.jdbc;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.service.DepartmentCAService;
import com.solvd.university.service.DepartmentService;
import com.solvd.university.service.impl.commonactions.DepartmentCommonActionsService;

import java.util.List;

public class DepartmentJdbcImplService extends DepartmentCommonActionsService implements DepartmentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final DepartmentCAService commonActionable;

    public DepartmentJdbcImplService(
            StudentRepository studentRepository,
            DepartmentRepository departmentRepository,
            DepartmentCAService commonActionable
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
