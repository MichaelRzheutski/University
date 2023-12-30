package com.solvd.university.service.impl;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.impl.StudentRepositoryDaoImpl;
import com.solvd.university.persistence.impl.SubjectRepositoryDaoImpl;
import com.solvd.university.service.StudentService;

import java.util.*;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentServiceImpl implements StudentService {
    List<Student> studentList = new StudentRepositoryDaoImpl().findAll();
    List<Subject> subjectList = new SubjectRepositoryDaoImpl().getAllSubjects();

    public List<Student> createAllSubjects() {

        for (Student student : studentList) {
            Set<Subject> tempSubjects = new HashSet<>();
            Random randomQuantity = new Random();
            int randomSubjectQuantity = randomQuantity.nextInt(studentList.size() - 1) % studentList.size() + 1;

            for (int i = 0; i < randomSubjectQuantity; i++) {
                Random randomIndex = new Random();
                int randomSubjectIndex = randomIndex.nextInt(studentList.size() - 1) % studentList.size();
                subjectList.get(randomSubjectIndex).setGrade(createGrade());
                tempSubjects.add(subjectList.get(randomSubjectIndex));
                student.setSubjects(tempSubjects);
            }
        }
        return studentList;
    }

    @Override
    public List<Student> printAllSubjects() {
        List<Student> allStudentsWithSubjects = createAllSubjects();

        for (Student student : allStudentsWithSubjects) {

            MY_LOGGER.info(student.getStudentId() + " | " + student.getFirstName() + " "
                    + student.getLastName() + " " + student.getSubjects().toString());
        }

        return allStudentsWithSubjects;
    }

    private Integer createGrade() {
        return new Random().nextInt(studentList.size() - 1) % studentList.size() + 1;
    }

    @Override
    public Student getStudentAllSubjects() {
        createAllSubjects();
        Student foundStudent = new StudentRepositoryDaoImpl().findById();

        for (Student student : studentList) {
            if (foundStudent.getStudentId().equals(student.getStudentId())) {
                MY_LOGGER.info(ANSI_GREEN + "Список предметов студента: " + ANSI_YELLOW +
                        student.getStudentId() + " | " + student.getFirstName() + " "
                        + student.getLastName() + ANSI_RESET);

                for (Subject subject : studentList.get(9).getSubjects()) {
                    MY_LOGGER.info(ANSI_YELLOW + subject.getSubjectName() + ANSI_RESET);
                }
                System.out.println();
            }
        }

        return foundStudent;
    }

    @Override
    public Student showStudentPerformance() {
        createAllSubjects();
        Student foundStudent = new StudentRepositoryDaoImpl().findById();

        for (Student student : studentList) {
            if (foundStudent.getStudentId().equals(student.getStudentId())) {
                MY_LOGGER.info(ANSI_GREEN + "Успеваемость студента: " + ANSI_YELLOW + foundStudent.getStudentId()
                        + " | " + foundStudent.getFirstName() + " " + foundStudent.getLastName() + ANSI_RESET);

                for (Subject subject : student.getSubjects()) {
                    MY_LOGGER.info(ANSI_GREEN + "Предмет: " + ANSI_YELLOW + subject.getSubjectName() + " | "
                            + ANSI_GREEN + "Оценка " + ANSI_YELLOW + subject.getGrade() + ANSI_RESET);
                }
                System.out.println();
            }
        }

        return foundStudent;
    }

    @Override
    public String takeExam() {
        Scanner scanner = new Scanner(System.in);
        int enteredStudentId;
        int enteredSubjectId;
        String examResult = "";

        MY_LOGGER.info(ANSI_GREEN + "Для сдачи экзамена введите ID студента:" + ANSI_RESET);
        if (scanner.hasNextInt()) {
            enteredStudentId = scanner.nextInt();

            MY_LOGGER.info(ANSI_GREEN + "Список доступных для сдачи предметов:" + ANSI_RESET);
            for (Subject subject : subjectList) {
                MY_LOGGER.info(subject.getSubjectId() + " | " + ANSI_YELLOW + subject.getSubjectName() + ANSI_RESET);
            }

            MY_LOGGER.info(ANSI_GREEN + "Введите ID предмета:" + ANSI_RESET);
            if (scanner.hasNextInt()) {
                enteredSubjectId = scanner.nextInt();

                if (enteredSubjectId >= 1 && enteredSubjectId <= subjectList.size()) {
                    Random randomMark = new Random();
                    int randomSubjectMark = randomMark.nextInt(10) + 1;
                    subjectList.get(enteredSubjectId).setGrade(0);
                    subjectList.get(enteredSubjectId).setGrade(randomSubjectMark);

                    if (randomizeExamTake(studentList, enteredStudentId, randomSubjectMark)) {
                        MY_LOGGER.info(
                                ANSI_GREEN + studentList.get(enteredStudentId).getFirstName() + " "
                                        + studentList.get(enteredStudentId).getLastName() + " сдал(ла) " +
                                        ANSI_YELLOW + subjectList.get(enteredStudentId).getSubjectName() +
                                        " оценка " + subjectList.get(enteredStudentId).getGrade() + ANSI_RESET);
                    } else {
                        MY_LOGGER.info(
                                ANSI_GREEN + studentList.get(enteredStudentId).getFirstName() + " "
                                        + studentList.get(enteredStudentId).getLastName() + " не сдал(ла) " +
                                        ANSI_YELLOW + subjectList.get(enteredStudentId).getSubjectName() +
                                        " оценка " + subjectList.get(enteredStudentId).getGrade() + ANSI_RESET);
                    }
                } else {
                    MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
                }
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }

        return examResult;
    }

    private boolean randomizeExamTake(List<Student> studentList, int enteredStudentId, int randomSubjectMark) {
        boolean isExamPassed = false;
        double tempAverageScore;
        double finalAverageScore;
        double[] examinationTicketDifficulty = {1.0, 5.0, 10.0};

        Random randomQuantity = new Random();
        int randomDificulty = randomQuantity.nextInt(3);

        tempAverageScore = studentList.get(enteredStudentId).getAverageScore();
        finalAverageScore = tempAverageScore + examinationTicketDifficulty[randomDificulty];

        if (finalAverageScore >= 10.0 && randomSubjectMark > 4) {
            isExamPassed = true;
        }

        return isExamPassed;
    }
}
