package com.solvd.university.service.impl.commonactions;

import com.solvd.university.domain.Student;
import com.solvd.university.domain.Subject;

import java.util.*;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class SubjectServiceCommonActions {
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
        for (Student student : studentsWithSubjects) {
            MY_LOGGER.info(student.getStudentId() + " | " + student.getFirstName() + " " +
                    student.getLastName() + " " + student.getSubjects().toString());
        }
        System.out.println();
    }

    protected void showStudentAllSubjects(List<Student> studentsWithSubjects, Student foundStudent) {
        int enteredStudentId = (int) (foundStudent.getStudentId() - 1);

        if (foundStudent.getStudentId().equals(studentsWithSubjects.get(enteredStudentId).getStudentId())) {
            MY_LOGGER.info(ANSI_GREEN + "Список предметов студента: " + ANSI_YELLOW +
                    studentsWithSubjects.get(enteredStudentId).getStudentId() + " | " +
                    studentsWithSubjects.get(enteredStudentId).getFirstName() + " " +
                    studentsWithSubjects.get(enteredStudentId).getLastName() + ANSI_RESET);

            for (Subject subject : studentsWithSubjects.get(enteredStudentId).getSubjects()) {
                MY_LOGGER.info(ANSI_YELLOW + subject.getSubjectName() + ANSI_RESET);
            }
            System.out.println();

        } else {
            MY_LOGGER.info(ANSI_RED + "Студент отсутствует в базе данных!" + ANSI_RESET);
        }
    }

    protected void showStudentResults(List<Student> studentsWithSubjects, Student foundStudent) {
        int enteredStudentId = (int) (foundStudent.getStudentId() - 1);

        if (foundStudent.getStudentId().equals(studentsWithSubjects.get(enteredStudentId).getStudentId())) {
            MY_LOGGER.info(ANSI_GREEN + "Успеваемость студента: " + ANSI_YELLOW +
                    studentsWithSubjects.get(enteredStudentId).getStudentId() + " | " +
                    studentsWithSubjects.get(enteredStudentId).getFirstName() + " " +
                    studentsWithSubjects.get(enteredStudentId).getLastName() + ANSI_RESET);

            for (Subject subject : studentsWithSubjects.get(enteredStudentId).getSubjects()) {
                MY_LOGGER.info(ANSI_GREEN + "Предмет: " + ANSI_YELLOW + subject.getSubjectName() + " | "
                        + ANSI_GREEN + "Оценка " + ANSI_YELLOW + subject.getGrade() + ANSI_RESET);
            }
            System.out.println();

        } else {
            MY_LOGGER.info(ANSI_RED + "Студент отсутствует в базе данных!" + ANSI_RESET);
        }
    }

    protected void passExam(List<Student> studentsWithSubjects, Student foundStudent) {
        Scanner scanner = new Scanner(System.in);
        int enteredStudentId = (int) (foundStudent.getStudentId() - 1);
        int enteredSubjectId = 0;

        if (foundStudent.getStudentId().equals(studentsWithSubjects.get(enteredStudentId).getStudentId())) {
            MY_LOGGER.info(ANSI_GREEN + "Список доступных для сдачи предметов: " + ANSI_RESET);
            for (Subject subject : studentsWithSubjects.get(enteredStudentId).getSubjects()) {
                MY_LOGGER.info(subject.getSubjectId() + " | " +
                        ANSI_YELLOW + subject.getSubjectName() + ANSI_RESET);
            }

            MY_LOGGER.info(ANSI_GREEN + "Введите ID предмета:" + ANSI_RESET);
            if (scanner.hasNextInt()) {
                enteredSubjectId = scanner.nextInt();
            } else {
                MY_LOGGER.info(ANSI_RED + "Введено неверное число, попробуйте ещё раз!" + ANSI_RESET);
            }

            for (Subject subject : studentsWithSubjects.get(enteredStudentId).getSubjects()) {
                if (enteredSubjectId == subject.getSubjectId()) {
                    int randomSubjectMark = new Random().nextInt(10) + 1;
                    subject.setGrade(0);
                    subject.setGrade(randomSubjectMark);

                    if (randomizeExamTake(studentsWithSubjects, enteredStudentId, randomSubjectMark)) {
                        MY_LOGGER.info(
                                ANSI_GREEN + studentsWithSubjects.get(enteredStudentId).getFirstName() + " " +
                                        studentsWithSubjects.get(enteredStudentId).getLastName() + " сдал(ла): " +
                                        ANSI_YELLOW + subject.getSubjectName() +
                                        " оценка " + ANSI_GREEN + subject.getGrade() + ANSI_RESET + "\n");
                    } else {
                        MY_LOGGER.info(
                                ANSI_GREEN + studentsWithSubjects.get(enteredStudentId).getFirstName() + " " +
                                        studentsWithSubjects.get(enteredStudentId).getLastName() + " не сдал(ла): " +
                                        ANSI_YELLOW + subject.getSubjectName() +
                                        " оценка " + ANSI_GREEN + subject.getGrade() + ANSI_RESET + "\n");
                    }
                }
            }
        } else {
            MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
        }
    }

    private boolean randomizeExamTake(
            List<Student> studentList, int enteredStudentId, int randomSubjectMark) {
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
