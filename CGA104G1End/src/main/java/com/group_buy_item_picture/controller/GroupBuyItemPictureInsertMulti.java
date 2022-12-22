package com.group_buy_item_picture.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.group_buy_item.model.Group_Buy_ItemVO;
import com.group_buy_item_picture.model.groupBuyItemPictureService;
import com.group_buy_item_picture.model.groupBuyItemPictureVO;

@WebServlet("/groupBuyItemPicture/groupBuyItemPictureInsertMulti.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GroupBuyItemPictureInsertMulti extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GroupBuyItemPictureInsertMulti() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        List<String> errorMsgs = new LinkedList<String>();
        req.setAttribute("errorMsgs", errorMsgs);
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
        // Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
        Integer gbitem_id = null;
        try {
            gbitem_id = Integer.valueOf(req.getParameter("gbitem_id").trim());
            System.out.println(gbitem_id);
        } catch (NumberFormatException e1) {
            errorMsgs.add("團購商品編號請填數字.");
            e1.printStackTrace();
        }

        String gbitem_name = req.getParameter("gbitem_name");

        // String gbitemNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]$";
        if (gbitem_name == null || gbitem_name.trim().length() == 0) {
            errorMsgs.add("團購商品名稱: 請勿空白");
        }
        // else if (!gbitem_name.trim().matches(gbitemNameReg)) {
        // errorMsgs.add("團購商品名稱: 只能是中、英文字母、數字和_ ");
        // }

        String gbitem_content = req.getParameter("gbitem_content");
        if (gbitem_content == null || gbitem_content.trim().length() == 0) {
            errorMsgs.add("團購商品內容請勿空白");
        }

        Integer gbitem_price = null;

        try {
            gbitem_price = Integer.valueOf(req.getParameter("gbitem_price").trim());

        } catch (NumberFormatException e) {
            errorMsgs.add("團購商品價格請填數字");
            e.printStackTrace();
        }

        Integer gbitem_status = null;

        try {
            gbitem_status = Integer.valueOf(req.getParameter("gbitem_status"));

        } catch (NumberFormatException e) {
            errorMsgs.add("團購商品狀態請填數字");
            e.printStackTrace();
        }

        java.sql.Date gbitem_startdate = null;

        try {
            gbitem_startdate = java.sql.Date.valueOf(req.getParameter("gbitem_startdate").trim());
        } catch (IllegalArgumentException e) {
            gbitem_startdate = new java.sql.Date(System.currentTimeMillis());
            e.printStackTrace();
            errorMsgs.add("請輸入日期!");
        }

        java.sql.Date gbitem_enddate = null;

        try {
            gbitem_enddate = java.sql.Date.valueOf(req.getParameter("gbitem_enddate").trim());
        } catch (IllegalArgumentException e) {
            gbitem_enddate = new java.sql.Date(System.currentTimeMillis());
            e.printStackTrace();
            errorMsgs.add("請輸入日期!");
        }
        Integer gbitem_type = null;

        try {
            gbitem_type = Integer.valueOf(req.getParameter("gbitem_type").trim());
        } catch (Exception e) {
            e.printStackTrace();
            errorMsgs.add("團購商品類別不可為空");
        }


        Group_Buy_ItemVO group_Buy_ItemVO = new Group_Buy_ItemVO();
        group_Buy_ItemVO.setGbitem_id(gbitem_id);
        group_Buy_ItemVO.setGbitem_name(gbitem_name);
        group_Buy_ItemVO.setGbitem_content(gbitem_content);
        group_Buy_ItemVO.setGbitem_price(gbitem_price);
        group_Buy_ItemVO.setGbitem_status(gbitem_status);
        group_Buy_ItemVO.setGbitem_startdate(gbitem_startdate);
        group_Buy_ItemVO.setGbitem_enddate(gbitem_enddate);
        group_Buy_ItemVO.setGbitem_type(gbitem_type);

        groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();

        List<groupBuyItemPictureVO> originalList = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);
        req.setAttribute("list2", originalList);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
            RequestDispatcher failureView = req
                    .getRequestDispatcher("/backend/group_Buy_Item/update_groupBuyItem_input.jsp");
            failureView.forward(req, res);
            return;
        }
        /*************************** 2.開始修改資料 *****************************************/

        // 開始執行上傳檔案
        Collection<Part> list = req.getParts();
        List<byte[]> picList = new ArrayList<byte[]>();
        BufferedInputStream bis = null;
        byte[] gbip_content = null;

        System.out.println("總共上傳的圖片張數:" + list.size());

        for (Part part : list) {
            bis = new BufferedInputStream(part.getInputStream());
            if (bis.available() > 1024) {
                gbip_content = new byte[bis.available()];
                bis.read(gbip_content);
                picList.add(gbip_content);
            }
        }

        if (picList.size() == 0) {
            errorMsgs.add("請選擇要上傳的圖片");
        }

        if (!errorMsgs.isEmpty()) {
            // 從資料庫讀取 groupBuyItemPictureVO 存入 list 中
            List<groupBuyItemPictureVO> list2 = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);
            req.setAttribute("list2", list2);
            req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);

            RequestDispatcher failureView = req
                    .getRequestDispatcher("/backend/group_Buy_Item/update_groupBuyItem_input.jsp");
            failureView.forward(req, res);
            return;
        }
        // 新增圖片資料
        for (int i = 0; i < picList.size(); i++) {
            gbipSvc.addGroupBuyItemPicture(gbitem_id, picList.get(i));
        }
        /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
        // 從資料庫讀取 groupBuyItemPictureVO 存入 list 中
        List<groupBuyItemPictureVO> list2 = gbipSvc.getAllGroupBuyItemPictureByGbitemID(gbitem_id);
        req.setAttribute("list2", list2);
        req.setAttribute("Group_Buy_ItemVO", group_Buy_ItemVO);
        RequestDispatcher successView = req
                .getRequestDispatcher("/backend/group_Buy_Item/update_groupBuyItem_input.jsp");
        successView.forward(req, res);

    }

}
