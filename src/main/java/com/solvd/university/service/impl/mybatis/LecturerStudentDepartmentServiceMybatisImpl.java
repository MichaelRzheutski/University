package com.solvd.university.service.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Lecturer;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.LecturerRepository;
import com.solvd.university.service.LecturerDepartmentCAService;
import com.solvd.university.service.LecturerDepartmentService;
import com.solvd.university.service.impl.commonactions.StudentDepartmentServiceCommonActions;

import java.util.List;

public class LecturerStudentDepartmentServiceMybatisImpl extends StudentDepartmentServiceCommonActions implements LecturerDepartmentService {
    private final LecturerRepository lecturerRepository;
    private final DepartmentRepository departmentRepository;
    private final LecturerDepartmentCAService lecturerDepartmentCAService;

    public LecturerStudentDepartmentServiceMybatisImpl(
            LecturerRepository lecturerRepository,
            DepartmentRepository departmentRepository,
            LecturerDepartmentCAService lecturerDepartmentCAService
    ) {
        this.lecturerRepository = lecturerRepository;
        this.departmentRepository = departmentRepository;
        this.lecturerDepartmentCAService = lecturerDepartmentCAService;
    }

    @Override
    public List<Lecturer> getLecturersWithDepartments() {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        List<Department> departments = departmentRepository.getAllDepartments();

        return lecturerDepartmentCAService.setDepartmentsToLecturers(lecturers, departments);
    }
}
