package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.StudentContact;
import com.solvd.university.persistence.PersistenceConfig;
import com.solvd.university.persistence.StudentContactRepository;
import org.apache.ibatis.session.SqlSession;

public class StudentContactRepositoryMybatisImpl implements StudentContactRepository {
    @Override
    public void create(StudentContact studentContact) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentContactRepository studentContactRepository = sqlSession.getMapper(StudentContactRepository.class);
            studentContactRepository.create(studentContact);
        }
    }
}