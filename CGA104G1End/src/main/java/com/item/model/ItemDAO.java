package com.item.model;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Base64.Encoder;

import com.util.JedisUtil;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ItemDAO implements ItemDAO_interface {

    @Override
    public Integer insert(ItemVO itemVO) {
        getSession().persist(itemVO);
        return itemVO.getItemId();
    }

    //可刪
    @Override
    public void update(ItemVO itemVO) {
        ItemVO vo = getSession().load(ItemVO.class, itemVO.getItemId());
        vo.setItemName(itemVO.getItemName());
        vo.setItemContent(itemVO.getItemContent());
        vo.setItemPrice(itemVO.getItemPrice());
        vo.setItemDate(itemVO.getItemDate());
        vo.setItemEnddate(itemVO.getItemEnddate());
        vo.setItemAmount(itemVO.getItemAmount());
        vo.setItemStatus(itemVO.getItemStatus());
        vo.setItemtId(itemVO.getItemtId());

    }

    @Override
    public void updateJS(ItemVO itemVO) {
        ItemVO vo = getSession().load(ItemVO.class, itemVO.getItemId());
        vo.setItemName(itemVO.getItemName());
        vo.setItemContent(itemVO.getItemContent());
        vo.setItemPrice(itemVO.getItemPrice());
        vo.setItemDate(itemVO.getItemDate());
        vo.setItemEnddate(itemVO.getItemEnddate());
        vo.setItemAmount(itemVO.getItemAmount());
        vo.setItemStatus(itemVO.getItemStatus());
        vo.setItemtId(itemVO.getItemtId());

    }

    @Override
    public void delete(Integer itemId) {
//        ItemVO itemVO = new ItemVO();
//        itemVO.setItemId(itemId);
//        getSesion().remove(itemVO);

        Session session = getSession();
        ItemVO itemVO = session.get(ItemVO.class, itemId);
        session.remove(itemVO);
    }

    @Override
    public ItemVO findByPrimaryKey(Integer itemId) {
        //SQL
        final String sql = "select * from ITEM where ITEM_ID = :item";                            //只回傳一筆
        ItemVO itemVO = getSession().createNativeQuery(sql, ItemVO.class).setParameter("item", itemId).uniqueResult();
        return itemVO;

    }

    @Override
    public List<ItemVO> getAll() {
        //HQL
        return getSession().createQuery("FROM ItemVO", ItemVO.class).list();
    }

    @Override
    public JSONObject getCount() {
        String sql = "select count(*) from item where ITEM_STATUS=1;";
        int count = ((Number) (getSession().createNativeQuery(sql).uniqueResult())).intValue();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);

        return jsonObject;
    }


    @Override
    public JSONArray search(String keyWords, Integer type) {

        final StringBuilder sql = new StringBuilder().append("From ItemVO  where ");
        List<ItemVO> list=null;
        if (keyWords.trim().length() != 0 && !(keyWords == null)) {
            sql.append(" " + "itemName like :keyWords");
//            sql.append(" " + "itemName" + " " + "like" + " "  + "'%" + keyWords + "%'" + " ");
            if (type != 0) {
                sql.append(" "+"and"+" " + "itemtId" + " " + "=" + " " + type + " ");

            }
             list = getSession().createQuery(sql.toString(), ItemVO.class).setParameter("keyWords","%"+keyWords+"%").list();
        }
        if (!(type == 0)&&(keyWords.isEmpty())) {

            sql.append(" " + "itemtId" + " " + "=" + " " + type + " " );
            list=getSession().createQuery(sql.toString(), ItemVO.class).list();
        }

        // sql.append(" "+"ITEM_STATUS"+" "+"="+" "+"0 or ITEM_STATUS=1");
        JSONArray jsonArray = new JSONArray();

        for (ItemVO itemVO : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", itemVO.getItemId());
            jsonObject.put("itemtId", itemVO.getItemtId());
            jsonObject.put("itemName", itemVO.getItemName());
            jsonObject.put("itemContent", itemVO.getItemContent());
            jsonObject.put("itemPrice", itemVO.getItemPrice());
            jsonObject.put("itemAmount", itemVO.getItemAmount());
            jsonObject.put("itemStatus", itemVO.getItemStatus());
            jsonObject.put("itemDate", itemVO.getItemDate());
            jsonObject.put("itemEndDate", itemVO.getItemEnddate());
            jsonObject.put("itemtName",itemVO.getItemTypeVO().getItemtName());
            Encoder encoder = Base64.getEncoder();

            if (itemVO.getPhotos().size() != 0) {
                String photo64 = encoder.encodeToString(itemVO.getPhotos().get(0).getIpPhoto());
                jsonObject.put("itemPhoto", photo64);
            }

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    public JSONArray frontEndSearch(String keyWords, Integer type) {

        final StringBuilder sql = new StringBuilder().append("From ItemVO  where ");
        List<ItemVO> list=null;
        if (keyWords.trim().length() != 0 && !(keyWords == null)) {
            sql.append(" " + "itemName like :keyWords");
//            sql.append(" " + "itemName" + " " + "like" + " "  + "'%" + keyWords + "%'" + " ");
            if (type != 0) {
                sql.append(" "+"and"+" " + "itemtId" + " " + "=" + " " + type + " ");

            }
            sql.append("and"+" "+"itemStatus=1");
            list = getSession().createQuery(sql.toString(), ItemVO.class).setParameter("keyWords","%"+keyWords+"%").list();
        }
        if (!(type == 0)&&(keyWords.isEmpty())) {

            sql.append(" " + "itemtId" + " " + "=" + " " + type + " " +"and"+" "+"itemStatus=1");
            list=getSession().createQuery(sql.toString(), ItemVO.class).list();
        }

        // sql.append(" "+"ITEM_STATUS"+" "+"="+" "+"0 or ITEM_STATUS=1");
        JSONArray jsonArray = new JSONArray();

        for (ItemVO itemVO : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", itemVO.getItemId());
            jsonObject.put("itemtId", itemVO.getItemtId());
            jsonObject.put("itemName", itemVO.getItemName());
            jsonObject.put("itemContent", itemVO.getItemContent());
            jsonObject.put("itemPrice", itemVO.getItemPrice());
            jsonObject.put("itemAmount", itemVO.getItemAmount());
            jsonObject.put("itemStatus", itemVO.getItemStatus());
            jsonObject.put("itemDate", itemVO.getItemDate());
            jsonObject.put("itemEndDate", itemVO.getItemEnddate());
            jsonObject.put("itemtName",itemVO.getItemTypeVO().getItemtName());
            Encoder encoder = Base64.getEncoder();

            if (itemVO.getPhotos().size() != 0) {
                String photo64 = encoder.encodeToString(itemVO.getPhotos().get(0).getIpPhoto());
                jsonObject.put("itemPhoto", photo64);
            }

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    //listAllItems.html
    @Override
    public JSONArray getAllList() {
        JSONArray jsonArray = new JSONArray();
        List<ItemVO> list = getSession().createQuery("FROM ItemVO order by itemId", ItemVO.class).list();
        for (ItemVO itemVO : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", itemVO.getItemId());
            jsonObject.put("itemtId", itemVO.getItemtId());
            jsonObject.put("itemName", itemVO.getItemName());
            jsonObject.put("itemContent", itemVO.getItemContent());
            jsonObject.put("itemPrice", itemVO.getItemPrice());
            jsonObject.put("itemAmount", itemVO.getItemAmount());
            jsonObject.put("itemStatus", itemVO.getItemStatus());
            jsonObject.put("itemDate", itemVO.getItemDate());
            jsonObject.put("itemEndDate", itemVO.getItemEnddate());
            jsonObject.put("itemtName",itemVO.getItemTypeVO().getItemtName());
            Encoder encoder = Base64.getEncoder();

            if (itemVO.getPhotos().size() != 0) {

                String photo64 = encoder.encodeToString(itemVO.getPhotos().get(0).getIpPhoto());
                jsonObject.put("itemPhoto", photo64);
            }

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    //	shop.html 前台商城
    @Override
    public JSONArray getAllJS(int pageNumber) {
        JSONArray jsonArray = new JSONArray();
        List<ItemVO> list = getSession().createQuery("FROM ItemVO where itemStatus=1 ", ItemVO.class)
                .setFirstResult((pageNumber - 1) * 8)
                .setMaxResults(8)
                .list();
        for (ItemVO itemVO : list) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", itemVO.getItemId());
            jsonObject.put("itemtId", itemVO.getItemtId());
            jsonObject.put("itemName", itemVO.getItemName());
            jsonObject.put("itemContent", itemVO.getItemContent());
            jsonObject.put("itemPrice", itemVO.getItemPrice());
            jsonObject.put("itemAmount", itemVO.getItemAmount());
            jsonObject.put("itemStatus", itemVO.getItemStatus());
            jsonObject.put("itemDate", itemVO.getItemDate());
            jsonObject.put("itemEndDate", itemVO.getItemEnddate());
//            if (itemVO.getPhotos().size() != 0) {
//                jsonObject.put("itemPhoto",itemVO.getPhotos().get(0).getIpPhoto());
//
//            }
            Encoder encoder = Base64.getEncoder();

            if (itemVO.getPhotos().size() != 0) {

                String photo64 = encoder.encodeToString(itemVO.getPhotos().get(0).getIpPhoto());
                jsonObject.put("itemPhoto", photo64);
            }

            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }

    @Override
    public void insertFavList(String item, String memId) {
        JedisPool jedisPool = JedisUtil.getJedisPool();
        Jedis jedis = jedisPool.getResource();

        HashMap<String, String> data = new HashMap<>();
        data.put("item", item);

        jedis.hmset(memId, data);

        jedis.close();
    }

    @Override
    public String  getFavList(String memId) {
       JedisPool jedisPool=JedisUtil.getJedisPool();
       Jedis jedis=jedisPool.getResource();
       String data=jedis.hget(memId,"item");
       jedis.close();
       return data;
    }


}
