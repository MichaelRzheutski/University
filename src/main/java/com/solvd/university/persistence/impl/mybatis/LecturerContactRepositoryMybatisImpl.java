package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.LecturerContact;
import com.solvd.university.persistence.LecturerContactRepository;
import com.solvd.university.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class LecturerContactRepositoryMybatisImpl implements LecturerContactRepository {
    @Override
    public void createLecturerContact(LecturerContact lecturerContact) {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerContactRepository lecturerContactRepository = sqlSession.getMapper(LecturerContactRepository.class);
            lecturerContactRepository.createLecturerContact(lecturerContact);
        }
    }

    @Override
    public List<LecturerContact> getAllLecturerContacts() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            LecturerContactRepository lecturerContactRepository = sqlSession.getMapper(LecturerContactRepository.class);
            return lecturerContactRepository.getAllLecturerContacts();
        }
    }
}
