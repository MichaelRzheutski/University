package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Department;
import com.solvd.university.domain.Student;
import com.solvd.university.service.DepartmentCAService;

import java.util.List;
import java.util.Random;

public class DepartmentCommonActionsService implements DepartmentCAService {
    @Override
    public List<Student> setDepartmentsToStudents(List<Student> studentList, List<Department> departmentList) {
        for (Student student : studentList) {
            Department tempDepartment;

            for (int i = 0; i < departmentList.size(); i++) {
                int randomDepartmentIndex = new Random().nextInt(
                        departmentList.size() - 1) % departmentList.size();
                int randomCourseIndex = new Random().nextInt(5) % 5 + 1;
                tempDepartment = departmentList.get(randomDepartmentIndex);
                tempDepartment.setCourse(randomCourseIndex);
                student.setDepartment(tempDepartment);
            }

        }
        return studentList;
    }
}
