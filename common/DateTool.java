import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateTool {
	
	
	 
	 /**
	  * 得到本周周一
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static Date getMondayOfThisWeek(Date date) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 1);
	  return c.getTime();
	 }
	 
	 /**
	  * 得到本周周日
	  * 
	  * @return yyyy-MM-dd
	  */
	 public static Date getSundayOfThisWeek(Date date) {
	  Calendar c = Calendar.getInstance();
	  c.setTime(date);
	  int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
	  if (day_of_week == 0)
	   day_of_week = 7;
	  c.add(Calendar.DATE, -day_of_week + 7);
	  return c.getTime();
	 }
	 	
	 	/**
		 * 查出日历某个月份所有的天数（按照星期分）
		 * @param month 需要查询的月份
		 * @return
		 * @throws Exception
		 */
		public static Date lastDayOfMonth(Date date)throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DAY_OF_MONTH, 1);
//			Date firstDayOfMonth = calendar.getTime();  
			calendar.add(Calendar.MONTH, 1);
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			Date lastDayOfMonth = calendar.getTime();
			
			return lastDayOfMonth;
		}
		
		/**
		 * 获取第二天
		 * @param date
		 * @return
		 * @throws Exception
		 */
		public static Date getNextDate(Date date)throws Exception{
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			return calendar.getTime();
		}
		
		public static List<Date> getOneMontDay(String dateStr)throws Exception{
			
			// 计算开始时间
			Date date = DateUtil.fomatDatesWithPattern(dateStr, "yyyy-MM");
			Date startDay = DateTool.getMondayOfThisWeek(date);
			
			// 结束时间
			Date lastDayOfMonth = DateTool.lastDayOfMonth(date);
			
			Date endDay = DateTool.getSundayOfThisWeek(lastDayOfMonth);
			
			List<Date> list = new ArrayList<Date>();
			
			while(!DateTool.isSameDay(startDay, endDay)){
				list.add(startDay);
//				String dateToString = DateUtil.dateToString(startDay, "yyyy-MM-dd");
//				System.err.println(dateToString);
				startDay = getNextDate(startDay);
			}
			list.add(endDay);
//			String dateToString = DateUtil.dateToString(endDay, "yyyy-MM-dd");
//			System.err.println(dateToString);
			
			return list;
		}
		
		/**
		 * 比较是不是同一天
		 * @param d1
		 * @param d2
		 * @return
		 * @throws Exception
		 */
		public static boolean isSameDay(Date d1,Date d2)throws Exception{
			boolean sameDay = org.apache.commons.lang.time.DateUtils.isSameDay(d1,  d2);
			return sameDay;
		}
}
