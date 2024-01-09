package com.solvd.university.persistence.impl.mybatis;

import com.solvd.university.domain.Department;
import com.solvd.university.persistence.DepartmentRepository;
import com.solvd.university.persistence.PersistenceConfig;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class DepartmentRepositoryMybatisImpl implements DepartmentRepository {
    @Override
    public List<Department> getAllDepartments() {
        try (SqlSession sqlSession = PersistenceConfig.getSessionFactory().openSession(true)) {
            DepartmentRepository departmentRepository = sqlSession.getMapper(DepartmentRepository.class);
            return departmentRepository.getAllDepartments();
        }
    }
}
