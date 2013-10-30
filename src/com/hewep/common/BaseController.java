package com.hewep.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hewep.util.StringUtils;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

public class BaseController extends Controller{
	
	/******************  获取请求参数 *****************************/
	public Map<String, Object> getParamMap(){
		Map<String, Object> record = new HashMap<String, Object>();
		Map<String, String[]> map= this.getParaMap();
		Set<String> keys = map.keySet();
		for (String string : keys) {
			if(map.get(string)[0] != null && !"".equals(map.get(string)[0])){
				record.put(string, map.get(string)[0]);
			}
		}
		return record;
	}
	
	public List<Integer> getParaList(String param){
		List<Integer> list = new ArrayList<Integer>();
		String ids = this.getPara(param);
		String[] idArr = ids.split(",");
		for (String string : idArr) {
			list.add(Integer.parseInt(string));
		}
		return list;
	}
	
	
	/*********************** 获取结果状态 *******************************/
	
	public String getMsgBySuccess(){
		return "{\"rtState\":1,\"rtMsg\":\"操作成功\"}";
	}
	public String getMsgByFailure(){
		return "{\"rtState\":0,\"rtMsg\":\"操作失败\"}";
	}
	public String getMsgBySuccess(String msg){
		return "{\"rtState\":1,\"rtMsg\":\""+msg+"\"}";
	}
	public String getMsgByFailure(String msg){
		return "{\"rtState\":0,\"rtMsg\":\""+msg+"\"}";
	}
	
	/************************ 处理结果集 ********************************/
	public String getResultByPage(Page<Record> page){
		String data = StringUtils.getJsonData(page.getList());
		String result = "{\"rows\":"+data+",\"total\":"+page.getTotalRow()+"}";
		return result;
	}
}
