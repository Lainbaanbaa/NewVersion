package com.favorite_list.model;

import java.util.*;

public interface Favorite_listDAO_interface {
	public void insert(Favorite_listVO favorite_listVO);
    public void update(Favorite_listVO favorite_listVO);
    public void delete(Integer mem_id);
    public Favorite_listVO findByPrimaryKey(Integer mem_id);
    public List<Favorite_listVO> getAll();
}
