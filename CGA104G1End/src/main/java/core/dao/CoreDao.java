package core.dao;

import java.util.List;

import org.hibernate.Session;

import core.util.HibernateUtil;
import org.hibernate.Transaction;

public interface CoreDao<P, I> {


    default Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    default Transaction beginTransaction() {
        return HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
    }

    default void commit() {
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
    }

    default void rollback() {
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
    }
}
