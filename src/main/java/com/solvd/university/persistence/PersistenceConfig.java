package com.solvd.university.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class PersistenceConfig {
    private static final SqlSessionFactory SESSION_FACTORY;

    static {
        try (InputStream is = Resources.getResourceAsStream("mybatis/mybatis.config.xml")) {
            SESSION_FACTORY = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static SqlSessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
