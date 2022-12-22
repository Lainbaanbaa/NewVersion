package com.coupon.controller;

import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "AddCouponServlet", value = "/backend/coupon/addCoupon.do")
public class AddCouponServlet extends HttpServlet {

    private CouponService service;

    @Override
    public void init() throws ServletException {
        service = new CouponServiceImpl();
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

        final String action = req.getParameter("action");

        if ("addCoupon".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            //*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
            final String couponInfo = req.getParameter("couponInfo");
            if (couponInfo == null || couponInfo.trim().length() == 0) {
                errorMsgs.add("折價券說明不可留白");
            }

            Double couponValue = null;
            try {
                couponValue = Double.valueOf(req.getParameter("couponValue").trim());
            } catch (NumberFormatException e) {
                couponValue = 0.0;
                errorMsgs.add("請輸入正確面額");
            }

            java.sql.Date getTimeStart = null;
            java.sql.Date getTimeOver = null;

            try {
                getTimeStart = java.sql.Date.valueOf(req.getParameter("getTimeStart").trim());
                getTimeOver = java.sql.Date.valueOf(req.getParameter("getTimeOver").trim());
            } catch (IllegalArgumentException e) {
                getTimeStart = new java.sql.Date(System.currentTimeMillis());
                getTimeOver = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入折價券領取時間!");
            }

            java.sql.Date useTimeStart = null;
            java.sql.Date useTimeOver = null;

            try {
                useTimeStart = java.sql.Date.valueOf(req.getParameter("useTimeStart").trim());
                useTimeOver = java.sql.Date.valueOf(req.getParameter("useTimeOver").trim());
            } catch (IllegalArgumentException e) {
                useTimeStart = new java.sql.Date(System.currentTimeMillis());
                useTimeOver = new java.sql.Date(System.currentTimeMillis());
                errorMsgs.add("請輸入折價券使用時間!");
            }

            Double minimum = null;
            try {
                minimum = Double.valueOf(req.getParameter("minimum").trim());
            } catch (NumberFormatException e) {
                minimum = 0.0;
                errorMsgs.add("請輸入正確金額");
            }

            Coupon coupon = new Coupon();
            coupon.setCouponNar(couponInfo);
            coupon.setCouponVal(couponValue);
            coupon.setReceiveStart(getTimeStart);
            coupon.setReceiveOver(getTimeOver);
            coupon.setUseStart(useTimeStart);
            coupon.setUseOver(useTimeOver);
            coupon.setMinimum(minimum);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("coupon", coupon);
                RequestDispatcher failureView = req.getRequestDispatcher("/backend/coupon/addCoupon.jsp");
                failureView.forward(req, res);
                return;
            }

            //*************************** 2.開始新增資料 ***************************************/
            CouponService couponService = new CouponServiceImpl();
            couponService.addCoupon(coupon);

            //*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            String url = "/backend/coupon/listAllCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);

        }

        if ("quit".equals(action)) {
            String url = "/backend/coupon/listAllCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }
}
