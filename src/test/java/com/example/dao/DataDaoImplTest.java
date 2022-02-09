package com.example.dao;

import com.example.model.Data;
import com.example.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataDaoImplTest {
    private static SessionFactory sessionFactory;
    private Session session;

    @BeforeAll
    public static void setup() {
        sessionFactory = HibernateSessionFactoryUtil.getSessionFactory();
        System.out.println("SessionFactory created");
    }

    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) sessionFactory.close();
        System.out.println("SessionFactory destroyed");
    }

    @Test
    void findByIdNotNullTest() {
        assertNotEquals(HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Data.class, 11),null);
    }

    @Test
    void save() {
        System.out.println("Running testCreate...");
        session.beginTransaction();
        Data data = new Data(1,"NameTest", "UrlTest");
        Integer id = (Integer) session.save(data);
        session.getTransaction().commit();
        Assertions.assertTrue(HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Data.class, 1)!=(null));
    }

    @Test
    void delete() {
        System.out.println("Running testDelete...");

        Integer id = 1;
        Data data = session.find(Data.class, id);

        session.beginTransaction();
        session.delete(data);
        session.getTransaction().commit();

        Data deletedProduct = session.find(Data.class, id);

        Assertions.assertNull(deletedProduct);
    }

    @Test
    void findAll() {
        System.out.println("Running testList...");

        Query<Data> query = session.createQuery("from Data", Data.class);
        List<Data> resultList = query.getResultList();

        Assertions.assertFalse(resultList.isEmpty());
    }


    @BeforeEach
    public void openSession() {
        session = sessionFactory.openSession();
        System.out.println("Session created");
    }

    @AfterEach
    public void closeSession() {
        if (session != null) session.close();
        System.out.println("Session closed\n");
    }
}