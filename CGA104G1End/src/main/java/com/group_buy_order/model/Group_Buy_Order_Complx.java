package com.group_buy_order.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.emp.model.EmpNoEffect;

public class Group_Buy_Order_Complx {
	
	public static String getgroup_buy_order_Condition(String columnName, String value) {
		
		String Condition = null;
		
		if ("gborder_id".equals(columnName) || "gbitem_id".equals(columnName)||"gb_id".equals(columnName)||"gbitem_amount".equals(columnName)||"gboriginal_price".equals(columnName)||"gb_endprice".equals(columnName)||"gborder_paying".equals(columnName)||"gborder_send".equals(columnName)||"gborder_status".equals(columnName)||"gborder_other".equals(columnName))
			Condition = columnName + "=" + value;
		else if ("gborder_other".equals(columnName)||"tracking_num".equals(columnName)||"receiver_name".equals(columnName)||"receiver_address".equals(columnName)||"receiver_phone".equals(columnName))
			Condition = columnName + " like '%" + value + "%'";
		else if ("gborder_date".equals(columnName)||"pickup_time".equals(columnName))                        
			Condition = columnName + "=" + "'"+ value +"'";                         
//		    Condition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle 的 date
		
		return Condition + " ";
	}
	
	public static String getWhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		StringBuffer whereCondition = new StringBuffer();
		int count = 0;
		for (String key : keys) {
			String value = map.get(key)[0];
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String Condition = getgroup_buy_order_Condition(key, value.trim());

				if (count == 1)
					whereCondition.append(" where " + Condition);
				else
					whereCondition.append(" and " + Condition);

				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}
		
		return whereCondition.toString();
	}
	public static void main(String argv[]) {

		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試
		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("gborder_id", new String[] { "1" });
		map.put("gborder_other", new String[] { " " });
		map.put("gborder_date", new String[] { " " });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from Group_Buy_Order "
				          + Group_Buy_Order_Complx.getWhereCondition(map)
				          + "order by gborder_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
	
}
