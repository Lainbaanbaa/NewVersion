package com.commodityDetails.model.dao.impl;

import com.commodityDetails.model.dao.CommodityDetailsDAO;
import com.commodityDetails.model.entity.CommodityDetails;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CommodityDetailsDAOImpl implements CommodityDetailsDAO {

    public CommodityDetailsDAOImpl() {

    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insert(CommodityDetails commodityDetails) {
        try {
            beginTransaction();
            getSession().persist(commodityDetails);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int deleteById(Integer orderId) {
        try {
            beginTransaction();
            Session session = getSession();
            CommodityDetails commodityDetails = session.load(CommodityDetails.class, orderId);
            session.remove(commodityDetails);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int updateById(CommodityDetails commodityDetails) {
        try {
            beginTransaction();
            Session session = getSession();
            session.merge(commodityDetails);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommodityDetails> getDetail(Integer orderId) {
        List<CommodityDetails> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM commodity_details where ORDER_ID = :id";
            list = getSession()
                    .createNativeQuery(sql, CommodityDetails.class)
                    .setParameter("id", orderId)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommodityDetails> selectAll() {
        List<CommodityDetails> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM commodity_details";
            list = getSession()
                    .createNativeQuery(sql, CommodityDetails.class)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }
}
