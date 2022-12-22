/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.share.controller;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.itemPhotos.model.ItemPhotosService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

@WebServlet(name = "HomeSlickServlet", value = "/HomeSlickServlet")
public class HomeSlickServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

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

        PrintWriter pw = res.getWriter();
//        ItemVO itemVO = new ItemVO();
        ItemService itemService = new ItemService();
        ItemPhotosService itemPhotosService = new ItemPhotosService();

        List<ItemVO> item = itemService.getAll();
        List<Integer> tempList = new ArrayList<Integer>();
        List<Integer> itemList = new ArrayList<>();
        List<Integer> itemResult = item
                .stream()
                .map(ItemVO::getItemId)
                .toList();
        for (int i = 0; i <= 5; i++) {
            int random = new Random().nextInt(itemResult.size());
            if (!tempList.contains(random)) {
                tempList.add(random);
                itemList.add(itemResult.get(random));
            } else {
                i--;
            }
        }

        List<ItemVO> items = itemList
                .stream()
                .map(itemService::getItem)
                .toList();
List<Integer> itemIdList = items
                .stream()
                .map(ItemVO::getItemId)
                .toList();
        List<String> nameList = items
                .stream()
                .map(ItemVO::getItemName)
                .toList();

        List<Integer> priceList = items
                .stream()
                .map(ItemVO::getItemPrice)
                .toList();

        List<byte[]> photoList = itemList
                .stream()
                .map(itemPhotosService::getPhoto)
                .toList();

        JSONArray jsonArr = new JSONArray();

        try {
            for (int integer = 0; integer < itemList.size(); integer++) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("itemId", itemList.get(integer));
                jsonObj.put("itemName", nameList.get(integer));
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
