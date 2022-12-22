package com.notification.mode;

import java.util.List;

import com.mem.model.MemVO;

public interface NotificationDAO_interface {
	public void insert(NotificationVO notificationVO);
    public void update(NotificationVO notificationVO);
    public void delete(Integer ntf_id);
    public NotificationVO findByPrimaryKey(Integer ntf_id);
    public List<NotificationVO> getAll();

}
