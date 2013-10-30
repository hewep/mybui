package com.hewep.controller;

import java.util.List;
import java.util.Map;

import com.hewep.common.BaseController;
import com.hewep.util.StringUtils;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheKit;
import com.jfinal.plugin.ehcache.CacheName;
import com.jfinal.plugin.ehcache.EvictInterceptor;


public class MenuController extends BaseController{
	
	public void index(){
		String columns = StringUtils.getColumns("menu");
		this.setAttr("columns", columns);
		this.render("menu_list.html");
	}
	@Before(CacheInterceptor.class)  // 默认 actionKey 为cacheName
	@CacheName("menuList")
	public void list(){
		int pageSize = this.getParaToInt("pageSize");
		int pageNum = this.getParaToInt("pageNum");
		Page<Record> menuPage = Db.paginate(pageNum+1, pageSize, "select * ", "from menu order by id desc");
		String result = this.getResultByPage(menuPage);
		this.renderJson(result);
	}
	
	@Before(EvictInterceptor.class)
	@CacheName("menuList")
	public void addOrUpdateMenu() throws Exception{
		String result = this.getMsgByFailure();
		try {
			Map<String,Object> record = this.getParamMap();
			Object id = record.get("id");
			Record menu;
			if(id == null){
				menu = new Record().setColumns(record);
				Db.save("menu", menu);
			}else{
				menu = Db.findById("menu", Integer.parseInt(id.toString()));
				menu.setColumns(record);
				Db.update("menu", menu);
			}
			// 清楚 菜单缓存
			CacheKit.removeAll("initMenu");
			result = this.getMsgBySuccess();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally{
			this.renderJson(result);
		}
	}
	
	@Before(EvictInterceptor.class)
	@CacheName("menuList")
	public void delMenus() throws Exception{
		String result = this.getMsgByFailure();
		try {
			List<Integer> idList = this.getParaList("ids");
			for (Integer id : idList) {
				Db.deleteById("menu", id);
			}
			result = this.getMsgBySuccess();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally{
			this.renderJson(result);
		}
	}
}
