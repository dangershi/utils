import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 普通工具
 */
public class Tools {

	
	/**
	  * 验证邮箱
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * 验证手机号码
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((1[0-9][0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	 /**
	  * 正则效验
	  * @param mobiles
	  * @return
	  */
	 public static boolean regularCheck(String regex, String text){
		 boolean isMatch = Pattern.matches(regex, text);
		 
		 return isMatch;
	 }
	 /**
	  * 校验数字是否是正整数
	  * @param str
	  * @return
	  */
	 public static boolean IsIntNumber(String str) {
		 boolean flag = false;
		 try{
		    Pattern regex = Pattern.compile("^\\+?[1-9][0-9]*$");
		    Matcher matcher = regex.matcher(str);
		    flag = matcher.matches();
		 }catch(Exception e){
		    flag = false;
		 }
		 return flag;
	 }
	 
	 /**
	 * 判断一个数字是是否是正的浮点数字
	 * @param num
	 * @return TRUE 表示是数字
	 * 			false 表示不是数字
	 */
	 public static boolean isDecial(String num) {
		String str = num;
		
		// 智能是 正的浮点型数字
//			String regex =  "^((\\d+\\.\\d*[1-9]\\d*)|(\\d*[1-9]\\d*\\.\\d+)|(\\d*[1-9]\\d*))$";//正浮点数   
		String regex =   "^\\d+(\\.\\d+)?$"; //非负浮点数   
		boolean matches = str.matches(regex);
		
		return matches;
		
	}
	 /**
	  * 去除小数点后面无用的0
	  * 1.如 5.600 --->5.6
	  * 2.如 15.00 --->15
	  * @param str
	  * @return
	  */
	 public static String trimNotNeedZero(String str){
		 if(str.indexOf(".") > 0){
	  	    //正则表达
			str = str.replaceAll("0+?$", "");//去掉后面无用的零
			str = str.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
  	     }
		 return str;
	 }
	 
	 /**
	  * 获取手机号码 后8位作为密码
	  * @param phone
	  * @return
	  */
	 public static String createPassword(String phone) {
		 boolean checkMobileNumber = Tools.checkMobileNumber(phone);
		 if(checkMobileNumber) {
			 String password = phone.substring(3, 11);
			 return password;
		 }
		 
		 return null;
	 }
	 
    /**
     * 生成一个指定范围内的随机小数(保留两位)
     * @param start
     * @param end
     * @param decimal
     * @return
     */
    public static double randomDouble(int start,int end){  
        DecimalFormat df=new DecimalFormat("0.00");  
        double rtnn = start + Math.random() * (end - start);  
        if (rtnn == start || rtnn == end) {  
            return randomDouble(start, end);  
        }  
        return new Double(df.format(rtnn).toString());  
    }  
}
