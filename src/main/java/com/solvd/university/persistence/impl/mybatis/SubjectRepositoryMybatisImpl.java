package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.Subject;
import com.solvd.university.persistence.PersistenceConfig;
import com.solvd.university.persistence.SubjectRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class SubjectRepositoryMybatisImpl implements SubjectRepository {
    @Override
    public List<Subject> getAllSubjects() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            SubjectRepository subjectRepository = sqlSession.getMapper(SubjectRepository.class);
            return subjectRepository.getAllSubjects();
        }
    }
}
