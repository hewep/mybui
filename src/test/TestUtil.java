package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestUtil {
	
	public static void main(String[] args) {
//		Map<String,String> map1 = new HashMap<String, String>();
//		Map<String,String> map2 = new HashMap<String, String>();
//		map1.put("hao", "123");
//		map1.put("wen", "hewep");
//		map2.putAll(map1);
//		
//		System.out.println(map1.equals(map2));
		
//		Pattern pattern = Pattern.compile("/bui/*");
//		System.out.println(pattern.matcher("/bui/zxc").matches());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		System.out.println(sdf.format(now));
		
		
		cal.add(Calendar.WEEK_OF_MONTH, -1);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date mon = cal.getTime();
		System.out.println(sdf.format(mon));
		
		cal.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		Date thur = cal.getTime();
		System.out.println(sdf.format(thur));
	}
}
