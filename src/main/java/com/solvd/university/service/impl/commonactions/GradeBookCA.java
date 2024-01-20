package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.service.SubjectServiceSetter;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.solvd.university.util.ConsoleColors.ANSI_GREEN;
import static com.solvd.university.util.ConsoleColors.ANSI_RESET;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class GradeBookCA implements SubjectServiceSetter {
    @Override
    public List<Student> setSubjectsToStudents(List<Student> studentList, List<Subject> subjectList) {
        for (Student student : studentList) {
            Set<Subject> tempSubjects = new HashSet<>();
            int randomSubjectQuantity = new Random().nextInt(
                    10 - 1) % studentList.size() + 1;
            int randomGrade = new Random().nextInt(
                    10 - 1) % studentList.size() + 1;

            for (int i = 0; i < randomSubjectQuantity; i++) {
                Random randomIndex = new Random();
                int randomSubjectIndex = randomIndex.nextInt(
                        subjectList.size() - 1) % subjectList.size();
                subjectList.get(randomSubjectIndex).setGrade(randomGrade);
                tempSubjects.add(subjectList.get(randomSubjectIndex));
                student.setSubjects(tempSubjects);
            }

        }
        return studentList;
    }

    protected void printStudentSubjects(List<Student> studentsWithSubjects) {
        MY_LOGGER.info(ANSI_GREEN +
                "Id студента" + " | " +
                "Имя и фамилия" + " | " +
                "Предмет + Оценка" + ANSI_RESET
        );
        for (Student student : studentsWithSubjects) {
            MY_LOGGER.info(student.getStudentId() + " | " + student.getFirstName() + " " +
                    student.getLastName() + " " + student.getSubjects().toString());

        }
        System.out.println();
    }
}
