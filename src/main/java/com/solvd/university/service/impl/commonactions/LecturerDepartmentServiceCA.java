package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Lecturer;
import com.solvd.university.service.LecturerDepartmentServiceSetter;

import java.util.List;
import java.util.Random;

public class LecturerDepartmentServiceCA implements LecturerDepartmentServiceSetter {
    @Override
    public List<Lecturer> setDepartmentsToLecturers(List<Lecturer> lecturerList, List<Department> departmentList) {
        for (Lecturer lecturer : lecturerList) {
            Department tempDepartment;

            for (int i = 0; i < departmentList.size(); i++) {
                int randomDepartmentIndex = new Random().nextInt(
                        departmentList.size() - 1) % departmentList.size();
                tempDepartment = departmentList.get(randomDepartmentIndex);
                lecturer.setDepartment(tempDepartment);
            }

        }
        return lecturerList;
    }
}
