package com.solvd.university.domain.logic;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.impl.StudentRepositoryImpl;
import com.solvd.university.persistence.impl.SubjectRepositoryImpl;

import java.util.*;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class StudentLogic {
    List<Student> studentList = new StudentRepositoryImpl().findAll();
    List<Subject> subjectList = new SubjectRepositoryImpl().getAllSubjects();

    private List<Student> getAllStudentsSubjects() {
        Set<Subject> tempSubjects = new HashSet<>();
        List<Student> allStudentsSubjects = new ArrayList<>(studentList);

        for (Student studentsSubject : allStudentsSubjects) {
            Random randomQuantity = new Random();
            int randomSubjectQuantity = randomQuantity.nextInt(studentList.size() - 1) % studentList.size() + 1;

            tempSubjects.clear();
            for (int i = 0; i < randomSubjectQuantity; i++) {
                Random randomIndex = new Random();
                int randomSubjectIndex = randomIndex.nextInt(studentList.size() - 1) % studentList.size();
                subjectList.get(randomSubjectIndex).setGrade(createGrade());
                tempSubjects.add(subjectList.get(randomSubjectIndex));
            }

            studentsSubject.setSubjects(tempSubjects);
        }

        return allStudentsSubjects;
    }

    public List<Student> printAllSubjects() {
        Set<Subject> tempSubjects = new HashSet<>();
        List<Student> allStudentsSubjects = new ArrayList<>(studentList);

        for (Student studentsSubject : allStudentsSubjects) {
            Random randomQuantity = new Random();
            int randomSubjectQuantity = randomQuantity.nextInt(studentList.size() - 1) % studentList.size() + 1;

            tempSubjects.clear();
            for (int i = 0; i < randomSubjectQuantity; i++) {
                Random randomIndex = new Random();
                int randomSubjectIndex = randomIndex.nextInt(studentList.size() - 1) % studentList.size();
                subjectList.get(randomSubjectIndex).setGrade(createGrade());
                tempSubjects.add(subjectList.get(randomSubjectIndex));
            }

            studentsSubject.setSubjects(tempSubjects);

            MY_LOGGER.info(studentsSubject.getFirstName() + " "
                    + studentsSubject.getLastName() + " " + studentsSubject.getSubjects().toString());
        }

        return allStudentsSubjects;
    }

    public Integer createGrade() {
        Random randomGrade = new Random();
        return randomGrade.nextInt(studentList.size() - 1) % studentList.size() + 1;
    }

    public Student getStudentSubjects() {
        List<Student> students = getAllStudentsSubjects();
        Student finalStudent = new StudentRepositoryImpl().findById();

        for (Student student : students) {
            if (finalStudent.getStudentId().equals(student.getStudentId())) {

                MY_LOGGER.info(finalStudent.getStudentId() + " | " + finalStudent.getFirstName() + " "
                        + finalStudent.getLastName() + " " + student.getSubjects());
            }
        }

        return finalStudent;
    }

    public Student showStudentPerformance() {
        List<Student> studentsWithSubjects = getAllStudentsSubjects();
        Student student = new StudentRepositoryImpl().findById();

        for (Student studentsWithSubject : studentsWithSubjects) {
            if (student.getStudentId().equals(studentsWithSubject.getStudentId())) {
                MY_LOGGER.info(ANSI_GREEN + "Студент: " + ANSI_YELLOW + student.getStudentId()
                        + " | " + student.getFirstName() + " " + student.getLastName() + ANSI_RESET);

                for (Subject subject : studentsWithSubject.getSubjects()) {
                    MY_LOGGER.info(ANSI_GREEN + "Предмет: " + ANSI_YELLOW + subject.getSubjectName() + " | "
                            + ANSI_GREEN + "Оценка " + ANSI_YELLOW + subject.getGrade() + ANSI_RESET);
                }
            }
        }

        return student;
    }

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
