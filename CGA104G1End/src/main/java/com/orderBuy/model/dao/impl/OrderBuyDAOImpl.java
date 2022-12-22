package com.orderBuy.model.dao.impl;

import com.orderBuy.model.dao.OrderBuyDAO;
import com.orderBuy.model.entity.OrderBuy;
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
public class OrderBuyDAOImpl implements OrderBuyDAO {

    public OrderBuyDAOImpl() {

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
    public int insert(OrderBuy orderBuy) {
        try {
            beginTransaction();
            getSession().persist(orderBuy);
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
            OrderBuy orderBuy = session.load(OrderBuy.class, orderId);
            session.remove(orderBuy);
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
    public int updateById(OrderBuy orderBuy) {
        try {
            beginTransaction();
            Session session = getSession();
            session.merge(orderBuy);
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
    public OrderBuy selectById(Integer orderId) {
        beginTransaction();
        OrderBuy orderBuy = getSession().get(OrderBuy.class, orderId);
        commit();
        return orderBuy;
    }

    @Override
    @Transactional(readOnly = true)
    public JSONArray listById(Integer memId) {
        JSONArray items = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from ORDER_BUY where MEM_ID = ?")) {

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
    public JSONArray listAll() {
        JSONArray items = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select * from ORDER_BUY order by ORDER_ID")) {

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
    @Transactional(readOnly = true)
    public List<OrderBuy> selectAll() {
        List<OrderBuy> list = null;
        try {
            beginTransaction();
            final String sql = "SELECT * FROM order_buy";
            list = getSession()
                    .createNativeQuery(sql, OrderBuy.class)
                    .list();
            commit();
        } catch (Exception e) {
            rollback();
            e.printStackTrace();
        }
        return list;
    }
}
