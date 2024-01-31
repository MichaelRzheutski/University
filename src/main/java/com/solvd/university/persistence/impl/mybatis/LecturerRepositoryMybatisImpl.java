package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.Lecturer;
import com.solvd.university.persistence.LecturerRepository;
import com.solvd.university.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LecturerRepositoryMybatisImpl implements LecturerRepository {
    @Override
    public List<Lecturer> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            return lecturerRepository.findAll();
        }
    }

    @Override
    public void create(Lecturer lecturer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            lecturerRepository.create(lecturer);
        }
    }

    @Override
    public Lecturer findById(Lecturer lecturer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            return lecturerRepository.findById(lecturer);
        }
    }

    @Override
    public void update(Lecturer lecturer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            lecturerRepository.update(lecturer);
        }
    }

    @Override
    public void deleteById(Lecturer lecturer) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            lecturerRepository.deleteById(lecturer);
        }
    }

    @Override
    public Long countOfEntries() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerRepository lecturerRepository = sqlSession.getMapper(LecturerRepository.class);
            return lecturerRepository.countOfEntries();
        }
    }
}
