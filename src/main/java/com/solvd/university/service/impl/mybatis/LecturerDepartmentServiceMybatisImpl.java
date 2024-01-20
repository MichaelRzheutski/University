package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Lecturer;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.LecturerRepository;
import com.solvd.university.service.LecturerDepartmentService;
import com.solvd.university.service.LecturerDepartmentServiceSetter;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCA;

import java.util.List;

public class LecturerDepartmentServiceMybatisImpl extends StudentDepartmentServiceCA implements LecturerDepartmentService {
    private final LecturerRepository lecturerRepository;
    private final DepartmentRepository departmentRepository;
    private final LecturerDepartmentServiceSetter lecturerDepartmentServiceSetter;

    public LecturerDepartmentServiceMybatisImpl(
            LecturerRepository lecturerRepository,
            DepartmentRepository departmentRepository,
            LecturerDepartmentServiceSetter lecturerDepartmentServiceSetter
    ) {
        this.lecturerRepository = lecturerRepository;
        this.departmentRepository = departmentRepository;
        this.lecturerDepartmentServiceSetter = lecturerDepartmentServiceSetter;
    }

    @Override
    public List<Lecturer> getLecturersWithDepartments() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        List<Department> departments = departmentRepository.getAllDepartments();

        return lecturerDepartmentServiceSetter.setDepartmentsToLecturers(lecturers, departments);
    }
}
