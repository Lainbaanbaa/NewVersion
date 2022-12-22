package com.coupon.controller;

import com.coupon.model.service.CouponService;
import com.coupon.model.service.impl.CouponServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet(name = "RemoveCouponServlet", value = "/backend/coupon/removeCoupon.do")
public class RemoveCouponServlet extends HttpServlet {

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

        if ("delete".equals(action)) { // 來自listAllEmp.jsp


            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /*************************** 1.接收請求參數 ***************************************/
            Integer couponId = Integer.valueOf(req.getParameter("coupon_id"));

            /*************************** 2.開始刪除資料 ***************************************/
            CouponServiceImpl couponService = new CouponServiceImpl();
            boolean b = couponService.removeCoupon(couponId);
            if (!b) {
                errorMsgs.add("折價券編號 " + couponId + " 已被擁有，因此無法移除!");
            }

            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req.getRequestDispatcher("/backend/coupon/listAllCoupon.jsp");
                failureView.forward(req, res);
                return;
            }
            /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
            String url = "/backend/coupon/listAllCoupon.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }
    }
}
