package com.coupon.model.dao;

import com.coupon.model.entity.Coupon;
import core.dao.CoreDao;
import org.json.JSONArray;

import java.util.List;

public interface CouponDAO extends CoreDao {

    int insert(Coupon coupon);

    int deleteById(Integer couponId);

    int updateById(Coupon coupon);

    JSONArray listAll();

    JSONArray getById(Integer couponId);

    Coupon selectById(Integer couponId);

    List<Coupon> selectAll();

}
