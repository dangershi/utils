import org.apache.commons.io.FilenameUtils;

public class StringTool {
	
	/**
	 * 获取文件名
	 * @param filename
	 * @return
	 */
	public static String getFileName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('/');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	/**
	 * 获取文件名后缀名
	 * @param extensionName
	 * @return
	 */
	public static String getExtensionName(String extensionName) {
		if ((extensionName != null) && (extensionName.length() > 0)) {
			int dot = extensionName.lastIndexOf('.');
			if ((dot > -1) && (dot < (extensionName.length() - 1))) {
				return extensionName.substring(dot + 1);
			}
		}
		return extensionName;
	}
	
	/**
	 * 获取文件名后缀名
	 * @param extensionName
	 * @return
	 */
	public static String getExtensionName2(String extensionName) {
		if ((extensionName != null) && (extensionName.length() > 0)) {
			
			String extension = FilenameUtils.getExtension(extensionName);
			/*int dot = extensionName.lastIndexOf('.');
			if ((dot > -1) && (dot < (extensionName.length() - 1))) {
				return extensionName.substring(dot + 1);
			}*/
		}
		return extensionName;
	}
	
	 public static String toChinese(String string) {
	        String[] s1 = { "零", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
	        String[] s2 = { "十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千" };

	        String result = "";

	        int n = string.length();
	        for (int i = 0; i < n; i++) {

	            int num = string.charAt(i) - '0';

	            if (i != n - 1 && num != 0) {
	                result += s1[num] + s2[n - 2 - i];
	            } else {
	                result += s1[num];
	            }
	        }
	       
	        result = result.replaceAll("零零", "零");
	        if(result.endsWith("零")){
	        	int lastIndexOf = result.lastIndexOf("零");
	        	result = result.substring(0, lastIndexOf);
	        }
	        
	        return result;

	    }
	 /**
	  * 获取字符串中有多少个中文
	  */
	 public static int getCharacterCount(String str){
         String regEx = "[^\u4e00-\u9fa5]+";
         String[] term = str.split(regEx);
         int count = 0;
         for(int i =0; i < term.length; i++){
        	 count = count + term[i].length();
         }
         return count;
	 }

}
