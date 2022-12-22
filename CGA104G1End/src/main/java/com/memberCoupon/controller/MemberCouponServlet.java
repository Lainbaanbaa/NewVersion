package com.memberCoupon.controller;

import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;
import com.memberCoupon.model.service.impl.MemberCouponServiceImpl;
import com.memberCoupon.model.service.MemberCouponService;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MemberCouponServlet", value = "/MemberCouponServlet")
public class MemberCouponServlet extends HttpServlet {

    private MemberCouponService service;

    @Override
    public void init() throws ServletException {
        service = new MemberCouponServiceImpl();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setCharacterEncoding("UTF-8");

        req.setCharacterEncoding("UTF-8");

        res.setContentType("text/html;charset=utf-8");
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

        PrintWriter pw = res.getWriter();

        MemberCouponService memberCouponService = new MemberCouponServiceImpl();
        JSONArray items = memberCouponService.listOwnCoupon(memId);

        pw.println(items);
    }
}
