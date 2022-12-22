package com.coupon.model.dao.impl;

import com.coupon.model.dao.CouponDAO;
import com.coupon.model.entity.Coupon;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

@Transactional(readOnly = true)
public class CouponDAOImpl implements CouponDAO {

    public CouponDAOImpl() {

    }

    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/hikariCP-BaRei");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int insert(Coupon coupon) {
        try {
            beginTransaction();
            getSession().persist(coupon);
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
    public int deleteById(Integer couponId) {
        try {
            beginTransaction();
            Session session = getSession();
            Coupon coupon = session.load(Coupon.class, couponId);
            session.remove(coupon);
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
    public int updateById(Coupon coupon) {
        try {
            beginTransaction();
            Session session = getSession();
            session.merge(coupon);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public JSONArray listAll() {

        JSONArray items = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from coupon")) {

            items = new JSONArray();

            ResultSet rs = pstmt.executeQuery();
            // 取得列數
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 遍瀝 ResultSet
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String value = null;
                    String columnName = metaData.getColumnLabel(i);// 列名稱
                    if (rs.getString(columnName) != null && !rs.getString(columnName).equals("")) {
                        value = new String(rs.getBytes(columnName), StandardCharsets.UTF_8);

                    } else {
                        value = "";
                    }
                    jsonObj.put(columnName, value);
                }
                items.put(jsonObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public JSONArray getById(Integer couponId) {

        JSONArray item = null;

        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from COUPON where COUPON_ID = ?")) {


            item = new JSONArray();

            pstmt.setInt(1, couponId);
            ResultSet rs = pstmt.executeQuery();
            // 取得列數
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 遍瀝 ResultSet
            while (rs.next()) {
                JSONObject jsonObj = new JSONObject();
                for (int i = 1; i <= columnCount; i++) {
                    String value = null;
                    String columnName = metaData.getColumnLabel(i);// 列名稱
                    if (rs.getString(columnName) != null && !rs.getString(columnName).equals("")) {
                        value = new String(rs.getBytes(columnName), StandardCharsets.UTF_8);

                    } else {
                        value = "";
                    }
                    jsonObj.put(columnName, value);
                }
                item.put(jsonObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    @Override
    @Transactional(readOnly = true)
    public Coupon selectById(Integer couponId) {
        beginTransaction();
        Coupon coupon = getSession().get(Coupon.class, couponId);
        commit();
        return coupon;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Coupon> selectAll() {
        List<Coupon> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM coupon";
            list = getSession()
                    .createNativeQuery(sql, Coupon.class)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }
}
