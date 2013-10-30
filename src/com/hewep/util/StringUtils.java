
package com.hewep.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.DbKit;
import com.jfinal.plugin.activerecord.Record;


public class StringUtils {
	
	// 将 nodeMap 转化为字符串
	public static String toString(Map<String,TreeNode> nodeMap){
		List<TreeNode> nodes = new ArrayList<TreeNode>();
		TreeNode temp;
		for (TreeNode node : nodeMap.values()) {
			temp = nodeMap.get(node.getPid());			
			if(temp != null){
				temp.addItem(node);
			}else{
				nodes.add(node);
			}
		}
		
		// 处理 json 数据
		JSONArray jsonArray = JSONArray.fromObject(nodes);
		for (Object object : jsonArray) {
			JSONObject obj = (JSONObject)object;
			// 取第一个菜单
			JSONObject tempObj = obj.getJSONArray("items").getJSONObject(0);
			if(tempObj !=null && (tempObj = tempObj.getJSONArray("items").getJSONObject(0)) != null ){
				String code = tempObj.get("id").toString();
				obj.element("homePage", code);
			}
			obj.element("menu", obj.get("items"));
			obj.remove("items");
		}
		return jsonArray.toString();
	}
	
	// 获取表中 字段名 和 注释 格式: [{"title":"编号","dataIndex":"id"}]
	public static String getColumns(String tableName){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Connection conn = null;
		try {
			conn = DbKit.getConnection();
			DatabaseMetaData  dmd =  conn.getMetaData();
			ResultSet rs = dmd.getColumns(null, "%", tableName, "%");
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", rs.getString("REMARKS"));
				map.put("dataIndex", rs.getString("COLUMN_NAME"));
				list.add(map);
			}
			Map<String, String> temp = new HashMap<String, String>();
			temp.put("title", "操作");
			temp.put("renderer", "function(){return '<span class=\"grid-command btn-edit\">编辑</span>';}");
			list.add(temp);
		} catch (Exception e) {
			throw new ActiveRecordException(e);
		} finally {
			DbKit.close(conn);
		}
		int width = 100/(list.size());
		for (Map<String, String> map : list) {
			map.put("width", width+"%");
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
		
	}
	// 获取查询记录json : [{"id":1,"name":"hewep"}]
	public static String getJsonData(List<Record> list){
		List<String> columns = new ArrayList<String>();
		for (Record record : list) {
			columns.add(record.toJson());
		}
		JSONArray jsonArray = JSONArray.fromObject(columns);
		
		return jsonArray.toString();
	}
	
}
