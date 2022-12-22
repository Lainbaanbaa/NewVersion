package com.memberCoupon.model.service.impl;

import com.coupon.model.dao.CouponDAO;
import com.coupon.model.dao.impl.CouponDAOImpl;
import com.coupon.model.entity.Coupon;
import com.memberCoupon.model.dao.impl.MemberCouponDAOImpl;
import com.memberCoupon.model.dao.MemberCouponDAO;
import com.memberCoupon.model.entity.MemberCoupon;
import com.memberCoupon.model.service.MemberCouponService;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;

import static java.lang.System.out;

public class MemberCouponServiceImpl implements MemberCouponService {

    private MemberCouponDAO dao;


    @Override
    public boolean getCoupon(MemberCoupon memberCoupon) {
        MemberCouponDAO memberCouponDAO = new MemberCouponDAOImpl();
        return memberCouponDAO.insert(memberCoupon) > 0;
    }

    @Override
    public boolean updateCouponStatus(MemberCoupon memberCoupon) {
        return dao.updateById(memberCoupon) > 0;
    }

    @Override
    public MemberCoupon getCouponById(Integer couponId) {
        return dao.getByCouponId(couponId);
    }

    @Override
    public JSONArray getOwnCoupon(Integer memId) {
        MemberCouponDAO memberCouponDAO = new MemberCouponDAOImpl();
        return memberCouponDAO.listById(memId);
    }

    @Override
    public List<MemberCoupon> getOwnCoupon(Integer memId, Integer couponId) {
        return dao.getByMemIdCouponId(memId, couponId);
    }

    @Override
    public List<MemberCoupon> listAllCoupon() {
        return dao.selectAll();
    }

    @Override
    public JSONArray listOwnCoupon(Integer memId) {
        MemberCouponDAO memberCouponDAO = new MemberCouponDAOImpl();
        CouponDAO couponDAO = new CouponDAOImpl();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        List<MemberCoupon> own = memberCouponDAO.getDetail(memId);
        List<Coupon> all = couponDAO.selectAll();

        List<Coupon> notUse = all
                .stream()
                .filter(a -> a.getUseStart().compareTo(timestamp) > 0)
                .filter(a -> a.getUseOver().compareTo(timestamp) < 0)
                .toList();

        List<Integer> notUseId = notUse
                .stream()
                .map(Coupon::getCouponId)
                .toList();

        List<Integer> ownId = own
                .stream()
                .map(MemberCoupon::getCouponId)
                .toList();

        List<MemberCoupon> used = own
                .stream()
                .filter(u -> u.getMcpnUse() == 1)
                .toList();

        List<Integer> usedId = used
                .stream()
                .map(MemberCoupon::getCouponId)
                .toList();

        List<Integer> result = ownId
                .stream()
                .filter(o -> !notUseId.contains(o))
                .filter(u -> !usedId.contains(u))
                .toList();

        JSONArray jsonArr = new JSONArray();
        JSONObject jsonObj;

        try {
            for (Integer integer : result) {
                JSONArray jsonArr3 = couponDAO.getById(integer);
                for (int i = 0; i < jsonArr3.length(); i++) {
                    jsonObj = jsonArr3.getJSONObject(i);
                    jsonArr.put(jsonObj);
                }
            }
        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        return jsonArr;
    }

    @Override
    public List<MemberCoupon> ownCoupon(Integer memId) {
        MemberCouponDAO memberCouponDAO = new MemberCouponDAOImpl();
        return memberCouponDAO.getDetail(memId);
    }


    @Override
    public Integer getNewsCount() {
        return null;
    }

    @Override
    public MemberCoupon updateCouponStatus(Integer memberId, Integer couponId, byte status) {
        MemberCouponDAO memberCouponDAO = new MemberCouponDAOImpl();
        MemberCoupon memberCoupon = new MemberCoupon();
        List<MemberCoupon> list = memberCouponDAO.getByMemIdCouponId(memberId, couponId);
        Timestamp date = null;
        for (MemberCoupon coupon : list) {
            date = coupon.getMcpnGettime();
        }
        memberCoupon.setCouponId(couponId);
        memberCoupon.setMemId(memberId);
        memberCoupon.setMcpnGettime(date);
        memberCoupon.setMcpnUse(status);
        memberCouponDAO.updateById(memberCoupon);
        return memberCoupon;
    }
}
