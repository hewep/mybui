package com.hewep.common;

import com.hewep.controller.IndexController;
import com.hewep.controller.MenuController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.tx.TxByRegex;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

public class AppConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);		
		me.setViewType(ViewType.FREE_MARKER);
		me.setBaseViewPath("/WEB-INF/view/");
		//me.setError404View("/common/404.html");
       // me.setError500View("/common/500.html");
	}
	
	@Override
	public void configRoute(Routes me) {
		me.add("/", IndexController.class);
		me.add("/menu", MenuController.class,"system/menu");
	}

	@Override
	public void configHandler(Handlers me) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new TxByRegex(".*addOrUpdate.*"));
		me.add(new TxByRegex(".*del.*"));
	}

	@Override
	public void configPlugin(Plugins me) {			
		me.add(new EhCachePlugin());
		C3p0Plugin cp = new C3p0Plugin(loadPropertyFile("jdbc.properties"));
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.setDialect(new MysqlDialect());
		arp.setShowSql(true);
		
		
	}
}
