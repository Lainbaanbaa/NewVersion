package com.memberCoupon.controller;

import com.memberCoupon.model.entity.MemberCoupon;
import com.memberCoupon.model.service.MemberCouponService;
import com.memberCoupon.model.service.impl.MemberCouponServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "GetCouponServlet", value = "/GetCouponServlet")
public class GetCouponServlet extends HttpServlet {

    private MemberCouponService service;

    @Override
    public void init() throws ServletException {
        service = new MemberCouponServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        res.setCharacterEncoding("UTF-8");

        /* 允許跨域主機地址 */
        res.setHeader("Access-Control-Allow-Origin", "*");
        /* 允許跨域 GET, POST, HEAD 等 */
        res.setHeader("Access-Control-Allow-Methods", "*");
        /* 重新預檢驗跨域的緩存時間 (s) */
        res.setHeader("Access-Control-Max-Age", "3600");
        /* 允許跨域的請求頭 */
        res.setHeader("Access-Control-Allow-Headers", "*");
        /* 是否攜帶 cookie */
        res.setHeader("Access-Control-Allow-Credentials", "true");

        HttpSession session = req.getSession();
        final Integer memId = (Integer) session.getAttribute("mem_id");
        System.out.println(memId);
        final Integer couponId = Integer.valueOf(req.getParameter("couponId"));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        try {

            MemberCoupon memberCoupon = new MemberCoupon();
            memberCoupon.setCouponId(couponId);
            memberCoupon.setMemId(memId);
            memberCoupon.setMcpnGettime(timestamp);
            memberCoupon.setMcpnUse((byte) 0);

            service.getCoupon(memberCoupon);

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
