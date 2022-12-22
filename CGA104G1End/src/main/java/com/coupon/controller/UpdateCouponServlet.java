package com.coupon.controller;

import com.coupon.model.entity.Coupon;
import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "UpdateCouponServlet", value = "/backend/coupon/updateCoupon.do")
public class UpdateCouponServlet extends HttpServlet {
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

        if ("getOne_For_Update".equals(action)) { // 來自listAllCoupon.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ****************************************/
            Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));

            /*************************** 2.開始查詢資料 ****************************************/
            CouponServiceImpl couponService = new CouponServiceImpl();
            Coupon coupon = couponService.getCouponById(couponId);

            /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
            req.setAttribute("coupon", coupon); // 資料庫取出的empVO物件,存入req
            String url = "/backend/coupon/updateCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
            final Integer couponId = Integer.valueOf(req.getParameter("coupon_id").trim());

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
            coupon.setCouponId(couponId);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("coupon", coupon); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req.getRequestDispatcher("/backend/coupon/updateCoupon.jsp");
                failureView.forward(req, res);
                return; // 程式中斷
            }

            //*************************** 2.開始修改資料 ***************************************/
            CouponService couponService = new CouponServiceImpl();
            couponService.updateCoupon(coupon);

            //*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
            req.setAttribute("coupon", coupon); // 資料庫update成功後,正確的的couponVO物件,存入req
            String url = "/backend/coupon/listAllCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }
    }
}
