package com.hewep.util;

import java.text.SimpleDateFormat;
import java.util.Date;



import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class DateProcessor implements JsonValueProcessor{
	private String pattern = "yyyy-MM-dd";
	
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public Object processArrayValue(Object arg0, JsonConfig arg1) {
	String[] obj={};
	if(arg0 instanceof Date[]){	
		Date[] dates = (Date[])arg0;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		obj=new String[dates.length];
		for(int i=0; i<dates.length;i++){
			obj[i]=sdf.format(dates[i]);
			}
		}
	return obj;
	}

	public Object processObjectValue(
			String arg0, Object arg1, JsonConfig arg2) {
		if(arg1!=null){
			if(arg1 instanceof Date){
				Date date = (Date)arg1;
				SimpleDateFormat sdf = new SimpleDateFormat(pattern);
				return sdf.format(date);
			}
		}
		return "";
	}


}
