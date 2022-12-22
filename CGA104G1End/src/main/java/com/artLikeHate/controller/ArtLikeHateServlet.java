package com.artLikeHate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.artLikeHate.model.ArtLikeHateService;
import com.artLikeHate.model.ArtLikeHateVO;

@WebServlet("/ArtLikeHateServlet")
public class ArtLikeHateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {

			Integer mem_id = Integer.valueOf(req.getParameter("mem_id").trim());
			Integer article_id = Integer.valueOf(req.getParameter("article_id").trim());
			Integer like_status = Integer.valueOf(req.getParameter("like_status").trim());
			ArtLikeHateService artLikeHateSvc = new ArtLikeHateService();
			ArtLikeHateVO artLikeHateVO = artLikeHateSvc.insertUpdate(mem_id, article_id, like_status);
			Integer like = artLikeHateSvc.getLike(article_id);
			Integer hate = artLikeHateSvc.getHate(article_id);
	        HashMap likeHateMap = new HashMap();
	        
	        likeHateMap.put("like", like);
	        likeHateMap.put("hate", hate);
	        likeHateMap.put("status", like_status);
	        JSONObject responseJSONObject = new JSONObject(likeHateMap);
	       
	        PrintWriter out = res.getWriter();
	        out.println(responseJSONObject);
		}
	}

}
