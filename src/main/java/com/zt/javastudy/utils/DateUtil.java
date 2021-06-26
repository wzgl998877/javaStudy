package com.zt.javastudy.utils;
 
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
 
public class DateUtil {
	/**
	 * 获取两个日期之间的所有日期
	 * @param startTime 开始日期
	 * @param endTime 结束日期
	 * @return
	 */
	public static List<String> getDayBetween(String startTime, String endTime) {
 
	    // 返回的日期集合
	    List<String> days = new ArrayList<String>();
 
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date start = dateFormat.parse(startTime);
	        Date end = dateFormat.parse(endTime);
 
	        Calendar tempStart = Calendar.getInstance();
	        tempStart.setTime(start);
 
	        Calendar tempEnd = Calendar.getInstance();
	        tempEnd.setTime(end);
	        tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
	        while (tempStart.before(tempEnd)) {
	            days.add(dateFormat.format(tempStart.getTime()));
	            tempStart.add(Calendar.DAY_OF_YEAR, 1);
	        }
 
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
 
	    return days;
	}
	
	/**
	   * 	获取两个月份之间的所有月份(含跨年)
	   * @param minDate
	   * @param maxDate
	   * @return
	   * @throws ParseException
	   */
	  public static List<String> getMonthBetween(String minDate, String maxDate) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");// 格式化为年月
		try {
			Calendar min = Calendar.getInstance();
			Calendar max = Calendar.getInstance();
 
			min.setTime(sdf.parse(minDate));
			// 设置为当月的第一天
			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
			System.out.println(min.getTime());
			max.setTime(sdf.parse(maxDate));
			// 设置为当月的第二天
			max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
			System.out.println(max.getTime());
			Calendar curr = min;
			while (curr.before(max)) {
				result.add(sdf.format(curr.getTime()));
				curr.add(Calendar.MONTH, 1);
			}
 
			// 实现排序方法
			Collections.sort(result, new Comparator<Object>() {
				@Override
				public int compare(Object o1, Object o2) {
					String str1 = (String) o1;
					String str2 = (String) o2;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
					Date date1 = null;
					Date date2 = null;
					try {
						date1 = format.parse(str1);
						date2 = format.parse(str2);
					} catch (ParseException e) {
						e.printStackTrace();
					}
 
					if (date2.compareTo(date1) > 0) {
						return -1;
					}
					return 1;
				}
			});
		}catch (ParseException e) {
	        e.printStackTrace();
	    }
		
		return result;
	}
	
	public static void main(String[] args) {
		/*List<String> days = getDayBetween("2021-04-26", "2021-05-02");
		for (String day : days) {
			System.out.println(day);
		}*/
		
		List<String> monthBetween = getMonthBetween("2020-03", "2020-04");
		for (String month : monthBetween) {
			System.out.println(month);
		}
	}
}