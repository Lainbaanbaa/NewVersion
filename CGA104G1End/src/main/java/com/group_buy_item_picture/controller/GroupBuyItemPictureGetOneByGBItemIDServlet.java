package com.group_buy_item_picture.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buy_item_picture.model.groupBuyItemPictureService;
import com.group_buy_item_picture.model.groupBuyItemPictureVO;

@WebServlet("/groupBuyItemPicture/groupBuyItemPictureGetOneByGBItemID.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class GroupBuyItemPictureGetOneByGBItemIDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GroupBuyItemPictureGetOneByGBItemIDServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		
		Integer gbip_id = Integer.valueOf(req.getParameter("gbip_id"));
		groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();
		groupBuyItemPictureVO gbipVO = gbipSvc.getOneGroupBuyItemPicture(gbip_id);
		
			ServletOutputStream out = res.getOutputStream();
			out.write(gbipVO.getGbip_content());
	}

}
