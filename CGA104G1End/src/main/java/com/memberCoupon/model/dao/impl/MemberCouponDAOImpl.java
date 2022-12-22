package com.memberCoupon.model.dao.impl;

import com.memberCoupon.model.dao.MemberCouponDAO;
import com.memberCoupon.model.entity.MemberCoupon;
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
public class MemberCouponDAOImpl implements MemberCouponDAO {

    public MemberCouponDAOImpl() {

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
    public int insert(MemberCoupon memberCoupon) {
        try {
            beginTransaction();
            getSession().persist(memberCoupon);
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
    public int deleteById(Integer memId) {
        try {
            beginTransaction();
            Session session = getSession();
            MemberCoupon memberCoupon = session.load(MemberCoupon.class, memId);
            session.remove(memberCoupon);
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
    public int updateById(MemberCoupon memberCoupon) {
        try {
            beginTransaction();
            Session session = getSession();
            session.merge(memberCoupon);
            commit();
            return 1;
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public MemberCoupon getByCouponId(Integer couponId) {
        beginTransaction();
        MemberCoupon memberCoupon = getSession().get(MemberCoupon.class, couponId);
        commit();
        return memberCoupon;
    }

    @Override
    public List<MemberCoupon> getByMemIdCouponId(Integer memId, Integer couponId) {
        beginTransaction();
        List<MemberCoupon> memberCoupon = getSession()
                .createNativeQuery("SELECT * FROM member_coupon where MEM_ID = :memId and COUPON_ID = :couponId", MemberCoupon.class)
                .setParameter("memId", memId)
                .setParameter("couponId", couponId)
                .list();

        commit();
        return memberCoupon;
    }

    @Override
    @Transactional(readOnly = true)
    public JSONArray listById(Integer memId) {

        JSONArray items = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select C.COUPON_ID, C.COUPON_VAL, C.COUPON_NAR, C.USE_START, C.USE_OVER, M.MCPN_GETTIME, M.MCPN_USE, C.MINIMUM from MEMBER_COUPON as M inner join COUPON as C on M.COUPON_ID = C.COUPON_ID where MEM_ID = ? and MCPN_USE = 0 ")) {

            items = new JSONArray();

            pstmt.setInt(1, memId);
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
    public JSONArray listByCouponId(Integer couponId) {
       JSONArray items = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select C.COUPON_ID, C.COUPON_VAL, C.COUPON_NAR, C.USE_START, C.USE_OVER, M.MCPN_GETTIME, M.MCPN_USE, C.MINIMUM from MEMBER_COUPON as M inner join COUPON as C on M.COUPON_ID = C.COUPON_ID where C.COUPON_ID = ?")) {

            items = new JSONArray();

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
                items.put(jsonObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    @Override
    public List<MemberCoupon> getDetail(Integer memId) {
        List<MemberCoupon> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM member_coupon where MEM_ID = :id";
            list = getSession()
                    .createNativeQuery(sql, MemberCoupon.class)
                    .setParameter("id", memId)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<MemberCoupon> getById(Integer couponId) {
        List<MemberCoupon> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM member_coupon where COUPON_ID = :id";
            list = getSession()
                    .createNativeQuery(sql, MemberCoupon.class)
                    .setParameter("id", couponId)
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
    public List<MemberCoupon> selectAll() {
        List<MemberCoupon> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM member_coupon";
            list = getSession()
                    .createNativeQuery(sql, MemberCoupon.class)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }
}
