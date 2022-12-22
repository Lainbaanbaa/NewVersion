package com.coupon.model.service;

import com.coupon.model.entity.Coupon;
import org.json.JSONArray;

import java.util.List;

public interface CouponService {

    boolean addCoupon(Coupon coupon);

    boolean removeCoupon(Integer couponId);

    boolean updateCoupon(Coupon coupon);

    Coupon getCouponById(Integer couponId);

    JSONArray listAllCouponJSON();

    JSONArray getById(Integer couponId);

    List<Coupon> listAllCoupon();

    Integer getNewsCount();

}
