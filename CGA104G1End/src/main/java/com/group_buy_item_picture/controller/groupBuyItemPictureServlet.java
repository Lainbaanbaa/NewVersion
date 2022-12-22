package com.group_buy_item_picture.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.group_buy_item_picture.model.groupBuyItemPictureService;
import com.group_buy_item_picture.model.groupBuyItemPictureVO;


@WebServlet("/groupBuyItemPicture/groupBuyItemPicture.do")
public class groupBuyItemPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public groupBuyItemPictureServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");

		
		ServletOutputStream out = res.getOutputStream();
		Integer gbitem_id = Integer.valueOf(req.getParameter("gbitem_id"));
		groupBuyItemPictureService gbipSvc = new groupBuyItemPictureService();
		groupBuyItemPictureVO gbipVO = gbipSvc.getOneGroupBuyItemPicture(gbitem_id);
		out.write(gbipVO.getGbip_content());
		
	
	}

}
