package com.hewep.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.hewep.util.StringUtils;
import com.hewep.util.TreeNode;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.jfinal.plugin.ehcache.CacheName;

public class IndexController extends Controller{
	
	public void index(){
		
		this.render("index.html");
	}
	@Before(CacheInterceptor.class)
	@CacheName("initMenu")
	public void initMenu(){
		List<Record> menus = Db.find("select * from menu where visible = 1 order by menu_sort");
		Map<String,TreeNode> nodeMap = new LinkedHashMap<String,TreeNode>();
		for (Record record : menus) {
			TreeNode node = new TreeNode(record.getStr("mid"),record.getStr("parent_mid"), record.getStr("menu_name"));
			
			node.setHref(record.getStr("href"));
			nodeMap.put(node.getId(), node);
		}
		this.renderText(StringUtils.toString(nodeMap));
	}
	
	public void login(){
		
	}
	
	public void getSystemInfo(){
		
		Properties props = System.getProperties();
		Runtime runtime = Runtime.getRuntime();
		long freeMemoery = runtime.freeMemory();
		long totalMemory = runtime.totalMemory();
		long usedMemory = totalMemory - freeMemoery;
		long maxMemory = runtime.maxMemory();
		long useableMemory = maxMemory - totalMemory + freeMemoery;
		this.setAttr("props", props);
		this.setAttr("freeMemoery", freeMemoery);
		this.setAttr("totalMemory", totalMemory);
		this.setAttr("usedMemory", usedMemory);
		this.setAttr("maxMemory", maxMemory);
		this.setAttr("useableMemory", useableMemory);
		
		this.render("system/sys_info.html");
	}
}
