package com.coupon.controller;

import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;
import com.memberCoupon.model.entity.MemberCoupon;
import com.memberCoupon.model.service.MemberCouponService;
import com.memberCoupon.model.service.impl.MemberCouponServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "ListAllCouponServlet", value = "/ListAllCouponServlet")
public class ListAllCouponServlet extends HttpServlet {

    private CouponService service;

    @Override
    public void init() throws ServletException {
        service = new CouponServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setCharacterEncoding("UTF-8");

        req.setCharacterEncoding("UTF-8");

        res.setContentType("text/html;charset=utf-8");

        HttpSession session = req.getSession();
        final Integer memId = (Integer) session.getAttribute("mem_id");

        PrintWriter pw = res.getWriter();

        MemberCouponService couponService = new MemberCouponServiceImpl();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        List<Coupon> allCoupon = service.listAllCoupon()
                .stream()
                .filter(a -> a.getReceiveStart().compareTo(timestamp) < 0)
                .filter(a -> a.getReceiveOver().compareTo(timestamp) > 0)
                .toList();
        List<MemberCoupon> ownCoupon = couponService.ownCoupon(memId);

        List<Integer> all = allCoupon
                .stream()
                .map(Coupon::getCouponId)
                .toList();

        List<Integer> own = ownCoupon
                .stream()
                .map(MemberCoupon::getCouponId)
                .toList();

        List<Integer> result = all
                .stream()
                .filter(o -> !own.contains(o))
                .toList();

        JSONArray items = new JSONArray();
        JSONObject jsonObj;

        try {
            for (Integer integer : result) {
                JSONArray jsonArr = service.getById(integer);
                for (int i = 0; i < jsonArr.length(); i++) {
                    jsonObj = jsonArr.getJSONObject(i);
                    items.put(jsonObj);
                }
            }
        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        pw.println(items);
    }
}
