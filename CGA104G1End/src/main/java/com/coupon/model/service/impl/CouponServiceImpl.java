package com.coupon.model.service.impl;

import com.coupon.model.dao.CouponDAO;
import com.coupon.model.dao.impl.CouponDAOImpl;
import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import org.json.JSONArray;

import java.util.List;

public class CouponServiceImpl implements CouponService {

    private CouponDAO dao;

    public CouponServiceImpl() {
        dao = new CouponDAOImpl();
    }

    @Override
    public boolean addCoupon(Coupon coupon) {
        return dao.insert(coupon) > 0;
    }

    @Override
    public boolean removeCoupon(Integer couponId) {
        return dao.deleteById(couponId) > 0;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        return dao.updateById(coupon) > 0;
    }

    @Override
    public Coupon getCouponById(Integer couponId) {
        return dao.selectById(couponId);
    }

    @Override
    public JSONArray listAllCouponJSON() {
        CouponDAO couponDAO = new CouponDAOImpl();
        return couponDAO.listAll();
    }

    @Override
    public JSONArray getById(Integer couponId) {
        CouponDAO couponDAO = new CouponDAOImpl();
        return  couponDAO.getById(couponId);
    }

    @Override
    public List<Coupon> listAllCoupon() {
        return dao.selectAll();
    }

    @Override
    public Integer getNewsCount() {
        return null;
    }
}
