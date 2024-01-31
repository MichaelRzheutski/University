package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.PersistenceConfig;
import com.solvd.university.persistence.StudentContactRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentContactRepositoryMybatisImpl implements StudentContactRepository {
    @Override
    public void createStudentContact(StudentContact studentContact) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentContactRepository studentContactRepository = sqlSession.getMapper(StudentContactRepository.class);
            studentContactRepository.createStudentContact(studentContact);
        }
    }

    @Override
    public List<StudentContact> getAllStudentContacts() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentContactRepository studentContactRepository = sqlSession.getMapper(StudentContactRepository.class);
            return studentContactRepository.getAllStudentContacts();
        }
    }
}
