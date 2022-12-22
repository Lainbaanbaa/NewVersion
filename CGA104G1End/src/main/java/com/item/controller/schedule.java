package com.item.controller;

import com.item.model.ItemService;
import com.item.model.ItemVO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@WebServlet(name = "schedule",urlPatterns = {"/schedule"}, loadOnStartup = 1)
public class schedule extends HttpServlet {
    Timer timer = new Timer();
    Calendar cal = Calendar.getInstance();

    @Override
    public void init() throws ServletException {
        timer.scheduleAtFixedRate(task, cal.getTime(), 24 * 60 * 60 * 1000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            ItemService itemService = new ItemService();
            List<ItemVO> list = itemService.getAll();
            for (ItemVO item : list) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date today = Date.valueOf(dateFormat.format(cal.getTime()));
                Integer result = item.getItemEnddate().compareTo(today);
                if (result < 0) {
                    itemService.expire(item.getItemId());
                }
            }
        }
    };

    @Override
    public void destroy() {
        timer.cancel();
    }

}
