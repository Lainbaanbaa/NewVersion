package com.group_join.model;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
public class Group_JoinGetAll {

public static String getEmpCondition(String columnName, String value) {
		
		String Condition = null;
		
		if ("gb_id".equals(columnName) || "mem_id".equals(columnName) || "gbpay_status".equals(columnName) || "pickup_status".equals(columnName) || "deliver_status".equals(columnName) || "gbbuy_amount".equals(columnName) || "gbbuy_price".equals(columnName))
			Condition = columnName + "=" + value;
//		else if ("gb_name".equals(columnName))
//			Condition = columnName + " like '%" + value + "%'";
//		else if ("onjob".equals(columnName))                        
//			Condition = columnName + "=" + "'"+ value +"'";                         
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
				String Condition = getEmpCondition(key, value.trim());

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
		map.put("gb_id", new String[] { "1" });
		map.put("pickup_status", new String[] { "" });
		map.put("gbpay_status", new String[] { "1" });
		map.put("action", new String[] { "getXXX" }); // 注意Map裡面會含有action的key

		String finalSQL = "select * from group_join "
				          + Group_JoinGetAll.getWhereCondition(map)
				          + "order by gb_id";
		System.out.println("●●finalSQL = " + finalSQL);

	}
}

