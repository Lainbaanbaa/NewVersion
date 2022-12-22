package com.memberCoupon.model.dao;

import com.commodityDetails.model.entity.CommodityDetails;
import com.memberCoupon.model.entity.MemberCoupon;
import core.dao.CoreDao;
import org.json.JSONArray;

import java.sql.SQLException;
import java.util.List;

public interface MemberCouponDAO extends CoreDao {

    int insert(MemberCoupon memberCoupon);

    int deleteById(Integer memId);

    int updateById(MemberCoupon memberCoupon);

    MemberCoupon getByCouponId(Integer couponId);

    List<MemberCoupon> getByMemIdCouponId(Integer memId, Integer couponId);

    JSONArray listById(Integer memId);

    JSONArray listByCouponId(Integer couponId);

    List<MemberCoupon> getDetail(Integer memId);

    List<MemberCoupon> getById(Integer couponId);

    List<MemberCoupon> selectAll();

}
