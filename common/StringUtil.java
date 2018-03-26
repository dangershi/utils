import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.google.common.base.Strings;

public class StringUtil {

	
	/**
	 * 返回指定日期Date 后/前 指定天数 的日期 格式yyyy-MM-dd  
	 * @param date
	 * @param days <0 为指定日期前 
	 * @return string yyyy-MM-dd  
	 */
	public static String getNextDateToString(Date date,long days){
		if(date == null) return null;
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate.plusDays(days).toString();
	}
	
	
	
	/**
	 * 校验是否有空
	 * @param strs
	 * @return 
	 */
	public static boolean isEmpty(String ... strs){
		for (int i = 0; i < strs.length; i++) {
			if(StringUtils.isEmpty(strs[i]) || StringUtils.isEmpty(strs[i].trim())) return true;
		}
	return false;
	
}
	/**
	 * 返回指定日期Date 后/前 指定天数 的日期
	 * @param date
	 * @param days
	 * @return
	 */
	public static LocalDate getNextDays(Date date,long days){
		if(date == null) return null;
		Instant instant = date.toInstant();
		ZoneId zone = ZoneId.systemDefault();
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
		LocalDate localDate = localDateTime.toLocalDate();
		return localDate.plusDays(days);
	}
	
	/**
	 * 获取指定日期 与当前日期相差天数 若指定日期>当前 返回>0
	 * @param dateStr yyyy-MM-dd
	 * @return
	 */
	public static Integer getDaysBetweenCurrentTDate(String dateStr){
		if(isNullOrEmpty(dateStr)) return null;
		String[] array = dateStr.split("-");
		LocalDate java8Release = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		LocalDate today = LocalDate.now();
		Period periodToNextJavaRelease = Period.between(today, java8Release);
		return periodToNextJavaRelease.getDays();
		
	}
	/**
	 * 判断参数是否为空，只有其中有一个参数为空则返回true，否则返回false
	 * 
	 * @modifier:
	 * @modify_description:
	 * @param objects
	 * @return
	 * @version v1.0.0
	 */
	public static boolean isEmpty(Object... objects) {
		boolean result = false;
		for (Object object : objects) {
			if (object instanceof String) {
				if (object == null || "".equals(object)) {
					return true;
				}
			}
			if (object instanceof Integer) {
				if (object == null || 0 == (Integer) object) {
					return true;
				}
			}
			if (object instanceof Double) {
				if (object == null || 0 == (Double) object) {
					return true;
				}
			}
			if (object instanceof Float) {
				if (object == null || 0 == (Float) object) {
					return true;
				}
			}
			if (object instanceof Long) {
				if (object == null || 0 == (Long) object) {
					return true;
				}
			}
			if (object instanceof Date) {
				if (object == null || null == (Date) object) {
					return true;
				}
			}
			if (object instanceof Timestamp) {
				if (object == null || null == (Timestamp) object) {
					return true;
				}
			}
		
			if (object == null) {
				return true;
			}
		}
		return result;
	}

	/**
	 * 对象转为string格式，为空时，转为默认带入字符串
	 *
	 * @param obj
	 *            待转对象
	 * @param defaultStr
	 *            默认字符串
	 * @return 字符串
	 */
	public static String objToString(Object obj, String defaultStr) {
		if (obj == null) {
			return defaultStr;
		} else {
			return obj.toString().trim();
		}
	}
	
	public static String replaceNull(Object obj, String defaultStr) {
		if (isEmpty(obj)) {
			return defaultStr;
		} else {
			return obj.toString().trim();
		}
	}
	
	/**
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str){
		
		return Strings.isNullOrEmpty(str);
	}
	   public static String transferTopath(String path,String sourcepath){
		   try {
	        File source  = new File(sourcepath);
	        String targetName = UUID.randomUUID().toString().replace("-", "")+sourcepath.substring(sourcepath.lastIndexOf("."));;
	        String targetPath = null;
	        boolean isWindows = SystemUtils.isWindowSys();
	        if(isWindows){
	            targetPath = path + File.separator + targetName;
	        }else{
	            String dirPath = PropertiesUtil.getPropertyByKey("FILE_LOCATION");
	            targetPath = dirPath + targetName;
	        }
	        
	        File targetFile = new File(targetPath);
	       
	            FileUtils.copyFile(source, targetFile);
	        
	        
	        // 分成window 和Linux 两个系统
	        String fullUrl = null;
	        if(isWindows){
	            String ip = SystemUtils.getHostIp();
	            int port = 8080;
	            fullUrl = "http://" + ip + ":"+port + targetName;
	        }else{
	            fullUrl = PropertiesUtil.getPropertyByKey("FILE_URL") + targetName;
	        }
	        source.delete();
	        return fullUrl;
		   } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        } 
	    }
	
	public static  String listToString(List<Object> list){
			return StringUtils.join(list.toArray());
	    	
	} 
	
	public static String  extractNumber(String str){
		if(str == null || "".equals(str.trim())) return "";
		str=str.trim();
		String regex= "\\d+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		if(m.find()){
			  return m.group(0);  
		}
		return "";
	}
	
}
