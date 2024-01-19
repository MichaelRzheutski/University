package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.StudentRepository;
import com.solvd.university.persistence.impl.mybatis.DepartmentRepositoryMybatisImpl;
import com.solvd.university.persistence.impl.mybatis.StudentRepositoryMybatisImpl;
import com.solvd.university.service.DepartmentService;
import com.solvd.university.service.DepartmentCAService;
import com.solvd.university.service.impl.commonactions.DepartmentCommonActionsService;

import java.util.List;

public class DepartmentMybatisImplService extends DepartmentCommonActionsService implements DepartmentService {
    private final StudentRepository studentRepository = new StudentRepositoryMybatisImpl();
    private final DepartmentRepository departmentRepository = new DepartmentRepositoryMybatisImpl();
    private final DepartmentCAService commonActionable = new DepartmentCommonActionsService();


    @Override
    public List<Student> getStudentsWithDepartments() {
        List<Student> students = studentRepository.findAll();
        List<Department> departments = departmentRepository.getAllDepartments();

        return commonActionable.setDepartmentsToStudents(students, departments);
    }
}
