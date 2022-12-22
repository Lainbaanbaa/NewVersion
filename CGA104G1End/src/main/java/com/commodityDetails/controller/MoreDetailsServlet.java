package com.commodityDetails.controller;

import com.commodityDetails.model.entity.CommodityDetails;
import com.commodityDetails.model.service.CommodityDetailsService;
import com.commodityDetails.model.service.impl.CommodityDetailsServiceImpl;
import com.itemPhotos.model.ItemPhotosService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.List;

import static java.lang.System.out;

@WebServlet(name = "MoreDetailsServlet", value = "/MoreDetailsServlet")
public class MoreDetailsServlet extends HttpServlet {

    private CommodityDetailsService service;

    @Override
    public void init() throws ServletException {
        service = new CommodityDetailsServiceImpl();
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

        final Integer orderId = Integer.valueOf(req.getParameter("orderId"));


        PrintWriter pw = res.getWriter();
        ItemPhotosService itemPhotosService = new ItemPhotosService();
        List<CommodityDetails> items = service.listOrderDetails(orderId);

        List<Integer> itemIdList = items
                .stream()
                .map(CommodityDetails::getItemId)
                .toList();

        List<String> nameList = items
                .stream()
                .map(CommodityDetails::getItemName)
                .toList();

        List<Integer> amountList = items
                .stream()
                .map(CommodityDetails::getCdAmount)
                .toList();

        List<Double> priceList = items
                .stream()
                .map(CommodityDetails::getItemPrice)
                .toList();

        List<byte[]> photoList = itemIdList
                .stream()
                .map(itemPhotosService::getPhoto)
                .toList();

        JSONArray jsonArr = new JSONArray();

        try {
            for (int integer = 0; integer < itemIdList.size(); integer++) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("itemId", itemIdList.get(integer));
                jsonObj.put("itemName", nameList.get(integer));
                jsonObj.put("cdAmount", amountList.get(integer));
                jsonObj.put("itemPrice", priceList.get(integer));
                jsonObj.put("photo", Base64.getEncoder().encodeToString(photoList.get(integer)));
                jsonArr.put(jsonObj);
            }
        } catch (Exception e) {
            out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
        pw.println(jsonArr);
    }
}
