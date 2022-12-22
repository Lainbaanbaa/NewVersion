package com.coupon.controller;

import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCouponValServlet", value = "/getCouponValServlet")
public class GetCouponValServlet extends HttpServlet {

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

        final Integer couponId = Integer.valueOf(req.getParameter("couponId"));
        final Double finalPrice = Double.valueOf(req.getParameter("finalPrice"));

        JSONObject jsonMsg = new JSONObject();
        PrintWriter pw = res.getWriter();

        CouponService couponService = new CouponServiceImpl();
        Coupon coupon = couponService.getCouponById(couponId);

        Double minimum = coupon.getMinimum();
        if (finalPrice < minimum) {
            jsonMsg.put("minimumErr", "無法使用折價券，未達到最低消費 NT$" + minimum);
            jsonMsg.put("couponVal", "0");
        }
        if (!jsonMsg.isEmpty()) {
            pw.println(jsonMsg);
            return;
        }
        jsonMsg.put("couponVal", String.valueOf(coupon.getCouponVal()));

        pw.println(jsonMsg);
    }

}
