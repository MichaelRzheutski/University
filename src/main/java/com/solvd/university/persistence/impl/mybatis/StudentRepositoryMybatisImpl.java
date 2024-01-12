package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.Student;
import com.solvd.university.persistence.PersistenceConfig;
import com.solvd.university.persistence.StudentRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StudentRepositoryMybatisImpl implements StudentRepository {
    @Override
    public List<Student> findAll() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            return studentRepository.findAll();
        }
    }

    @Override
    public void create(Student student) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            studentRepository.create(student);
        }
    }

    @Override
    public Student findById(Student student) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            return studentRepository.findById(student);
        }
    }

    @Override
    public void update(Student student) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            studentRepository.update(student);
        }
    }

    @Override
    public void deleteById(Student student) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            studentRepository.deleteById(student);
        }
    }

    @Override
    public Long countOfEntries() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            return studentRepository.countOfEntries();
        }
    }
}
