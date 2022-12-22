package com.itemPhotos.controller;

import com.itemPhotos.model.ItemPhotosService;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/item/GetItemPhoto")
public class GetItemPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("getOnePhoto".equals(action)) {

			String item_id = request.getParameter("item_id");

			ItemPhotosService service = new ItemPhotosService();
			byte[] photo = service.getPhoto(Integer.parseInt(item_id));
			if (photo != null && photo.length != 0) {
				response.getOutputStream().write(photo);
			}
		} else if("getAllPhoto".equals(action)) {
			Integer item_id = Integer.valueOf(request.getParameter("item_id"));
			ItemPhotosService service = new ItemPhotosService();
			JSONArray photo=service.getAllPhoto(item_id);
//
			PrintWriter out=response.getWriter();
			out.write(photo.toString());
		} else if("DeletePhoto".equals(action)) {

			ItemPhotosService itemPhotosService =new ItemPhotosService();
			Integer ip_id = Integer.valueOf(request.getParameter("ip_id"));
			boolean  result= itemPhotosService.deletePhoto(ip_id);
			JSONObject resultObject=new JSONObject();
			resultObject.put("successful",result);
			System.out.println(resultObject);
			Writer out=response.getWriter();
			out.write(resultObject.toString());
		}
	}
}
