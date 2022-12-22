package com.item.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;
import org.json.JSONArray;
import org.json.JSONObject;

import com.item.model.ItemService;
import com.item.model.ItemVO;
import com.itemPhotos.model.ItemPhotosService;
import com.itemPhotos.model.ItemPhotosVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 10 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/item/items")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if ("GetCount".equals(action)) {
            ItemService itemService = new ItemService();
            JSONObject jsonObject = itemService.getCount();
            Writer out = res.getWriter();
            out.write(jsonObject.toString());
        } else if ("GetAll_For_Display_JS".equals(action)) {

            Integer pageNumber = Integer.valueOf(req.getParameter("pageNumber"));
            ItemService itemService = new ItemService();
            JSONArray jsonArray = itemService.getAllJs(pageNumber);

            Writer out = res.getWriter();
            out.write(jsonArray.toString());
            //把拿到的jsonarray轉成json格式的字串
        } else if ("getOne_For_DisplayJS".equals(action)) {
            Integer item_id = Integer.valueOf(req.getParameter("itemId"));

            ItemService itemSvc = new ItemService();

            ItemVO itemVO = itemSvc.getItem(item_id);


            JSONObject item = new JSONObject();
            item.put("itemId", itemVO.getItemId());
            item.put("itemtId", itemVO.getItemtId());
            item.put("itemName", itemVO.getItemName());
            item.put("itemContent", itemVO.getItemContent());
            item.put("itemPrice", itemVO.getItemPrice());
            item.put("itemAmount", itemVO.getItemAmount());
            item.put("itemStatus", itemVO.getItemStatus());
            item.put("itemDate", itemVO.getItemDate());
            item.put("itemEnddate", itemVO.getItemEnddate());
            item.put("itemtName", itemVO.getItemTypeVO().getItemtName());


            Writer out = res.getWriter();
            out.write(item.toString());


        } else if ("getAllList".equals(action)) {

            ItemService itemService = new ItemService();
            Writer out = res.getWriter();
            JSONArray jsonArray = itemService.getAllList();
            out.write(jsonArray.toString());
        } else if ("GetAllPhotos".equals(action)) {

            ItemPhotosService itemPhotosService = new ItemPhotosService();
            Integer item_id = Integer.valueOf(req.getParameter("item_id"));
            JSONArray list = itemPhotosService.getAllPhoto(item_id);

            Writer out = res.getWriter();
            out.write(list.toString());
        } else if ("search".equals(action)) {

            String keyWords = req.getParameter("keyWords");
            Integer typeId = Integer.valueOf(req.getParameter("type"));


            ItemService itemService = new ItemService();
            JSONArray jsonArray = itemService.search(keyWords, typeId);

            Writer out = res.getWriter();
            out.write(jsonArray.toString());

        } else if ("frontEndSearch".equals(action)) {

            String keyWords = req.getParameter("keyWords");
            Integer typeId = Integer.valueOf(req.getParameter("type"));

            ItemService itemService = new ItemService();
            JSONArray jsonArray = itemService.frontEndSearch(keyWords, typeId);

            Writer out = res.getWriter();
            out.write(jsonArray.toString());

        } else if ("getFavList".equals(action)) {
            HttpSession httpSession = req.getSession();
            String memId = ((Integer) httpSession.getAttribute("mem_id")).toString();

            ItemService itemService = new ItemService();
            String data = itemService.getFavList(memId);

            Writer out = res.getWriter();
            out.write(data);

        } else if ("moveFavList".equals(action)) {
            System.out.println("有執行！！！！！！！！！");
            String itemId = req.getParameter("id");

            HttpSession httpSession = req.getSession();
            String memId =((Integer)  httpSession.getAttribute("mem_id")).toString();

            ItemService itemService = new ItemService();
            String result = itemService.removeTraceList(memId, itemId);

            Writer out = res.getWriter();
            out.write(result);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("update".equals(action)) {


            Gson gson = new Gson();
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
            //ItemVO vo = gson.fromJson(req.getReader(), ItemVO.class);

            JsonObject json = gson.fromJson(req.getReader(), JsonObject.class);
            ItemVO vo = gson.fromJson(json.get("item"), ItemVO.class);
            String[] photo64 = gson.fromJson(json.get("photo"), String[].class);

            List<byte[]> photo = new ArrayList<byte[]>();

            if (photo64.length != 0) {
                for (String image : photo64) {
                    String base64Image = image.split(",")[1];
                    byte[] transform = Base64.getDecoder().decode(base64Image);
                    photo.add(transform);
                }
            }
//			System.out.println(vo.getItemDate());
            ItemService itemService = new ItemService();
            itemService.updateJS(vo);


            if (photo.size() != 0) {
                for (byte[] p : photo) {
                    ItemPhotosService itemPhotosService = new ItemPhotosService();
                    ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
                    itemPhotosVO.setItemId(vo.getItemId());
                    itemPhotosVO.setIpPhoto(p);
                    itemPhotosService.insert(itemPhotosVO);
                }
            }


        }
        if ("delete".equals(action)) {

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);

            Integer itemId = jsonObject.get("deleteId").getAsInt();

            System.out.println("itemId:" + itemId);
            ItemService itemSvc = new ItemService();
            itemSvc.deleteItem(itemId);

        }
        if ("getOne_For_Display".equals(action)) {
            Integer item_id = Integer.valueOf(req.getParameter("item_id"));
            ItemService itemSvc = new ItemService();
            ItemVO itemVO = itemSvc.getItem(item_id);


            req.setAttribute("itemVO", itemVO);

            String url = "/item/listOneItem.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }

        if ("GetAllPhotos".equals(action)) {

            ItemPhotosService itemPhotosService = new ItemPhotosService();
            Integer item_id = Integer.valueOf(req.getParameter("item_id"));
            JSONArray list = itemPhotosService.getAllPhoto(item_id);

            Writer out = res.getWriter();
            out.write(list.toString());
        }

        if ("insertItem".equals(action)) {
            Gson gson = new Gson();
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

            JsonObject json = gson.fromJson(req.getReader(), JsonObject.class);
            ItemVO vo = gson.fromJson(json.get("item"), ItemVO.class);
            String[] photo64 = gson.fromJson(json.get("photo"), String[].class);

            List<byte[]> photo = new ArrayList<byte[]>();

            if (photo64.length != 0) {
                for (String image : photo64) {
                    String base64Image = image.split(",")[1];
                    byte[] transform = Base64.getDecoder().decode(base64Image);
                    photo.add(transform);
                }
            }

            ItemService itemService = new ItemService();
            Integer result = itemService.addItemJS(vo);


            if (photo.size() != 0) {
                for (byte[] p : photo) {
                    ItemPhotosService itemPhotosService = new ItemPhotosService();
                    ItemPhotosVO itemPhotosVO = new ItemPhotosVO();
                    itemPhotosVO.setItemId(vo.getItemId());
                    itemPhotosVO.setIpPhoto(p);
                    itemPhotosService.insert(itemPhotosVO);
                }
            }

            JSONObject resultObject = new JSONObject();
            resultObject.put("successful", result > 0 ? "true" : "false");
            Writer out = res.getWriter();
            out.write(resultObject.toString());


        } else if ("trace".equals(action)) {
            Gson gson = new Gson();
            JsonObject json = gson.fromJson(req.getReader(), JsonObject.class);

            HttpSession httpSession = req.getSession();
            String memId = ((Integer) httpSession.getAttribute("mem_id")).toString();

            System.out.println(memId);


            ItemService itemService = new ItemService();
            itemService.insertFavList(json, memId);


        }


    }


}
